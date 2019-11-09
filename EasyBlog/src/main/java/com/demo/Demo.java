package com.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Demo {
	public static void main(String[] args) {
		Sum(100);
	}

	private static void Sum(int sum) {
		long start = System.currentTimeMillis();
		System.out.println("start:"+start);
		int count = 0;
		List<Integer> list = new LinkedList<Integer>();
		//List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= sum; i++) {
			if (i % 6 == 0 || i % 10 == 6) {
				list.add(i);
				count += i;
			}
		}
		System.out.println(list);
		System.out.println(count);
		long end = System.currentTimeMillis();
		System.out.println("end:"+end);
		System.out.println("time:"+(end-start));
	}
}
