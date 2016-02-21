package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MySortedSetTest<E> {
	
	private String a1, a2, a3, a4;
	
	private Integer n1, n2, n3, n4;
	
	MySortedSet<String> set = new MySortedSet<String>();
	Collection<String> c = new ArrayList<String>();

	MySortedSet<Integer> numset = new MySortedSet<Integer>();
	Collection<Integer> numc = new ArrayList<Integer>();
	
	MySortedSet<Integer> rnumset = new MySortedSet<Integer>(new MyComp());
	Collection<Integer> rnumc = new ArrayList<Integer>();
	
	@Before
	public void setUp() throws Exception {
		
		a1 = "Clooney";
		a2 = "Timberlake";
		a3 = "Afleck";
		a4 = "Chow";
		
		n1 = 1;
		n2 = 21;
		n3 = 13;
		n4 = 14;
		
		}

	@After
	public void tearDown() throws Exception {
	}
	
	// Checks if true is returned when a string or integer is added to a set
	
	@Test
	public void testAdd() {
		
		assertEquals(true, set.add(a1));
		assertEquals(true, numset.add(n2));
		assertEquals(true, set.add(a4));
		assertEquals(true, numset.add(n4));
		
		assertEquals(true, rnumset.add(n2));
		assertEquals(true, rnumset.add(n4));
		
	}
	
	// Checks if true is returned when the created collection is added to a set.
	
	@Test
	public void testAddAll() {
		
		c.add(a1);
		c.add(a4);
		c.add(a3);
		
		numc.add(n2);
		numc.add(n3);
		numc.add(n4);
		
		assertEquals(true, set.addAll(c));
		assertEquals(true, numset.addAll(numc));

	}

	// Checks if the first value in the set is the correct one according to sorting.
	
	@Test
	public void testFirst() {
		
				set.add(a1);
				set.add(a2);
				set.add(a3);
				
				numset.add(n1);
				numset.add(n2);
				numset.add(n3);
				numset.add(n4);
				
				rnumset.add(n1);
				rnumset.add(n2);
				rnumset.add(n3);
				rnumset.add(n4);
				
		assertEquals(a3, set.first());
		assertEquals(n1, numset.first());
		assertEquals(n2, rnumset.first());
	}
	
	// Checks if the last value in the set is the correct one according to sorting.

	@Test
	public void testLast() {
		set.add(a3);
		set.add(a2);
		
		numset.add(n1);
		numset.add(n2);
		numset.add(n3);
		
		rnumset.add(n1);
		rnumset.add(n2);
		rnumset.add(n3);
		
		assertEquals(a2, set.last());
		assertEquals(n2, numset.last());
		assertEquals(n1, rnumset.last());
		
	}
	
	// Checks if the set had the expected number of values within it and then checks if the set removes all of them.

	@Test
	public void testClear() {
		
		set.add(a1);
		set.add(a2);
		set.add(a3);
		set.add(a4);
		assertEquals(4, set.size());
		
		numset.add(n1);
		numset.add(n2);
		numset.add(n3);
		numset.add(n4);
		assertEquals(4, numset.size());
		
		rnumset.add(n1);
		rnumset.add(n2);
		rnumset.add(n3);
		assertEquals(3, rnumset.size());
		
		set.clear();
		assertEquals(0, set.size());
		
		numset.clear();
		assertEquals(0, numset.size());
		
	}

	// Checks if the set has the selected value.
	
	@Test
	public void testContains() {
		
		set.add(a1);
		set.add(a2);
		set.add(a3);
		set.add(a4);
	
		numset.add(n1);
		numset.add(n2);
		numset.add(n3);
		numset.add(n4);
		
		rnumset.add(n1);
		rnumset.add(n2);
		rnumset.add(n3);
		
		assertEquals(true, set.contains(a1)
				&& set.contains(a2)
				&& set.contains(a3));
		
		assertEquals(true, numset.contains(n1)
				&& numset.contains(n2)
				&& numset.contains(n3)
				&& numset.contains(n4));
		
		assertEquals(true, rnumset.contains(n1)
				&& rnumset.contains(n2)
				&& rnumset.contains(n3));
		
	}
	
	// Checks if the values within the collection are also within the set.

	@Test
	public void testContainsAll() {
		
		set.add(a1);
		set.add(a2);
		set.add(a3);
		set.add(a4);
		
		c.add(a1);
		c.add(a2);
		c.add(a3);
		c.add(a4);
		
		numset.add(n1);
		numset.add(n2);
		numset.add(n3);
		numset.add(n4);
		
		numc.add(n1);
		numc.add(n2);
		numc.add(n3);
		numc.add(n4);
		
		assertEquals(true, set.containsAll(c));
		assertEquals(true, numset.containsAll(numc));
		
	}
	
	// Checks if the set has no values inside it.

	@Test
	public void testIsEmpty() {
		
		assertEquals(true, set.isEmpty());
		assertEquals(true, numset.isEmpty());
	}
	
	// Checks if after placing a variable/value into the set, if the method will return true when it is removed.

	@Test
	public void testRemove() {
		
		set.add(a1);
		set.add(a2);
		set.add(a3);
		
		numset.add(n1);
		numset.add(n2);
		numset.add(n3);
		
		assertEquals(true, set.remove(a1));
		assertEquals(true, numset.remove(n1));
	}

	// Checks if the remove all will remove the entire group of values inside the set returning true and the size is zero.
	
	@Test
	public void testRemoveAll() {
		
		set.add(a1);
		set.add(a2);
		set.add(a3);
		
		c.add(a1);
		c.add(a2);
		c.add(a3);
		
		
		numset.add(n1);
		numset.add(n2);
		numset.add(n3);
		
		numc.add(n1);
		numc.add(n2);
		numc.add(n3);
		
		assertEquals(3, set.size());
		assertEquals(true, set.removeAll(c));
		assertEquals(0, set.size());
		
		assertEquals(3, numset.size());
		assertEquals(true, numset.removeAll(numc));
		assertEquals(0, numset.size());
		
	}
	
	// Tests if the size of the set changes according to how many values are input.

	@Test
	public void testSize() {
		
		assertEquals(0, set.size());
		set.add(a1);
		set.add(a2);
		set.add(a3);
		
		assertEquals(0, numset.size());
		numset.add(n2);
		numset.add(n4);
		
		assertEquals(3, set.size());
		assertEquals(2, numset.size());
	}

	// Checks if the array was set up in the proper order and its output is the expected result.
	
	@Test
	public void testToArray() {
		
		set.add(a1);
		set.add(a2);
		set.add(a3);
		
		numset.add(n1);
		numset.add(n2);
		numset.add(n3);
		
		rnumset.add(n1);
		rnumset.add(n2);
		rnumset.add(n3);
		
		Object[] arr = set.toArray();
		Object[] arr2 = {"Afleck", "Clooney", "Timberlake"};
		
		Object[] arr3 = numset.toArray();
		Object[] arr4 = {1, 13, 21};
		
		Object[] arr5 = rnumset.toArray();
		Object[] arr6 = {21, 13, 1};
		
		for(int i = 0; i < arr.length; i++)
			assertEquals(arr[i], arr2[i]);
		
		for(int j = 0; j < arr3.length; j++)
			assertEquals(arr3[j], arr4[j]);
		
		for(int k = 0; k < arr5.length; k++)
			assertEquals(arr5[k], arr6[k]);
	}

}