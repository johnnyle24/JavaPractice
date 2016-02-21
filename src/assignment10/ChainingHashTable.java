package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Class that creates a hash table made up of linked lists.
 * @author Johnny Le, Rithie Penn
 *
 */
public class ChainingHashTable implements Set<String> {


	private LinkedList<String>[] mHashTable;
	private HashFunctor mfunctor;

	private int collision = 0;
	private int total = 0;

	/**
	 * 
	 * Constructs the HashTable from an array of size capacity
	 * and creates a linked list at each array index.
	 * in the hash functor
	 * @param capacity
	 * @param functor
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {

		mHashTable = (LinkedList<String>[]) new LinkedList[capacity];

		mfunctor = functor;
	}

	/**
	 * adds to hash table and returns true if add is successful
	 * 
	 * @param item
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(String item) {
		boolean change = false;
		int hashcode = mfunctor.hash(item);

		int index = hashcode;
		
		
		//initialize the linked list at index
		if (mHashTable[index % mHashTable.length] == null)
			mHashTable[index % mHashTable.length] = new LinkedList<String>();
		
		if (!contains(item)) {
			mHashTable[index % mHashTable.length].add(item);
			change = true;
			total++;
		}
		
		if (contains(item)) {
			collision++;
		}

		//determine the loadFactor
		int loadFactor = 0;
		for (int i=0; i < mHashTable.length; i++) {
			if (mHashTable[i] != null) //check if there is linked list at point
				loadFactor += mHashTable[i].size();
		}

		loadFactor = loadFactor/mHashTable.length;
		
		// Check and complete rehashing
		if (loadFactor >= (mHashTable.length)) {
			int nPrime = mHashTable.length + 1;
			while (nPrime % 2 == 0 || nPrime % 3 == 0 || nPrime % 5 == 0) {
				nPrime++;
			}
			
			LinkedList<String>[] hashTableTemp = mHashTable;
			mHashTable = (LinkedList<String>[]) new LinkedList[nPrime];
			
			for (int i = 0; i < hashTableTemp.length; i++) {
				for (int j = 0; j < hashTableTemp[i].size(); j++) {
					add(hashTableTemp[i].get(j));
				}
			}	
		}
		
		return change;
	}

	/**
	 * adds all items to the collection, and returns true if any are added
	 * @param items
	 * @return boolean
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean change = false;
		for (String item : items) {
			if (add(item) == true) {
				change = true;
			}
		}
		return change;
	}

	/**
	 * 
	 * Removes all items from the hashtable.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		
		total = 0;
		mHashTable = (LinkedList<String>[]) new LinkedList[mHashTable.length];

	}

	/**
	 * checks for item at its hash
	 * @param item
	 * @return boolean
	 */
	@Override
	public boolean contains(String item) {
		int hashcode = mfunctor.hash(item);

		int index = hashcode;
		
		//ensures no null pointer by checking if there is data at index
		//and after check, checks for item
		if(mHashTable[index % mHashTable.length] != null && mHashTable[index % mHashTable.length].contains(item)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	/**
	 * This method will determine if all items in the collection have an item equal to
	 * it within the hash table. If for any item that isn't true, it will return false.
	 * @param items
	 * @return boolean
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean change = false;
		for (String item : items) {
			if (contains(item) == true) {
				change = true;
			}
			else { //if at any point does not contain, return false
				return false;
			}
		}
		return change;
	}

	/**
	 * This method will state whether or not the hash table is empty.
	 * @return boolean
	 */
	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the number of items in the hash table.
	 * @return total
	 */
	@Override
	public int size() {
		return total;
	}
	
	/**
	 * Returns the number collisions that have occurred.
	 * @return int collision
	 */
	public int collisions() {
		return collision;
	}

}
