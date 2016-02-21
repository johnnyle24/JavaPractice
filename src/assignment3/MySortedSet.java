package assignment3;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * A set that creates an array based sorting variables as they are inserted. It
 * is sorted based on natural ordering with use of a comparable or with a preset
 * ordering with a comparator. In addition, it has the ability to prevent the
 * addition of duplicates, utilize a binary search, check for specific items or
 * entire collections, and shift variables within the set. These traits are
 * supplemented with an iterator, a record of the number of items within the
 * set, and methods for removing/adding/clearing single or groups of variables.
 * 
 * @author Tony Chow and Johnny Le
 *
 * @param <E>
 */

public class MySortedSet<E> implements SortedSet<E> {

	int size;
	@SuppressWarnings("unchecked")
	E[] array = (E[]) new Object[8];
	E[] temp;

	Comparator<? super E> c;

	/**
	 * A constructor that is utilized when MySortedSet isn't given a comparator.
	 * Set this.c to null.
	 */

	public MySortedSet() {
		// sets the comparator c to null;
		this.c = null;
	}

	/**
	 * A constructor that is utilized when MySortedSet is given a comparator.
	 * 
	 * @param comparator
	 *            c constructor when MySortedSet is given a comparator
	 */

	public MySortedSet(Comparator<? super E> c) {
		// returns the comparator passes in as parameter
		this.c = c;
	}

	/**
	 * @return null if it has a comparable and c, the comparator, otherwise
	 */

	@Override
	public Comparator<? super E> comparator() {
		// TODO Auto-generated method stub
		if (this.c != null) {
			return c;
		} else
			return c;
	}

	/**
	 * @return returns the first element in the generic array
	 */

