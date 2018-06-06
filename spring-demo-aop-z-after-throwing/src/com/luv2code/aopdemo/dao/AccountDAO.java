package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;
	
	
	public String getName() {
		System.out.println(getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": in setServiceCode()");
		this.serviceCode = serviceCode;
	}

	public void addAccount(Account theAccount, boolean param) {
		System.out.println(getClass() + ": Doing my DB workd: adding Account");
	}
	
	public boolean doSomeBoolMethod(String theParam) {
		System.out.println("This is doSomeBoolMethod, do stuff....");
		return false;
	}
	
	public List<Account> findAccounts(boolean tripWire) {
		// simulate exception
		if (tripWire) {
			throw new RuntimeException("The test exception!!!!");
		}
		
		List<Account> myAccounts = new ArrayList<>();
		// create sample accounts
		Account acc1 = new Account("Vu", "beginner");
		Account acc2 = new Account("Huynh", "master");
		myAccounts.add(acc1);
		myAccounts.add(acc2);
		
		return myAccounts;
	}
}
