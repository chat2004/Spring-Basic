package com.luv2code.aopdemo.aspect;

import java.util.List;

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

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object arroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out the method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n===============> Executing @Arround on method: " + method);
		
		// get begin time stamp
		long begin = System.currentTimeMillis();
		
		// execute the method
		Object result = theProceedingJoinPoint.proceed();
		
		// get end time stamp
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		System.out.println("\n========> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
	}
	
	// this is where we add all of our related advice for logging
	
	// start with an @Before advice
	
//	@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
	//@Before("execution(public void add*())")
	//@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n========> Executing @Before advice");
		
		// display the method signature
		MethodSignature methodSign = (MethodSignature)theJoinPoint.getSignature();
		System.out.println("Method: " + methodSign);
		
		// display method arguments
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru args
		for (Object tempArg : args) {
			System.out.println(tempArg);
			if (tempArg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account)tempArg;
				System.out.println("Account name: " + theAccount.getName());
				System.out.println("Account level: " + theAccount.getLevel());
			}
		}
		
	}
	
	// Add new advice for @AfterReturning on the find account method
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", 
			returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		// print out the method are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n===============> Executing @AfterReturning on method: " + method);
		
		// print out the result
		System.out.println("\n===============> Result is: " + result);
		
		// post-process the data... and modify it:
		// convert the account names to upper case
		convertAccountNameToUpperCase(result);
		
		System.out.println("\n===============> Result after post-processing is: " + result);
	}
	
	// Add new advice for @AfterThowing on the find account method
	@AfterThrowing(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out the method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n===============> Executing @AfterThrowing on method: " + method);
		
		// log the exception
		System.out.println("\n===============> The exception is: " + theExc);
	}
	
	// Add new after advice for @After on the find account method
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		// print out the method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n===============> Executing @After finally on method: " + method);
	}

	private void convertAccountNameToUpperCase(List<Account> result) {

		for (Account account : result) {
			account.setName(account.getName().toUpperCase());
		}
	}
	
	
}
