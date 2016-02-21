package assignment7;

import static org.junit.Assert.*;
import assignment7.BalancedSymbolChecker;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tester class for the BalancedSymbolChecker
 * 
 * @author Emily Dennis, Johnny Le
 *
 */
public class BalancedSymbolCheckerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClass1() throws FileNotFoundException {
		assertEquals(
				"ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
				BalancedSymbolChecker.checkFile("TesterClass1"));
	}

	@Test
	public void testClass2() throws FileNotFoundException {
		assertEquals(
				"ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",
				BalancedSymbolChecker.checkFile("TesterClass2"));
	}

	@Test
	public void testClass3() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("TesterClass3"));
	}

	@Test
	public void testClass4() throws FileNotFoundException {
		assertEquals("ERROR: File ended before closing comment.",
				BalancedSymbolChecker.checkFile("TesterClass4"));
	}

	@Test
	public void testClass5() throws FileNotFoundException {
		assertEquals(
				"ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.",
				BalancedSymbolChecker.checkFile("TesterClass5"));
	}

	@Test
	public void testClass6() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("TesterClass6"));
	}

	@Test
	public void testClass7() throws FileNotFoundException {
		assertEquals(
				"ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.",
				BalancedSymbolChecker.checkFile("TesterClass7"));
	}

	@Test
	public void testClass8() throws FileNotFoundException {
		assertEquals(
				"ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.",
				BalancedSymbolChecker.checkFile("TesterClass8"));
	}

	@Test
	public void testClass9() throws FileNotFoundException {
		assertEquals(
				"ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.",
				BalancedSymbolChecker.checkFile("TesterClass9"));
	}

	@Test
	public void testClass10() throws FileNotFoundException {
		assertEquals(
				"ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.",
				BalancedSymbolChecker.checkFile("TesterClass10"));
	}

	@Test
	public void testClass11() throws FileNotFoundException {
		assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.",
				BalancedSymbolChecker.checkFile("TesterClass11"));
	}

	@Test
	public void testClass12() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("TesterClass12"));
	}

	@Test
	public void testClass13() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("TesterClass13"));
	}

	@Test
	public void testClass14() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("TesterClass14"));
	}
}
