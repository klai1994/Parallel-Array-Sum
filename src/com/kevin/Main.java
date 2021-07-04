package com.kevin;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	final static int arraySize = 200000000;
	final static int threads = 12;

	public static void main(String[] args) {
		
		// Single thread
		int sum = 0;
		long startTime = System.nanoTime();
		int[] array = new int[arraySize];	
		Random rand = new Random();		
		
		for (int i = 0; i < arraySize; i++) {	
			array[i] = rand.nextInt(10) + 1;
			sum += array[i];
		}	
		long endTime = System.nanoTime();
		
		System.out.println("Sum: " + sum);
		System.out.println("Single thread time: " + (endTime - startTime));
		
		// Multi thread
		startTime = System.nanoTime();
		
		ExecutorService executor = Executors.newFixedThreadPool(threads); 
		Callable<Integer> threadCall = new ArrayThread(threads, arraySize);
		
		try {
			for (int i = 0; i < threads; i++) {
				sum = executor.submit(threadCall).get();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		
		System.out.println("Sum: " + sum);
		System.out.println("Multi thread time: " + (endTime - startTime));
		}
	
}
