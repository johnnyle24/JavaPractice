package assignment1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatrixJTest {
	private Matrix M1, M2, M3, referenceMultiply, referenceAddition, totallyWrongMatrix;

	@Before
	public void setUp() throws Exception {
		M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });

		M2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });

		M3 = new Matrix(new int[][] { { 4, 5, 6 }, { 3, 2, 1 } });

		referenceMultiply = new Matrix(new int[][] { { 13, 12 },
				{ 29, 26 } });

		referenceAddition = new Matrix(new int[][] { { 5, 7, 9 },
				{ 5, 7, 7 } });

		totallyWrongMatrix = new Matrix(new int[][] { { 7, 13 },
				{ 17, 23 } });
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void matrixTest1() {
		assertEquals(referenceMultiply, M1.times(M2));
		
	}
		
	@Test
	public void matrixTest2() {
		assertEquals(referenceAddition, M1.plus(M3));
		
	}

	@Test
	public void matrixTest3() {
		assertEquals(referenceAddition, M1.plus(M3));
		
	}
}
