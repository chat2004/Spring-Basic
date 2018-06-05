package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	public void addAccount(Account theAccount, boolean param) {
		System.out.println(getClass() + ": Doing my DB workd: adding Account");
	}
	
	public boolean doSomeBoolMethod(String theParam) {
		System.out.println("This is doSomeBoolMethod, do stuff....");
		return false;
	}
}
