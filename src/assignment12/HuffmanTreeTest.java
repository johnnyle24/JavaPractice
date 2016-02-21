package assignment12;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Tests the file compression and decompression using Huffman's algorithm, which
 * encodes and decodes each character of a file using a binary trie.
 * 
 * Ensures patency with pre-generated files and the begins algorithm the
 * generates and tests data sets of varying sizes and forms.
 * 
 * @author Johnny Le & Rithie Penn
 */

public class HuffmanTreeTest {

	/**
	 * Compresses the file
	 * 
	 * @param infile
	 * @param outfile
	 */
	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.compressFile(new File(infile), new File(outfile));

		t.huffmanToDot("huffman_tree_test.dot");
	}

	/**
	 * Decompresses the file
	 * 
	 * @param infile
	 * @param outfile
	 */
	public static void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.decompressFile(new File(infile), new File(outfile));
	}

	/**
	 * Main function that begins by testing pre-generated txt files and then
	 * follows by testing varying sizes of data sets for sets of all of the same
	 * letter, all of the same number, equally distributed data, randomized
	 * numbers as data, and randomized numbers interspersed with letters as
	 * data.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Basic
		// compressFile("originaltest1.txt", "compressedtest1.txt");

		// decompressFile("compressedtest1.txt", "decompressedtest1.txt");

		// All the same character
		// compressFile("originaltest2.txt", "compressedtest2.txt");

		// decompressFile("compressedtest2.txt", "decompressedtest2.txt");

		// Small file
		// compressFile("originaltest3.txt", "compressedtest3.txt");

		// decompressFile("compressedtest3.txt", "decompressedtest3.txt");

		// Large file
		// compressFile("originaltest4.txt", "compressedtest4.txt");

		// decompressFile("compressedtest4.txt", "decompressedtest4.txt");

		// Unique Characters
		// compressFile("originaltest5.txt", "compressedtest5.txt");

		// decompressFile("compressedtest5.txt", "decompressedtest5.txt");

		/**
		 * This portion of the code contains the tests of the generated lists.
		 */

		int maxSize = 1000; // Maximum amount of values to be placed in the
							// list.

		double fileSizeOriginal;
		double fileSizeDecompressed;

		ArrayList<Integer> varInt; // List of variable integers

		System.out.println("Compressed File Size\tDecompressed File Size");

		System.out.println("Same Letter");
		// All the same letter
		for (int i = 1; i < maxSize; i *= 2) {

			// File is written

			FileOutputStream out;

			try {
				out = new FileOutputStream("A12test.txt");
				for (int j = 0; j < i; j++) {
					out.write('a');
				}
				out.close();
			} catch (IOException e) {
				System.err.println(e);
			}

			// File is compressed

			compressFile("A12test.txt", "compressedtest.txt");

			decompressFile("compressedtest.txt", "A12testD.txt");

			// Length of the files are measured. This data is taken in bytes so
			// the value is multiplied by 8 to calculate the bits.

			File file = new File("compressedtest.txt");
			fileSizeOriginal = file.length() * 8;

			File fileD = new File("A12testD.txt");
			fileSizeDecompressed = fileD.length() * 8;

			// Prints out the size of the compressed file versus the
			// decompressed.

			System.out.println(fileSizeOriginal + "\t" + fileSizeDecompressed);

		}
		System.out.println("Same number");
		// All the same number
		for (int i = 1; i < maxSize; i *= 2) {

			FileOutputStream out;

			try {
				out = new FileOutputStream("A12test.txt");
				for (int j = 0; j < i; j++) {
					out.write('1');
				}
				out.close();
			} catch (IOException e) {
				System.err.println(e);
			}

			compressFile("A12test.txt", "compressedtest.txt");

			decompressFile("compressedtest.txt", "A12testD.txt");

			File file = new File("compressedtest.txt");
			fileSizeOriginal = file.length() * 8;

			File fileD = new File("A12testD.txt");
			fileSizeDecompressed = fileD.length() * 8;

			System.out.println(fileSizeOriginal + "\t" + fileSizeDecompressed);

		}

		System.out.println("Evenly Distributed");
		// Evenly distributed among 10 integers
		for (int i = 1; i < maxSize; i *= 2) {

			FileOutputStream out;

			try {
				out = new FileOutputStream("A12test.txt");
				for (int j = 0; j < i; j++) {
					int number = j % 10 + 1;
					out.write((char) (number + 'A'));
				}
				out.close();
			} catch (IOException e) {
				System.err.println(e);
			}

			compressFile("A12test.txt", "compressedtest.txt");

			decompressFile("compressedtest.txt", "A12testD.txt");

			File file = new File("compressedtest.txt");
			fileSizeOriginal = file.length() * 8;

			File fileD = new File("A12testD.txt");
			fileSizeDecompressed = fileD.length() * 8;

			System.out.println(fileSizeOriginal + "\t" + fileSizeDecompressed);

		}

		System.out.println("Variable Numbers");
		// Variable Numbers
		for (int i = 1; i < maxSize; i *= 2) {

			FileOutputStream out;
			varInt = generateAverageCase(i);

			try {
				out = new FileOutputStream("A12test.txt");
				for (int j = 0; j < i; j++) {
					out.write(varInt.get(j));
				}
				out.close();
			} catch (IOException e) {
				System.err.println(e);
			}

			compressFile("A12test.txt", "compressedtest.txt");

			decompressFile("compressedtest.txt", "A12testD.txt");

			File file = new File("compressedtest.txt");
			fileSizeOriginal = file.length() * 8;

			File fileD = new File("A12testD.txt");
			fileSizeDecompressed = fileD.length() * 8;

			System.out.println(fileSizeOriginal + "\t" + fileSizeDecompressed);

		}

		System.out.println("Variables Ints with Letters");
		// Variable integers with a couple letters
		for (int i = 1; i < maxSize; i *= 2) {

			FileOutputStream out;
			varInt = generateAverageCase(i);
			Random randomizer = new Random();

			try {
				out = new FileOutputStream("A12test.txt");
				for (int j = 0; j < i; j++) {
					if (j == (int) randomizer.nextInt(i)) {
						out.write('a');
					} else
						out.write(varInt.get(j));
				}
				out.close();
			} catch (IOException e) {
				System.err.println(e);
			}

			compressFile("A12test.txt", "compressedtest.txt");

			decompressFile("compressedtest.txt", "A12testD.txt");

			File file = new File("compressedtest.txt");
			fileSizeOriginal = file.length() * 8;

			File fileD = new File("A12testD.txt");
			fileSizeDecompressed = fileD.length() * 8;

			System.out.println(fileSizeOriginal + "\t" + fileSizeDecompressed);

		}

	}

	/**
	 * This method creates the best case which in this method is a list of
	 * integers from 49 to 57 in consecutive ascending order.
	 *
	 * @param size
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		ArrayList<Integer> bestCase = new ArrayList<Integer>(size);
		int j = 49;
		for (int i = 1; i < size + 1; i++) {
			j++;
			bestCase.add(i - 1, j);
			if (j == 57) {
				j = 49;
			}
		}
		return bestCase;
	}

	/**
	 * This method generates the average case by taking the best case and for
	 * every index value exchanges them with other randomly selected indices.
	 *
	 * @param size
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {
		ArrayList<Integer> averageCase = new ArrayList<Integer>(size);
		ArrayList<Integer> bestCase = generateBestCase(size);
		Random randomizer = new Random();
		for (int i = 0; i < size - 1; i++) {
			exchange(bestCase, i, (int) randomizer.nextInt(size));
		}
		averageCase = bestCase;
		return averageCase;
	}

	/**
	 * This method sets the left value to the right and vice versa.
	 *
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static <T> void exchange(ArrayList<T> arr, int left, int right) {
		T temp = arr.get(left);
		arr.set(left, arr.get(right));
		arr.set(right, temp);
	}

}
