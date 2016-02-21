package assignment10;

import java.util.Collection;

/**
 * Class for creating a hash table that uses quadratic probing to resolve collisions.
 * @author Rithie Penn, Johnny Le
 *
 */
public class QuadProbeHashTable implements Set<String> {

	private String[] mHashTable;
	private boolean[] mDeleted; // to determine whether something is occupying space
	private HashFunctor mfunctor;
	
	private int collision = 0;
	private int total = 0;
	
	/**
	 * The constructor for the quadratic probing hash table. Accepts the capacity for the size of the table
	 * and the functor for determining hashes. It will also form an array that states if a given index
	 * is deleted or not.
	 * @param capacity, functor
	 * @param functor
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {

		mHashTable = new String[capacity];

		mDeleted = new boolean[capacity]; // initializing as all spaces empty
		for (int i = 0; i < capacity; i++) {
			mDeleted[i] = true;
		}

		mfunctor = functor;

	}

	/**
	 * Adds the given string to the hash table and will rehash the entire table if
	 * the load factor, the fraction filled, is greater than or equal to half of the table's length.
	 * 
	 * @param item
	 * @return boolean change
	 */
	@Override
	public boolean add(String item) {
		boolean change = false;
		int hashcode = mfunctor.hash(item);
		
		double lengths = mHashTable.length;
		
		int index = hashcode % mHashTable.length;
		int qProbeInt = 0;
		boolean inserting = true;
		
		//determine current loadFactor
		double loadFactor = size() / lengths;

		while (inserting && loadFactor < 0.5 && (!contains(item))) {
			if (mDeleted[index] == true) {
				mHashTable[index] = item;
				mDeleted[index] = false;
				change = true;
				total++;
				inserting = false;
			}

			else {
				collision++;
				qProbeInt++;
				index = Math.abs((hashcode + (qProbeInt * qProbeInt)) % mHashTable.length);
			}
		}

		// Rehash here
		if (loadFactor >= 0.5) { //check if loadFactor is over
			int nPrime = mHashTable.length*2 + 1;
			while (nPrime % 2 == 0 || nPrime % 3 == 0 || nPrime % 5 == 0) {
				nPrime++;
			}
			//create temporary storage
			String[] hashTableTemp = mHashTable;
			boolean[] deletedTemp = mDeleted;
			collision = 0;
			
			mHashTable = new String[nPrime];
			mDeleted = new boolean[nPrime];
			for (int i = 0; i < nPrime; i++) {
				mDeleted[i] = true;
			}
			
			for (int i = 0; i < hashTableTemp.length; i++) {
				if (!deletedTemp[i]) {
					if (add(hashTableTemp[i])) {
						change = true;
					}
				}
			}
		}
		return change;
	}

	/**
	 * This method will take all items in the collection and attempt to insert it. If
	 * any item was successfully inserted, the method will return true. If no items were
	 * successfully inserted, the method will return false.
	 * @param items
	 * @return change
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
	 * This method clears the hash table.
	 */
	@Override
	public void clear() {
		
		total = 0;

		mHashTable = new String[mHashTable.length];

		mDeleted = new boolean[mHashTable.length]; // initializing as all spaces
													// empty
		for (int i = 0; i < mHashTable.length; i++) {
			mDeleted[i] = true;
		}

	}

	/**
	 * This method will determine if the item is in the hash table.
	 * @param item
	 * @return boolean
	 */
	@Override
	public boolean contains(String item) {
		int hashcode = mfunctor.hash(item);

		int index = hashcode % mHashTable.length;
		int qProbeInt = 0;
		
		for (int i = 1; i < mHashTable.length/2; i++){
			if(mHashTable[index] == item)
				return true;
			else 
				qProbeInt++;
				index = Math.abs((hashcode + (qProbeInt * qProbeInt)) % mHashTable.length);
		}
		return false;
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
			else {
				return false;
			}
		}
		return change;
	}

	/**
	 * This method checks if the hashtable is empty.
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
	 * This method will return the total number of items within the hashtable.
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
