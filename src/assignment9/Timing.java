package assignment9;

public class Timing {

	public static void main(String[] args) {
		// Organize the data that comes out
		System.out.println("Iteration\tTime(ns)");

		long startTime, midpointTime, stopTime;

		// Empty loop for the thread to stabilize
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		long timesToLoop = 1000;

		for (int z = 1; z <= 35; z++) {

			startTime = System.nanoTime();

			// Loop containing the stuff we're timing
			for (long i = 0; i < timesToLoop; i++) {
				PathFinder.solveMaze("bigMaze.txt", "bigMazeOutput.txt");
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

			System.out.println(z + "\t" + averageTime);
		}
	}
}
