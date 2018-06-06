package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;
import com.luv2code.aopdemo.ArroundWithLoggerDemoApp;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object arroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out the method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n===============> Executing @Arround on method: " + method);
		
		// get begin time stamp
		long begin = System.currentTimeMillis();
		
		// execute the method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());
			
			// rethrow the exception
			throw e;
		}
		
		// get end time stamp
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		myLogger.info("\n========> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
	}
	
	// this is where we add all of our related advice for logging
	
	// start with an @Before advice
	
//	@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
	//@Before("execution(public void add*())")
	//@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("\n========> Executing @Before advice");
		
		// display the method signature
		MethodSignature methodSign = (MethodSignature)theJoinPoint.getSignature();
		myLogger.info("Method: " + methodSign);
		
		// display method arguments
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru args
		for (Object tempArg : args) {
			myLogger.info(tempArg.toString());
			if (tempArg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account)tempArg;
				myLogger.info("Account name: " + theAccount.getName());
				myLogger.info("Account level: " + theAccount.getLevel());
			}
		}
		
	}
	
	// Add new advice for @AfterReturning on the find account method
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", 
			returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		// print out the method are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n===============> Executing @AfterReturning on method: " + method);
		
		// print out the result
		myLogger.info("\n===============> Result is: " + result);
		
		// post-process the data... and modify it:
		// convert the account names to upper case
		convertAccountNameToUpperCase(result);
		
		myLogger.info("\n===============> Result after post-processing is: " + result);
	}
	
	// Add new advice for @AfterThowing on the find account method
	@AfterThrowing(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out the method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n===============> Executing @AfterThrowing on method: " + method);
		
		// log the exception
		myLogger.info("\n===============> The exception is: " + theExc);
	}
	
	// Add new after advice for @After on the find account method
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		// print out the method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n===============> Executing @After finally on method: " + method);
	}

	private void convertAccountNameToUpperCase(List<Account> result) {

		for (Account account : result) {
			account.setName(account.getName().toUpperCase());
		}
	}
	
	
}
