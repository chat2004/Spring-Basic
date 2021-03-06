package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method
		theAccountDAO.addAccount(new Account(), true);
		
		theAccountDAO.doSomeBoolMethod("aa");
		
		// call the getter/setter of AccountDAO
		theAccountDAO.setName("test");
		theAccountDAO.setServiceCode("service code");
		
		String name = theAccountDAO.getName();
		String serviceCode = theAccountDAO.getServiceCode();
		
		// call the method again
		System.out.println("\ncall membership DAO add account");
		theMembershipDAO.addAccount();
		
		// close the context
		context.close();
	}

}
