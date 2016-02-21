package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.lang.Math;

/**
 * Represents a priority queue of generically-typed items. The queue is
 * implemented as a min heap. The min heap is implemented implicitly as an
 * array.
 * 
 * @author Rithie Penn, Johnny Le
 */
public class PriorityQueue<AnyType> {

	private int currentSize;

	private AnyType[] array;

	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according to their
	 * natural ordering (i.e., AnyType is expected to be Comparable) AnyType is
	 * not forced to be Comparable.
	 */
	public PriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator. Orders
	 * elements according to the input Comparator (i.e., AnyType need not be
	 * Comparable).
	 */
	public PriorityQueue(Comparator<? super AnyType> c) {
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() {
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {
		if (currentSize == 0) // if at start there are no elements
			throw new NoSuchElementException();

		return array[0];
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException {

		if (currentSize == 0) // if at start there are no elements
			throw new NoSuchElementException();

		AnyType min = array[0]; // store the minimum value for returning at end

		if (currentSize == 1) { // if there was only ever one element in heap,
								// no need to perform further
			currentSize--;
			return min;
		}

		array[0] = array[size() - 1];
		currentSize--;
		percolateDown(0);
		return min;
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x
	 *            -- the item to be inserted
	 */
	public void add(AnyType x) {

		if (size() == array.length - 1) {
			AnyType[] tempArray = array;
			array = (AnyType[]) new Object[array.length * 2];
			for (int i = 0; i < tempArray.length; i++) {
				array[i] = tempArray[i];
			}
		}

		array[size()] = x;
		percolateUp(x);
		currentSize++;

	}
	
	public void read() {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * Continually exchanges the current item with the item in the parent node
	 * until the parent's item is less than the child's.
	 * 
	 * @param x
	 */
	private void percolateUp(AnyType x) {
		
		if (size() == 1) {
			if (lessThan(array[0], x))
				exchange(0, 1);
		}

		else if (size() != 0) {
			double posn = size();
			
			if (posn > 1) {
			
				while (posn > 0 && !lessThan(x, array[(int) Math.ceil(posn / 2) - 1])) {
					exchange((int) Math.ceil(posn / 2) - 1, (int) posn);
					posn = (double) Math.ceil(posn / 2) - 1;
				}
			}
		}
	}

	/**
	 * Continually exchanges the current item with the item in the child node if
	 * the child's item is less than the parent's.
	 * 
	 * @param curr
	 */
	private void percolateDown(int curr) {

		while (curr * 2 + 1 < size()) {
			int next = curr * 2 + 1;
			if (lessThan(array[curr], array[next]) || lessThan(array[curr], array[next])) {
				if (lessThan(array[next+1], array[next]))
					exchange(curr, next);
				else
					exchange(curr, next+1);
			}
			curr = next;
		}
		
		array[size()] = null;

	}

	/**
	 * Checks if the item is less than the target item in the array.
	 * 
	 * @param x
	 * @param y
	 * @return boolean
	 */
	private boolean lessThan(AnyType x, AnyType y) {
		return (compare(y, x) < 0);
	}

	/**
	 * Exchanges the current item with the target item
	 * 
	 * @param here
	 * @param from
	 */
	private void exchange(int here, int from) {
		AnyType storage = array[here];
		array[here] = array[from];
		array[from] = storage;
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for (int i = 0; i < currentSize; i++) {
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i]
						+ "|<f2> \"]");
				if (((i * 2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1)
							+ ":f1");
				if (((i * 2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2)
							+ ":f1");
			}

			out.println("}");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by
	 * the user at construction time, or Comparable, if no Comparator was
	 * provided.
	 */
	private int compare(AnyType lhs, AnyType rhs) {
		if (cmp == null)
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe
																		// to
																		// ignore
																		// warning
		// We won't test your code on non-Comparable types if we didn't supply a
		// Comparator

		return cmp.compare(lhs, rhs);
	}

	// LEAVE IN for grading purposes
	public Object[] toArray() {
		Object[] ret = new Object[currentSize];
		for (int i = 0; i < currentSize; i++)
			ret[i] = array[i];
		return ret;
	}
}