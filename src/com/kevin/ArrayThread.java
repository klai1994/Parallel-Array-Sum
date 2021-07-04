package com.kevin;

import java.util.Random;
import java.util.concurrent.Callable;

public class ArrayThread  implements Callable{
	
	Random rand = new Random();		
	static int sum = 0;
	int multiSize;
	int[] array;
		
	public ArrayThread(int threads, int arraySize) {
		multiSize = arraySize/threads;
	}
	
	@Override
	public Integer call() {
		array = new int[multiSize];		
		for (int i = 0; i < multiSize; i++) {	
			array[i] = rand.nextInt(10) + 1;
			sum += array[i];
		}
		
		return sum;
	}

}
