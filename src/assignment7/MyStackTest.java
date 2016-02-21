package assignment7;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tester class for MyStack
 * 
 * @author Emily Dennis, Johnny Le
 *
 */
public class MyStackTest {

	private MyStack<Integer> testerStack;

	@Before
	public void setUp() throws Exception {
		testerStack = new MyStack<Integer>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClear() {
		testerStack.push(1);
		testerStack.push(2);
		testerStack.push(3);
		assertEquals(false, testerStack.isEmpty());

		testerStack.clear();
		assertEquals(true, testerStack.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, testerStack.isEmpty());

		testerStack.push(1);
		testerStack.push(2);
		testerStack.push(3);
		assertEquals(false, testerStack.isEmpty());
	}

	@Test
	public void testPeek() {
		testerStack.push(1);
		assertEquals(1, (int) testerStack.peek());
		testerStack.push(2);
		assertEquals(2, (int) testerStack.peek());
		testerStack.push(3);
		assertEquals(3, (int) testerStack.peek());
	}

	@Test(expected = NoSuchElementException.class)
	public void testPeekException() {
		testerStack.peek();
	}

	@Test
	public void testPop() {
		testerStack.push(1);
		testerStack.push(2);
		testerStack.push(3);
		assertEquals(3, testerStack.size());

		testerStack.pop();
		assertEquals(2, testerStack.size());
		assertEquals(2, (int) testerStack.peek());
	}

	@Test(expected = NoSuchElementException.class)
	public void testPopException() {
		testerStack.pop();
	}

	@Test
	public void testPush() {
		testerStack.push(1);
		assertEquals(1, (int) testerStack.peek());
	}

	@Test
	public void testSize() {
		testerStack.push(1);
		testerStack.push(2);
		testerStack.push(3);
		assertEquals(3, testerStack.size());
	}

}
