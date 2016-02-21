package assignment11;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is a timing class for the priority queue consisting of min heaps.
 * 
 * @author Johnny Le
 */

public class Timing {

	public static void main(String[] args) {
		// Organize the data that comes out
		System.out.println("Size\tTime(ns)");

		long startTime, midpointTime, stopTime;

		// Empty loop for the thread to stabilize
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		long timesToLoop = 10000;

		PriorityQueue<Integer> pQueue;

		for (int z = 1024; z <= 1000000; z *= 2) {

			ArrayList<Integer> collectTest = generateAverageCase(z);
			
			startTime = System.nanoTime();

			// Loop containing the stuff we're timing
			for (long i = 0; i < timesToLoop; i++) {
				pQueue = new PriorityQueue<Integer>();
				for (int h = 0; h < z; h++) {
					pQueue.add(collectTest.get(h));// Add it
				}
				
				//pQueue.findMin();
				//pQueue.deleteMin();
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (long i = 0; i < timesToLoop; i++) {
				pQueue = new PriorityQueue<Integer>();
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) / timesToLoop / z);

			// collisionTotal = qHashG.collisions();

			System.out.println(z + "\t" + averageTime);
		}
	}

	/**
	 * This method creates the best case which in this method is a list of
	 * integers from 1 to size in consecutive ascending order.
	 *
	 * @param size
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		ArrayList<Integer> bestCase = new ArrayList<Integer>(size);
		for (int i = 0; i < size; i++) {
			bestCase.add(i, i + 1);
		}
		return bestCase;
	}

	/**
	 * This method generates the average case by taking the best case and for
	 * every index value exchanges them with other randomly selected indices.
	 *
	 * @param size
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {
		ArrayList<Integer> averageCase = new ArrayList<Integer>(size);
		ArrayList<Integer> bestCase = generateBestCase(size);
		Random randomizer = new Random();
		for (int i = 0; i < size - 1; i++) {
			exchange(bestCase, i, (int) randomizer.nextInt(size));
		}
		averageCase = bestCase;
		return averageCase;
	}

	/**
	 * This method sets the left value to the right and vice versa.
	 *
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static <T> void exchange(ArrayList<T> arr, int left, int right) {
		T temp = arr.get(left);
		arr.set(left, arr.get(right));
		arr.set(right, temp);
	}
}
