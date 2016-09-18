package com.embracesource.scala.interactingwithjava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.embracesource.scala.interactingwithjava.TestInteractingWithJava.Thrower;

public class JavaExamples {
	public static List<Integer> getNumbers() {
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		return numbers;
	}

	public static Map<String, String> getPeeps() {
		Map<String, String> peeps = new HashMap<String, String>();
		peeps.put("captain", "Kirk");
		peeps.put("doctor", "McCoy");
		return peeps;
	}

	public static int sum(List<Integer> list) {
		int sum = 0;
		for (int i : list) {
			sum = sum + i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Thrower t = new Thrower();
		try {
			t.exceptionThrower();
		} catch (Exception e) {
			System.err.println("Caught the exception.");
			e.printStackTrace();
		}
	}
}
