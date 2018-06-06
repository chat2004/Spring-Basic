package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declaration
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	// combine the Pointcut
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		// display method we are calling
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("========> in @Before: calling method: " + method);
		
		// display argument of the method
		Object[] args = theJoinPoint.getArgs();
		for (Object arg : args) {
			myLogger.info("=========> argument: " + arg);
		}
	}
	
	// add @AfterReturning advice
	@AfterReturning(pointcut="forAppFlow()",
			returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		// display method we are calling
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("========> in @AfterReturning: from method: " + method);
		
		// display data returned
		myLogger.info("========> The result: " + theResult);
	}
	
}