package assignment10;

import java.util.ArrayList;
import java.util.Random;

public class Timing {

	public static void main(String[] args) {
		// Organize the data that comes out
		System.out.println("Hash Table Size\tTime(ns)");

		long startTime, midpointTime, stopTime;
		
		int collisionTotal;
		
		GoodHashFunctor gFunctor = new GoodHashFunctor();
		MediocreHashFunctor mFunctor = new MediocreHashFunctor();
		BadHashFunctor bFunctor = new BadHashFunctor();
		
		int lCap = 1031;
		
		int mCap = 20029;
		
		int hCap = 74017;
		
		QuadProbeHashTable qHashG = new QuadProbeHashTable(lCap, gFunctor);
		
		QuadProbeHashTable qHashM = new QuadProbeHashTable(mCap, mFunctor);
		
		QuadProbeHashTable qHashB = new QuadProbeHashTable(mCap, bFunctor);
		
		ChainingHashTable cHashG = new ChainingHashTable(lCap, gFunctor);
		
		ChainingHashTable cHashM = new ChainingHashTable(mCap, mFunctor);
		
		ChainingHashTable cHashB = new ChainingHashTable(mCap, bFunctor);

		// Empty loop for the thread to stabilize
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
		long timesToLoop = 1000;

		for (int z = 1; z <= 10000; z *= 2) {
			
			ArrayList<String> collectTest = generateAverageCase(z);
			
			qHashG.clear(); //Clear it

			startTime = System.nanoTime();

			// Loop containing the stuff we're timing
			for (long i = 0; i < timesToLoop; i++) {
				qHashG.addAll(collectTest); // Add it
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (long i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			
			//collisionTotal = qHashG.collisions();

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
	public static ArrayList<String> generateBestCase(int size) {
		ArrayList<String> bestCase = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			bestCase.add(i, Integer.toString(i + 1));
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
	public static ArrayList<String> generateAverageCase(int size) {
		ArrayList<String> averageCase = new ArrayList<String>(size);
		ArrayList<String> bestCase = generateBestCase(size);
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
