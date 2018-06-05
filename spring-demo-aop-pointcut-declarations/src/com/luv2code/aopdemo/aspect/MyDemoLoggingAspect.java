package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// define a point cut
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	// this is where we add all of our related advice for logging
	
	// start with an @Before advice
	
//	@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
	//@Before("execution(public void add*())")
	//@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n========> Executing @Before advice on method addAccount() ");
	}
	
	// add new advice
	@Before("forDaoPackage()")
	public void performApiAnalytics() {
		System.out.println("\n========> Perform API Analytics method");
	}	
}
