package assignment5;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortUtilTest {
	int a, b, d, e, f, threshold;
	ArrayList<Integer> bestCase, worstCase, averageCase,
					zeroCase, oneCase, twoCase,
					zeroCheck, oneCheck, twoCheck,
					largeBestCase, largeWorstCase, largeAverageCase;
	
	
	Comparator<Integer> comp;

	@Before
	public void setUp() throws Exception {
		
		 comp = new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2);
				}
			};
			
		
		a = 10;
		b = 100;
		d = 1;
		e = 2;
		f = 0;
		threshold = 80;

		bestCase = SortUtil.generateBestCase(a);
		worstCase = SortUtil.generateWorstCase(a);
		averageCase = SortUtil.generateAverageCase(a);
		
		largeBestCase = SortUtil.generateBestCase(b);
		largeWorstCase = SortUtil.generateWorstCase(b);
		largeAverageCase = SortUtil.generateAverageCase(b);
		
		zeroCase = SortUtil.generateWorstCase(f);
		oneCase = SortUtil.generateWorstCase(d);
		twoCase = SortUtil.generateWorstCase(e);
		
		zeroCheck = SortUtil.generateBestCase(f);
		oneCheck = SortUtil.generateBestCase(d);
		twoCheck = SortUtil.generateBestCase(e);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void mergeSortBestTest() {
		SortUtil.mergesort(bestCase, threshold, comp);
		
		assertEquals(bestCase, bestCase);
	}
	
	
	@Test
	public void mergeSortAverageTest() {
		SortUtil.mergesort(averageCase, threshold, comp);

		assertEquals(bestCase, averageCase);
	}
	
	@Test
	public void mergeSortWorstTest() {
		SortUtil.mergesort(worstCase, threshold,  comp);
		
		assertEquals(bestCase, worstCase);
	}
	
	@Test
	public void mergeSortLargeBestTest() {
		SortUtil.mergesort(largeBestCase, threshold, comp);
		
		assertEquals(largeBestCase, largeBestCase);
	}
	
	
	@Test
	public void mergeSortLargeAverageTest() {
		SortUtil.mergesort(largeAverageCase, threshold, comp);

		assertEquals(largeBestCase, largeAverageCase);
	}
	
	@Test
	public void mergeSortLargeWorstTest() {
		SortUtil.mergesort(largeWorstCase, threshold,  comp);
		
		assertEquals(largeBestCase, largeWorstCase);
	}
	
	@Test
	public void mergeSortZeroTest() {
		SortUtil.mergesort(zeroCase, threshold, comp);
		
		assertEquals(zeroCheck, zeroCase);
	}
	
	@Test
	public void mergeSortOneTest() {
		SortUtil.mergesort(oneCase, threshold, comp);
		
		assertEquals(oneCheck, oneCase);
	}
	
	@Test
	public void mergeSortTwoTest() {
		SortUtil.mergesort(twoCase, threshold, comp);
		
		assertEquals(twoCheck, twoCase);
	}
	

	@Test
	public void quickSortBestTest() {
		SortUtil.quicksort(bestCase, comp);
		
		assertEquals(bestCase, bestCase);
	}
	
	
	@Test
	public void quickSortAverageTest() {
		SortUtil.quicksort(averageCase, comp);

		assertEquals(bestCase, averageCase);
	}
	
	@Test
	public void quickSortWorstTest() {
		SortUtil.quicksort(worstCase,  comp);
		
		assertEquals(bestCase, worstCase);
	}
	
	@Test
	public void quickSortZeroTest() {
		SortUtil.quicksort(zeroCase,  comp);
		
		assertEquals(zeroCheck, zeroCase);
	}
	
	@Test
	public void quickSortOneTest() {
		SortUtil.quicksort(oneCase,  comp);
		
		assertEquals(oneCheck, oneCase);
	}
	
	@Test
	public void quickSortTwoTest() {
		SortUtil.quicksort(twoCase,  comp);
		
		assertEquals(twoCheck, twoCase);
	}
	
	@Test
	public void quickSortLargeBestTest() {
		SortUtil.quicksort(largeBestCase, comp);
		
		assertEquals(largeBestCase, largeBestCase);
	}
	
	
	@Test
	public void quickSortLargeAverageTest() {
		SortUtil.quicksort(largeAverageCase, comp);

		assertEquals(largeBestCase, largeAverageCase);
	}
	
	@Test
	public void quickSortLargeWorstTest() {
		SortUtil.quicksort(largeWorstCase,  comp);
		
		assertEquals(largeBestCase, largeWorstCase);
	}
	
	
}
