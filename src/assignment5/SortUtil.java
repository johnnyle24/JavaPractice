package assignment5;
 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
 
public class SortUtil {
 
        /**
         * This method invokes the insertion sort when called.
         * @param arr
         * @param comp
         *
         */
        public static <T> void insertionSort(ArrayList<T> arr,
                        Comparator<? super T> comp) {
                for (int i = 1; i < arr.size(); i++) {
                        T key = (T) arr.get(i);
                        int j = i - 1;
                        while ((j > -1) && (comp.compare(arr.get(j), (T) key) > 0)) {
                                arr.set(j + 1, arr.get(j));
                                j--;
                        }
                        arr.set(j + 1, key);
                }
        }
 
	    /**
	     * This method is the driver for the mergesort. It creates the temp arraylist used in the
	     * merge and calls the recursive method.
	     * @param arr
	     * @param comp
	     */
        @SuppressWarnings("unchecked")
        public static <T> void mergesort(ArrayList<T> arr, int insertionSortThreshold,
                        Comparator<? super T> comp) {
                // handling boundary cases
                if (arr.size() == 0) {
                        return;
                }
                ArrayList<T> temp = new ArrayList<T>(arr.size());
                for (Integer i = 0; i < arr.size(); i++) {
                        temp.add((T) i);
                }
                mergesortRecursive(arr, 0, arr.size() - 1, insertionSortThreshold,comp, temp);
        }
 
        /**
         * This method will call insertion sort if the size of the selected portion of the array is
         * within a certain size. If the end is greater than the start then it will call itself twice
         * followed by the merge method.
         * @param arr
         * @param start
         * @param end
         * @param comp
         * @param temp
         */
        public static <T> void mergesortRecursive(ArrayList<T> arr, int start,
                        int end, int insertionSortThreshold, Comparator<? super T> comp,
                        ArrayList<T> temp) {
                // runs insertion sort if array size is 10 or less
                if ((end - start + 1) < insertionSortThreshold) {
                        insertionSort(arr, comp);
                        return;
                }
                if (end > start) {
                        int mid = (end + start) / 2;
                        // runs the recursive call to break up into smaller arrays
                        mergesortRecursive(arr, start, mid, insertionSortThreshold, comp, temp);
                        mergesortRecursive(arr, (mid + 1), end, insertionSortThreshold, comp, temp);
                        // merges the two sub arrays together
                        merge(arr, start, mid + 1, end, comp, temp);
                }
        }
 
        /**
         * This method utilizes the temporary ArrayList passed in. For the selected values, it will run comparisons
         * based on which value is lower and sort that to left accordingly.
         *
         * Once done, it will copy the temporary list over to the actual ArrayList being sorted.
         * @param arr
         * @param start
         * @param mid
         * @param end
         * @param comp
         * @param temp
         */
        public static <T> void merge(ArrayList<T> arr, int start, int mid, int end,
                        Comparator<? super T> c, ArrayList<T> temp) {
                int firstend = mid - 1;
                int temppos = start;
                // compares the values of the indices of the sub array lists starting
                // from the first and then swaps them
                while ((start <= (firstend)) && (mid <= end)) {
                        if (c.compare(arr.get(start), arr.get(mid)) < 0) {
                                temp.set(temppos, arr.get(start));
                                temppos++;
                                start++;
                        } else {
                                temp.set(temppos, arr.get(mid));
                                temppos++;
                                mid++;
                        }
                }
                // places everything else in
                while (start <= firstend) {
                        temp.set(temppos, arr.get(start));
                        temppos++;
                        start++;
                }
                while (mid <= end) {
                        temp.set(temppos, arr.get(mid));
                        temppos++;
                        mid++;
                }
                // copies the values over to the original arraylist
                for (int i = 0; i < temppos; i++) {
                        arr.set(i, temp.get(i));
                }
        }
 
        /**
         * This is the quicksort driver method. It sets the bounds of the ArrayList and calls the
         * recursive quicksort function.
         * @param arr
         * @param comp
         */
        public static <T> void quicksort(ArrayList<T> arr,
                        Comparator<? super T> comp) {
                int left = 0;
                int right = arr.size() - 1;
                quicksortRecursive(arr, left, right, comp);
        }
 
