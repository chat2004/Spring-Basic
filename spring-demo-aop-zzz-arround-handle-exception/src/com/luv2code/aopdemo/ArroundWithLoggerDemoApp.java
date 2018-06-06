package com.luv2code.aopdemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;

public class ArroundWithLoggerDemoApp {

	private static Logger myLogger = Logger.getLogger(ArroundWithLoggerDemoApp.class.getName());
	
	public static void main(String[] args) {
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		myLogger.info("Calling getFortune");
		String data = trafficFortuneService.getFortune();
		
		myLogger.info("My fortune is: " + data);
		
		myLogger.info("Finished");
		// close the context
		context.close();
	}

}