	@Override
	public E first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (size > 0) {
			return array[0];
		}
		return null;
	}

	/**
	 * @return returns the last element in the generic array
	 */

	@Override
	public E last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (size > 0) {
			return array[size - 1];
		}
		return null;
	}

	/**
	 * @param Generic
	 *            o
	 * @return a boolean value (false) depending on if E o is already within the
	 *         set or (true) if there are no duplicates and the variable was
	 *         succesfully added.
	 */

	@Override
	public boolean add(E o) {
		// TODO Auto-generated method stub
		if (size == 0) {
			array[0] = o;
			size++;
			return true;
		}
		if (binarySearch(array, o, c) == true) {
			return false;
		}
		if (size == array.length) {
			doubleArraySize(array);
			for (int i = size; i >= binarySearchIndex(o); i--) {
				array[i + 1] = array[i];
			}
			array[binarySearchIndex(o)] = o;
			size++;
			return true;
		}
		if (size < array.length) {
			int index = binarySearchIndex(o);
			for (int i = size - 1; i >= index; i--) {
				array[i + 1] = array[i];
			}
			array[index] = o;
			size++;
			return true;
		}
		return false;
	}

	/**
	 * @param Collection
	 *            c
	 * @return a boolean value depending on if it added anything in(false) the
	 *         array or not(true) and adds objects in c to the array
	 */

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		boolean change = false;
		for (E o : c) {
			if (add(o) == true) {
				change = true;
			}
		}
		return change;
	}

	/**
	 * @return clears the array by setting everything to null
	 */

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = size; i > 0; i--) {
			array[i] = null;
			size--;
		}
	}

	/**
	 * @param Object
	 *            o
	 * @return a true if o casted to E is contained in the array and false if
	 *         not
	 */

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return binarySearch(array, (E) o, this.c);
	}

	/**
	 * @param Collection
	 *            c
	 * @return a true if all o casted to E in the collection is contained in the
	 *         array and false otherwise
	 */

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		for (Object o : c) {
			contains(o);
			if (contains(o) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return a true array is empty and false otherwise
	 */

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (size > 0) {
			return false;
		}
		return true;
	}

	/**
	 * @return null
	 */

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param Object
	 *            o
	 * @return true if is in the array and removed, otherwise return false
	 */

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		E newO = (E) o;
		int index = binarySearchIndex(newO);
		if (binarySearch(array, newO, this.c) == true) {
			for (int i = index; i < size; i++) {
				array[i] = array[i + 1];
			}
			array[size] = null;
			size--;
			return true;
		}
		return false;
	}

	/**
	 * @param Collection
	 *            c
	 * @return true if it removed any element x in the collection c from the
	 *         array, false otherwise
	 */

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		boolean change = false;
		for (Object o : c) {
			if (contains(o) == true) {
				remove(o);
				change = true;
			}
		}
		return change;
	}

	/**
	 * @return size if this method is called.
	 */

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * @return an array of size "size" without the null values
	 */

	@Override
	public E[] toArray() {
		// TODO Auto-generated method stub
		E[] newArray = (E[]) new Object[size];
		for (int i = 0; i < size; i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	/**
	 * @param Type
	 *            e
	 * @return index denoting the location of insertion for E e.
	 */

	@SuppressWarnings("unchecked")
	public int binarySearchIndex(E e) {
		int start = 0;
		int end = size - 1;
		int mid = 0;
		// comparable
		Comparable<E> comparableE = (Comparable<E>) e;
		if (c == null) {
			while (start <= end) {
				mid = (end + start) / 2;
				if (comparableE.compareTo(array[mid]) < 0) {
					end = mid - 1;
					continue;
				}
				if (comparableE.compareTo(array[mid]) > 0) {
					start = mid + 1;
					continue;
				}
				if (comparableE.compareTo(array[mid]) == 0) {
					return mid;
				}
			}
			if (comparableE.compareTo(array[mid]) > 0)
				return mid + 1;
			return mid;
		}
		// comparator
		while (start <= end) {
			mid = (end + start) / 2;
			if (c.compare(array[mid], e) > 0) {
				end = mid - 1;
				continue;
			}
			if (c.compare(array[mid], e) < 0) {
				start = mid + 1;
				continue;
			}
			if (c.compare(array[mid], e) == 0) {
				return mid;
			}
		}
		if (c.compare(array[mid], e) < 0) {
			return mid + 1;
		}
		return mid;
	}

	@SuppressWarnings("unchecked")
	public boolean binarySearch(E[] genericArray, E e, Comparator<? super E> c) {
		E[] library;
		library = genericArray;
		int start = 0;
		int end = size - 1;
		int mid = 0;
		// comparable
		Comparable comparableE = (Comparable) e;
		if (c == null) {
			while (start <= end) {
				mid = (end + start) / 2;
				if (comparableE.compareTo(genericArray[mid]) < 0) {
					end = mid - 1;
					continue;
				}
				if (comparableE.compareTo(genericArray[mid]) > 0) {
					start = mid + 1;
					continue;
				}
				if (comparableE.compareTo(genericArray[mid]) == 0) {
					return true;
				}
			}
			return false;
		}
		// comparator
		while (start <= end) {
			mid = (end + start) / 2;
			if (c.compare(genericArray[mid], e) > 0) {
				end = mid - 1;
				continue;
			}
			if (c.compare(genericArray[mid], e) < 0) {
				start = mid + 1;
				continue;
			}
			if (c.compare(genericArray[mid], e) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param E
	 *            [] e
	 * @return an array with all the elements in the correct indexes except the
	 *         array.length is doubled
	 */

	@SuppressWarnings("unchecked")
	public E[] doubleArraySize(E[] e) {
		if (size == e.length) {
			temp = (E[]) new Object[e.length * 2];
			for (int i = 0; i < e.length - 1; i++) {
				temp[i] = e[i];
			}
			e = temp;
		}
		return array;
	}

	/**
	 * @param E
	 *            [] e
	 * @return a halved array with all the elements in the correct indexes.
	 *         Array.length is halved in this case
	 */

	@SuppressWarnings("unchecked")
	public E[] halveArraySize(E[] e) {
		if (size <= e.length / 2) {
			temp = (E[]) new Object[e.length / 2];
			for (int i = 0; i < size; i++) {
				temp[i] = e[i];
			}
			e = temp;
		}
		return null;
	}

	// SortedSetIterator class
	private class SortedSetIterator implements Iterator<E> {

		int i = 0;

		/**
		 * @return a true if the array has another (E) object that isn't null
		 *         and returns false otherwise
		 */

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (i < array.length) {
				return true;
			}
			return false;
		}

		/**
		 * @return E an object at index i+1, the next thing in the array
		 */

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if (i < array.length) {
				i++;
				return array[i];
			}
			throw new NoSuchElementException();
		}

		/**
		 * @return nothing but removes value at index i from the array
		 */

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			for (int j = i; j < array.length; j++) {
				array[j] = array[j + 1];
			}
			array[array.length] = null;
			size--;
		}
	}
}