        /**
         * This method will end once the left bound is greater than or equal to the right bound.
         * It sets the pivot value and partitions the array. Once done, it will call itself and
         * essentially split itself in half.
         * @param arr
         * @param left
         * @param right
         * @param comp
         */
        public static <T> void quicksortRecursive(ArrayList<T> arr, int left,
                        int right, Comparator<? super T> comp) {
                if (left >= right)
                	return;
 
                // places pivot in the correct position in the array
                int partition = partition(arr, left, right, comp);
 
                // run this on sub arrays
                if (left < partition - 1)
                quicksortRecursive(arr, left, partition - 1, comp);
                
                if (right > partition)
                quicksortRecursive(arr, partition, right, comp);
 
        }
 
        /**
         * This method compares the left and right side of the array.
         * @param arr
         * @param left
         * @param right
         * @param pivot
         * @param comp
         * @return leftCursor
         */
        public static <T> int partition(ArrayList<T> arr, int left, int right,
                        Comparator<? super T> comp) {
        	
                int leftCursor = left;
                int rightCursor = right;

            	T pivot = arr.get(right - 1);
 
                // puts the pivot in the correct position by moving all the values
                // less than the pivot to the left and greater than values to the right
                while (leftCursor <= rightCursor) {
                        // finds an index where the value is greater than pivot if any exist
                        while (comp.compare(arr.get(leftCursor), pivot) < 0)
                                leftCursor++;
                        // finds an index where value is less than pivot if any exist
                        while (comp.compare(arr.get(rightCursor), pivot) > 0)
                                rightCursor--;
                        // exchanges the two values from the given index
                        if (leftCursor <= rightCursor) {
                                exchange(arr, leftCursor, rightCursor);
                                leftCursor++;
                                rightCursor--;
                        }
                }
            	return leftCursor;
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
 
        /**
         * This method creates the best case which in this method is a list of integers from
         * 1 to size in consecutive ascending order.
         *
         * @param size
         * @return ArrayList<Integer>
         */
        public static ArrayList<Integer> generateBestCase(int size) {
                ArrayList<Integer> bestCase = new ArrayList<Integer>(size);
                for (int i = 0; i < size; i++) {
                        bestCase.add(i, (i + 1));
                }
                return bestCase;
        }
 
        /**
         * This method generates the average case by taking the best case and for every index value
         * exchanges them with other randomly selected indices.
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
         * This method generates worst case by forming an ArrayList with the integers 1 to size in reverse
         * order.
         *
         * @param size
         * @return ArrayList<Integer>
         */
        public static ArrayList<Integer> generateWorstCase(int size) {
                ArrayList<Integer> worstCase = new ArrayList<Integer>(size);
                int j = 0;
                for (int i = size; i > 0; i--) {
                        worstCase.add(j, i);
                        j++;
                }
                return worstCase;
        }
        
        /**
         * obtains the median of three values
         *
         * @param arr
         * @param c
         * @return int
         */
        public static <T> int median(ArrayList<T> arr, int left, int right,
                        Comparator<? super T> c) {
                int mid = (right + left) / 2;
                if (arr.size() >= 3) {
                        if ((c.compare(arr.get(left), arr.get(mid)) >= 0)
                                        && (c.compare(arr.get(right), arr.get(left)) >= 0)) {
                                return left;
                        }
                        if ((c.compare(arr.get(mid), arr.get(left)) >= 0)
                                        && (c.compare(arr.get(right), arr.get(mid)) >= 0)) {
                                return mid;
                        } else
                                return right;
                } else
                        return left;
        }
        
        
        public static void main(String [] args) {
                ArrayList<Integer> averageCase = generateAverageCase(500000);
                quicksort(averageCase, new Comparator<Integer>() {
 
                        @Override
                        public int compare(Integer o1, Integer o2) {
                                return o1.compareTo(o2);
                        }
                });
                System.out.println(averageCase);
        }
}