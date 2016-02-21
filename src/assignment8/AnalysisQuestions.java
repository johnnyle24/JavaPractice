package assignment8;
 
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;
 
public class AnalysisQuestions {
 
        public static void main(String[] args) {
                Experiment4A();
        }
 
        public static void Experiment3A() {
                System.out.println("Problem Size\tTime(ns)");
 
                long startTime, midpointTime, stopTime;
 
                // First, spin computing stuff until one second has gone by.
                // This allows this thread to stabilize.
 
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }
 
                long timesToLoop = 100;
 
                BinarySearchTree<Integer> tester = new BinarySearchTree<Integer>();
 
                // This is the main loop which determines size n
                for (int z = 1000; z <= 10000; z += 100) {
 
                        // creating the ordered set
                        for (int j = 0; j < z; j++) {
                                tester.add(z);
                        }
 
                        startTime = System.nanoTime();
 
                        // main loop for timing
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
                                        tester.contains(j);
                                }
                        }
 
                        midpointTime = System.nanoTime();
 
                        // Run an empty loop to capture the cost of running the loop.
 
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
 
                                }
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
 
        public static void Experiment3B() {
                System.out.println("Problem Size\tTime(ns)");
 
                long startTime, midpointTime, stopTime;
 
                // First, spin computing stuff until one second has gone by.
                // This allows this thread to stabilize.
 
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }
 
                // Now, run the test.
 
                long timesToLoop = 100;
 
                BinarySearchTree<Integer> tester = new BinarySearchTree<Integer>();
 
                // This is the main loop which determines size n
                for (int z = 1000; z <= 10000; z += 100) {
 
                        ArrayList<Integer> arr = new ArrayList<Integer>();
 
                        // creating the basic array with ordered elements
                        for (int i = 0; i < z; i++) {
                                arr.add(i, i);
                        }
 
                        // randomly swapping the elements
                        for (int j = 0; j < z; j++) {
                                int rand = new Random().nextInt(z);
                                int temp = arr.get(j);
                                arr.set(j, arr.get(rand));
                                arr.set(rand, temp);
                        }
 
                        // clear out the old elements, add the new ones
                        tester.clear();
                        tester.addAll(arr);
 
                        startTime = System.nanoTime();
 
                        // main loop for timing
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
                                        tester.contains(j);
                                }
                        }
 
                        midpointTime = System.nanoTime();
 
                        // Run an empty loop to capture the cost of running the loop.
 
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
 
                                }
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
 
        public static void Experiment4A() {
                System.out.println("TreeSet: Problem Size\tTime(ns)");
 
                long startTime, midpointTime, stopTime;
 
                // First, spin computing stuff until one second has gone by.
                // This allows this thread to stabilize.
 
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }
 
                // Now, run the test.
 
                long timesToLoop = 100;
 
                // Collections to use
                TreeSet<Integer> treeTester = new TreeSet<Integer>();
                BinarySearchTree<Integer> binaryTester = new BinarySearchTree<Integer>();
                ArrayList<Integer> arr = new ArrayList<Integer>();
 
                // This is the main loop which determines size n
                for (int z = 1000; z <= 10000; z += 100) {
 
                        // create the basic array with ordered elements
                        for (int i = 0; i < z; i++) {
                                arr.add(i, i);
                        }
 
                        // randomly swap the elements of the ordered array
                        for (int j = 0; j < z; j++) {
                                int rand = new Random().nextInt(z);
                                int temp = arr.get(j);
                                arr.set(j, arr.get(rand));
                                arr.set(rand, temp);
                        }
 
                        // clear out the collections
                        treeTester.clear();
                        binaryTester.clear();
 
                        startTime = System.nanoTime();
 
                        // main loop for timing
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
                                        treeTester.add(arr.get(j));
                                }
                        }
                        midpointTime = System.nanoTime();
 
                        // empty loop
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
 
                                }
                        }
                        stopTime = System.nanoTime();
                        double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                                        / timesToLoop;
 
                        System.out.print(z + "\t" + averageTime);
 
                        startTime = System.nanoTime();
 
                        // main loop for timing
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
                                        binaryTester.add(j);
                                }
                        }
 
                        midpointTime = System.nanoTime();
 
                        // Run an empty loop to capture the cost of running the loop.
 
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
 
                                }
                        }
 
                        stopTime = System.nanoTime();
 
                        // Compute the time, subtract the cost of running the loop
                        // from the cost of running the loop and computing square roots.
                        // Average it over the number of runs.
 
                        averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                                        / timesToLoop;
 
                        System.out.print("\t" + averageTime + "\n");
                }
        }
 
        public static void Experiment4B() {
                System.out.println("TreeSet: Problem Size\tTime(ns)");
 
                long startTime, midpointTime, stopTime;
 
                // First, spin computing stuff until one second has gone by.
                // This allows this thread to stabilize.
 
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }
 
                // Now, run the test.
 
                long timesToLoop = 100;
 
                // Several collections to use
                TreeSet<Integer> treeTester = new TreeSet<Integer>();
                BinarySearchTree<Integer> binaryTester = new BinarySearchTree<Integer>();
                ArrayList<Integer> arr = new ArrayList<Integer>();
 
                // This is the main loop which determines size n
                for (int z = 1000; z <= 10000; z += 100) {
 
                        // Make the initial array containing all of the elements
                        for (int i = 0; i < z; i++) {
                                arr.add(i, i);
                        }
 
                        // This loop takes the array from above and switches all of the
                        // elements randomly
                        for (int j = 0; j < z; j++) {
                                int rand = new Random().nextInt(z);
                                int temp = arr.get(j);
                                arr.set(j, arr.get(rand));
                                arr.set(rand, temp);
                        }
 
                        // Clear everything and then add the current data to the array
                        treeTester.clear();
                        treeTester.addAll(arr);
                        binaryTester.clear();
                        binaryTester.addAll(arr);
 
                        startTime = System.nanoTime();
 
                        // main loop for timing
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
                                        treeTester.contains(j);
                                }
                        }
                        midpointTime = System.nanoTime();
 
                        // Empty loop
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
 
                                }
                        }
 
                        stopTime = System.nanoTime();
 
                        double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                                        / timesToLoop;
 
                        System.out.print(z + "\t" + averageTime);
 
                        startTime = System.nanoTime();
 
                        // main loop for timing
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
                                        binaryTester.contains(j);
                                }
                        }
 
                        midpointTime = System.nanoTime();
 
                        // Run an empty loop to capture the cost of running the loop.
 
                        for (long i = 0; i < timesToLoop; i++) {
                                for (int j = 0; j < z; j++) {
 
                                }
                        }
 
                        stopTime = System.nanoTime();
 
                        // Compute the time, subtract the cost of running the loop
                        // from the cost of running the loop and computing square roots.
                        // Average it over the number of runs.
 
                        averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                                        / timesToLoop;
 
                        System.out.print("\t" + averageTime + "\n");
                }
        }
}