package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

import javafx.scene.chart.PieChart.Data;

@Component
public class RandomFortuneService implements FortuneService {

	// create an array of strings
	private String[] data = {
			"String 1",
			"String 2",
			"String 3"
	};
	
	//create a random number generator
	private Random myRandom = new Random();
	
	@Override
	public String getFortune() {
		
		// pick a random string from the array
		int index = myRandom.nextInt(data.length);
		
		String theFortune = data[index];
		
		return theFortune;
	}

}
