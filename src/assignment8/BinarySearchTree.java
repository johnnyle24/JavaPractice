package assignment8;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Class that creates a Binary Search Tree with on a given input file whose
 * contents will be sorted based on natural ordering.
 * 
 * @author Emily Dennis, Johnny Le
 */

public class BinarySearchTree<Type extends Comparable<? super Type>> implements
		SortedSet<Type> {

	// BinarySearchTree member variables
	BSTNode root;
	int total;

	// BinarySearchTree Constructor
	public BinarySearchTree() {
		total = 0;
		root = null;
	}

	// Class for nodes
	private class BSTNode {
		Type data;
		BSTNode left, right;

		// This is the constructor for the node
		public BSTNode(Type item) {
			this.data = item;
			this.left = null;
			this.right = null;
		}

	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean add(Type item) throws NullPointerException {
		if (item == null)
			throw new NullPointerException();
		else
			return addRecursive(item, root);
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items)
			throws NullPointerException {

		// For each item of Type in the collection in items, it will attempt to
		// add it to the binary search tree. If any item is unsuccessful
		for (Type item : items) {
			if (item == null)
				throw new NullPointerException();
			else if (!addRecursive(item, root)) {
				return false;
			}
		}
		return true;
	}

	// Recursive method for add
	private boolean addRecursive(Type item, BSTNode current) {

		boolean change = false;

		if (current == null) {
			root = new BSTNode(item);
			total++;
			change = true;
		}

		// Is the item less than the data at the node?
		else if (item.compareTo(current.data) <= 0) {
			// If the next spot is empty, add the item there.
			if (current.left == null) {
				current.left = new BSTNode(item);
				total++;
				change = true;
			} else
				change = addRecursive(item, current.left);
		}

		else if (item.compareTo(current.data) > 0) {
			// If the next spot is empty, add the item there.
			if (current.right == null) {
				current.right = new BSTNode(item);
				total++;
				change = true;
			} else
				change = addRecursive(item, current.right);
		}
		return change;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		total = 0;
		root = null;
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean contains(Type item) throws NullPointerException {
		if (item == null)
			throw new NullPointerException();
		else
			return containsRecursive(item, root);
	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean containsAll(Collection<? extends Type> items)
			throws NullPointerException {

		boolean found = false;

		// For each item of Type in the collection in items, it will check if it
		// is in the binary search tree. If successful for any item, returns
		// true.
		for (Type item : items) {
			if (item == null)
				throw new NullPointerException();
			else {
				if (containsRecursive(item, root) == true) {
					found = true;
				} else
					found = false;
			}
		}
		return found;
	}

	/**
	 * Recursive method used for the contains functions. It will return true or
	 * false depending on whether an item is found or not.
	 * @param item
	 * @param current
	 * @return boolean
	 */
	private boolean containsRecursive(Type item, BSTNode current) {

		boolean found = false;

		// If the bottom of the tree is reached, return false.
		if (current == null) {
			found = false;
		}

		// If the item is equal to the value at the node, return true.
		else if (item.compareTo(current.data) == 0) {
			found = true;
		}

		// Is the item less than the data at the node?
		else if (item.compareTo(current.data) < 0) {
			found = containsRecursive(item, current.left);
		}

		else if (item.compareTo(current.data) > 0) {
			found = containsRecursive(item, current.right);
		}
		return found;
	}

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public Type first() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return findSmallest(root);
	}

	// Recursively finding the smallest element
	private Type findSmallest(BSTNode current) {
		if (current.left != null) {
			return findGreatest(current.left);
		}
		return current.data;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public Type last() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return findGreatest(root);
	}

	/**
	 * Finds the greatest value in the binary tree set.
	 * @param current
	 * @return current.data
	 */
	private Type findGreatest(BSTNode current) {
		if (current.right != null) {
			return findGreatest(current.right);
		}
		return current.data;
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item
	 *            - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually removed); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean remove(Type item) throws NullPointerException {
		if (item == null)
			throw new NullPointerException();
		else
			return removeRecursive(item, root);
	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items
	 *            - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually removed);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean removeAll(Collection<? extends Type> items)
			throws NullPointerException {

		// For each item of Type in the collection in items, it will remove it.
		// If successful for any item, returns true.
		for (Type item : items) {
			if (item == null)
				throw new NullPointerException();
			else {
				if (!removeRecursive(item, root)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method recursively removes items from the set.
	 * @param item
	 * @param current
	 * @return
	 */
	private boolean removeRecursive(Type item, BSTNode current) {

		// If the bottom of the tree is reached without finding the item, return
		// true since the item is not in the tree.
		if (current == null) {
			return true;
		}

		// If the value to be removed is the root PROBLEM HERE
		if (current.data == item) {
			if (current.left != null && current.right != null) {
				BSTNode smallest = takeSmallest(current.right);
				smallest.left = current.left;
				smallest.right = current.right;
				root = smallest;
			} else if (current.left != null) {
				root = current.left;
			} else if (current.right != null) {
				root = current.right;
			} else {
				root = null;
			}
			total--;
			return true;
		}

		if (current.left != null) {
			if (current.left.data == item) {
				if (current.left.left != null && current.left.right != null) {
					BSTNode smallest = takeSmallest(current.left.right);
					smallest.left = current.left.left;
					smallest.right = current.left.right;
					current.left = smallest;
				} else if (current.left.left != null) {
					current.left = current.left.left;
				} else if (current.left.right != null) {
					current.left = current.left.right;
				} else {
					current.left = null;
				}
				total--;
				return true;
			}
		}

		if (current.right != null) {
			if (current.right.data == item) {
				if (current.right.left != null && current.right.right != null) {
					BSTNode smallest = takeSmallest(current.right.right);
					smallest.left = current.right.left;
					smallest.right = current.right.right;
					current.right = smallest;
				} else if (current.right.left != null) {
					current.right = current.right.left;
				} else if (current.right.right != null) {
					current.right = current.right.right;
				} else {
					current.right = null;
				}
				total--;
				return true;
			}
		}

		// Nothing yet? Keep going deeper into the tree
		if (item.compareTo(current.data) < 0)
			return removeRecursive(item, current.left);

		if (item.compareTo(current.data) > 0)
			return removeRecursive(item, current.right);
		else
			return false;
	}

	/**
	 * This method locates the smallest value to the right of the current node and
	 * will remove it from there and replace the current parent node with it.
	 * @param current
	 * @return smallest
	 */
	private BSTNode takeSmallest(BSTNode current) {
		if (current.left != null) {
			BSTNode smallest = takeSmallest(current.left);
			if (current.left.left == null)
				current.left = null;
			return smallest;
		}
		return current;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return total;
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> BSTarray = new ArrayList<Type>();

		inOrder(BSTarray, root);

		return BSTarray;
	}

	/**
	 * Pre ordering of the list from the binary search tree.
	 * @param BSTarray
	 * @param current
	 */
	private void preOrder(ArrayList<Type> BSTarray, BSTNode current) {
		if (current == null)
			return;

		BSTarray.add(current.data);

		preOrder(BSTarray, current.left);
		preOrder(BSTarray, current.right);
	}

	/**
	 * Places the list in order from the binary search tree.
	 * @param BSTarray
	 * @param current
	 */
	private void inOrder(ArrayList<Type> BSTarray, BSTNode current) {
		if (current == null)
			return;

		inOrder(BSTarray, current.left);

		BSTarray.add(current.data);

		inOrder(BSTarray, current.right);
	}

	/**
	 * PostOrder version of sorting the binary search tree into a list.
	 * @param BSTarray
	 * @param current
	 */
	private void postOrder(ArrayList<Type> BSTarray, BSTNode current) {
		if (current == null)
			return;

		postOrder(BSTarray, current.left);
		postOrder(BSTarray, current.right);

		BSTarray.add(current.data);
	}

	/**
	 * Method for generating a dot file based on the BinarySearchTree.
	 * 
	 * @param filename
	 * @return String
	 */
	public void writeDot(String filename) {
		try {
			// Writes the output onto a file.
			PrintWriter result = new PrintWriter(new FileWriter(filename));

			// Sets up the dot graph and properties
			result.println("digraph  BST{");
			result.println("node shape=record");

			if (root != null)
				writeDotRecursive(root, result);

			// Close the graph
			result.println("}");
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This is the method for writing the dot
	private void writeDotRecursive(BSTNode n, PrintWriter result)
			throws Exception {
		result.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if (n.left != null) {

			// Creates the left subtree
			writeDotRecursive(n.left, result);

			// Connects n and the left subtree
			result.println(n.data + ":L -> " + n.left.data + ":D");

		}

		if (n.right != null) {

			// Creates the left subtree
			writeDotRecursive(n.right, result);

			// Connects n and the left subtree
			result.println(n.data + ":L -> " + n.right.data + ":D");

		}

	}

}