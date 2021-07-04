package com.kevin;

import java.util.Random;

public class Main {
	
	final static int arraySize = 200000000;
	static int singleSum = 0;
	static int multiSum = 0;
	
	public static void main(String[] args) {
		
		int[] singleArr = new int[arraySize];
		int[] multiArr= new int[arraySize];
		Random rand = new Random();		
		
		for (int i = 0; i < arraySize; i++) {
			singleArr[i] = rand.nextInt(10) + 1;
			multiArr[i] = rand.nextInt(10) + 1;
		}
		
		
		long startTime = System.nanoTime();
		for (int i = 0; i < arraySize; i++) {
			singleSum += singleArr[i];
		}
		long endTime = System.nanoTime();
		
		System.out.println("Sum: " + singleSum);
		System.out.println("Single thread time: " + (endTime - startTime));
		
		startTime = System.nanoTime();
		new Thread() {
			public void run() {
				for (int i = 0; i < arraySize / 2; i++) {
					multiSum += singleArr[i];
				}
			}
		}.start();
		new Thread() {
			public void run() {
				for (int i = arraySize / 2; i < arraySize; i++) {
					multiSum += singleArr[i];
				}
			}
		}.start();
		endTime = System.nanoTime();
		
		System.out.println("Sum: " + multiSum);
		System.out.println("Multi thread time: " + (endTime - startTime));
	}
	
}
