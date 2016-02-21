package assignment8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing class for the Binary Search Tree.
 * 
 * @author Emily Dennis, Johnny Le
 */

public class BinarySearchTreeTester {

	BinarySearchTree<Integer> tester;

	ArrayList<Integer> arrayInteger, arrayNull, balancedIntegerArray;

	@Before
	public void setUp() throws Exception {
		tester = new BinarySearchTree<Integer>();

		arrayInteger = new ArrayList<Integer>();
		arrayInteger.add(1);
		arrayInteger.add(2);
		arrayInteger.add(3);
		arrayInteger.add(4);
		arrayInteger.add(5);
		
		arrayNull = null;
		
		balancedIntegerArray = new ArrayList<Integer>();
		balancedIntegerArray.add(1);
		balancedIntegerArray.add(3);
		balancedIntegerArray.add(7);
		balancedIntegerArray.add(10);
		balancedIntegerArray.add(11);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		assertFalse(tester.contains(3));
		assertTrue(tester.add(3));
		assertTrue(tester.contains(3));
		assertEquals(1, tester.size());

		tester.add(4);
		tester.add(2);
		assertEquals(3, tester.size());
		assertTrue(tester.contains(4));
		assertTrue(tester.contains(2));
		
		tester.clear();
		tester.add(5);
		tester.add(2);
		tester.add(10);
		tester.add(7);
		tester.add(6);
		assertEquals(5, tester.size());
		assertTrue(tester.contains(6));
		assertTrue(tester.contains(10));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddException(){
		tester.add(null);
	}

	@Test
	public void testAddAll() {
		assertTrue(tester.isEmpty());
		assertTrue(tester.addAll(arrayInteger));
		assertEquals(5, tester.size());
		assertTrue(tester.contains(3));
	}

	@Test(expected = NullPointerException.class)
	public void testAddAllException(){
		tester.addAll(arrayNull);
	}
	
	@Test
	public void testClear() {
		tester.addAll(arrayInteger);
		assertFalse(tester.isEmpty());

		tester.clear();
		assertTrue(tester.isEmpty());
	}

	@Test
	public void testContains() {
		tester.addAll(arrayInteger);
		assertTrue(tester.contains(2));
		assertFalse(tester.contains(6));
	}
	
	@Test(expected = NullPointerException.class)
	public void testContainsException(){
		tester.contains(null);
	}

	@Test
	public void testContainsAll() {
		tester.addAll(arrayInteger);
		assertTrue(tester.containsAll(arrayInteger));
		
		tester.clear();
		assertFalse(tester.containsAll(arrayInteger));
		
		tester.add(0);
		assertFalse(tester.containsAll(arrayInteger));
	}
	
	@Test(expected = NullPointerException.class)
	public void testContainsAllException(){
		tester.containsAll(arrayNull);
	}

	@Test
	public void testFirst() {
		tester.addAll(arrayInteger);
		assertEquals(1, (int) tester.first());

		tester.add(0);
		assertEquals(0, (int) tester.first());
		
		tester.add(6);
		assertEquals(0, (int) tester.first());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFirstException(){
		tester.first();
	}

	@Test
	public void testIsEmpty() {
		assertTrue(tester.isEmpty());
		
		tester.add(0);
		assertFalse(tester.isEmpty());
		
		tester.addAll(arrayInteger);
		assertFalse(tester.isEmpty());
	}

	@Test
	public void testLast() {
		tester.addAll(arrayInteger);
		assertEquals(5, (int) tester.last());

		tester.add(0);
		assertEquals(5, (int) tester.last());
		
		tester.add(6);
		assertEquals(6, (int) tester.last());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testLastException(){
		tester.last();
	}

	@Test
	public void testRemove() {
		tester.addAll(arrayInteger);
		assertTrue(tester.contains(3));
		assertEquals(5, tester.size());
		
		assertFalse(tester.contains(6));
		assertTrue(tester.remove(6));
		assertFalse(tester.contains(6));
		
		assertTrue(tester.remove(3));
		assertFalse(tester.contains(3));
		assertEquals(4, tester.size());
		
		assertTrue(tester.remove(5));
		assertFalse(tester.contains(5));
		assertEquals(3, tester.size());
		
		// tests removing a node with 2 children
		tester.clear();
		tester.add(3);
		tester.add(1);
		tester.add(10);
		tester.add(7);
		tester.add(11);
		assertTrue(tester.remove(10));
		assertFalse(tester.contains(10));
		assertEquals(4, tester.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testRemoveException(){
		tester.remove(null);
	}

	@Test
	public void testRemoveAll() {
		tester.addAll(arrayInteger);
		assertTrue(tester.containsAll(arrayInteger));
		assertTrue(tester.removeAll(arrayInteger));
		assertTrue(tester.isEmpty());
		assertFalse(tester.containsAll(arrayInteger));
		
		tester.addAll(arrayInteger);
		tester.add(6);
		assertTrue(tester.removeAll(arrayInteger));
		assertEquals(1, tester.size());
		
		tester.add(7);
		assertTrue(tester.removeAll(arrayInteger));
		assertEquals(2, tester.size());
		assertTrue(tester.contains(6));
		assertTrue(tester.contains(7));
		
		tester.add(3);
		assertTrue(tester.removeAll(arrayInteger));
		assertFalse(tester.contains(3));
		assertEquals(2, tester.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testRemoveAllException(){
		tester.removeAll(arrayNull);
	}

	@Test
	public void testSize() {
		assertEquals(0, tester.size());
		tester.addAll(arrayInteger);
		assertEquals(5, tester.size());
		
		tester.add(0);
		assertEquals(6, tester.size());
	}

	@Test
	public void testToArrayList() {
		tester.addAll(arrayInteger);
		assertArrayEquals(arrayInteger.toArray(), tester.toArrayList().toArray());
		
		tester.clear();
		tester.add(3);
		tester.add(1);
		tester.add(10);
		tester.add(7);
		tester.add(11);
		assertArrayEquals(balancedIntegerArray.toArray(), tester.toArrayList().toArray());
	}
}
