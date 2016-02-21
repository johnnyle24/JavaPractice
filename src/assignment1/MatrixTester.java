/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment1;

public class MatrixTester {
	public static void main(String[] args)
	{			
		Matrix M1 = new Matrix(new int[][]
		                                 {{1, 2, 3},
										  {2, 5, 6}});
		
		Matrix M2 = new Matrix(new int[][]
		                                 {{4, 5},
										  {3, 2},
										  {1, 1}});
		
		Matrix M3 = new Matrix(new int[][]
				{{4, 5, 6},
				{3, 2, 1}});
		
		// this is the known correct result of multiplying M1 by M2
		Matrix referenceMultiply = new Matrix(new int[][]
		                                                {{13, 12},
														 {29, 26}});
		
		Matrix referenceAddition = new Matrix(new int[][]
				{{5, 7, 9},
				{5, 7, 7}});
		
		Matrix totallyWrongMatrix = new Matrix(new int[][]
				{{7, 13},
				{17, 23}});
		
		/* 
		 * Note that none of the tests below will be correct until you have implemented all methods.
		 * This is just one example of a test, you must write more tests and cover all cases.
		 */
		
		// get the matrix computed by your times method
		Matrix computedMultiply = M1.times(M2);
		
		Matrix computedAddition = M1.plus(M3);
		
		Matrix computedWrong = M1.times(totallyWrongMatrix);
		
		Matrix computedWrongPartTwo = M1.plus(totallyWrongMatrix);
				
		// exercises your toString method
		System.out.println("Computed result for M1 * M2:\n" + computedMultiply); 
		
		System.out.println("Computed result for M1 + M2:\n" + computedAddition);
		
		System.out.println("This should return a multiplied null:\n" + computedWrong + "\n");
		
		System.out.println("This should return a summed null:\n" + computedWrongPartTwo + "\n");
		
		// exercises your .equals method, and makes sure that your computed result is the same as the known correct result
		if(!referenceMultiply.equals(computedMultiply)) 
			System.out.println("Product should be:\n" + referenceMultiply);
		
		if(!referenceAddition.equals(computedAddition))
			System.out.println("Sum should be:\n" + referenceAddition);
		
		if(!referenceMultiply.equals(totallyWrongMatrix))
			System.out.println("If the result is not equal to the totallyWrongMatrix, this line is printed...so that means this equals method works. Huzzah.\n");
		
		System.out.println("This line is printing M1:\n" + M1);
			System.out.println("If toString() works, it should look like this:\n" + "1 2 3\n2 5 6");
		
		
		
		
		
		//TODO: fill in more tests that fully exercise all of your methods
	}
}
