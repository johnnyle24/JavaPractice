package assignment1;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	// Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int d[][])
	{
		numRows = d.length; // d.length is the number of 1D arrays in the 2D array
		if(numRows == 0)
			numColumns = 0;
		else
			numColumns = d[0].length; // d[0] is the first 1D array
		data = new int[numRows][numColumns]; // create a new matrix to hold the data
		// copy the data over
		for(int i=0; i < numRows; i++) 
			for(int j=0; j < numColumns; j++)
				data[i][j] = d[i][j];
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */
		
		for (int i=0; i < numRows; i++) {
			for (int j=0; j < numColumns; j++) {
				if (m.data[i][j] != data[i][j])
					return false;	
			}
		}
		return true;
		
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this 
		 * 			matrix, as specified on the assignment page
		 */
		
		String tString = "";
		
		for (int i=0; i < numRows; i++) {
			for (int j=0; j < numColumns; j++) {
				tString = tString + data[i][j] + " ";
			}
			tString = tString + "\n";
		}
		
		return tString;
	}
	
	public Matrix times(Matrix m)
	{	
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
		 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
		 *  the correct values for matrix multiplication
		 */
		
		Matrix mMatrix = new Matrix(new int[numRows][m.numColumns]);
		
		int sumf;
		
		if (numColumns == m.numRows) {
			for (int i=0; i < numRows; i++) {
				for (int j=0; j < m.numColumns; j++) {
					sumf = 0;
					for (int k = 0; k < numColumns; k++) {
						sumf += (data[i][k])*(m.data[k][j]);
					}
					mMatrix.data[i][j] = sumf;
					}
				}
			return mMatrix; } // placeholder
		else
			return null;
	}
	
	public Matrix plus(Matrix m)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for addition, if not, return null.
		 *  If they are compatible, create a new matrix and fill it in with
		 *  the correct values for matrix addition
		 */
		
		Matrix mMatrix = new Matrix(new int[numRows][m.numColumns]);
		
		int sumf;
		
		if (numRows == m.numRows && numColumns == m.numColumns) {
			for (int i=0; i < numRows; i++) {
				for (int j=0; j < numColumns; j++) {
					sumf = (data[i][j])+(m.data[i][j]);
					mMatrix.data[i][j] = sumf;
					}
				}
			return mMatrix; } // placeholder
		else
			return null;
		}
	}
