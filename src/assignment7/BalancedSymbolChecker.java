package assignment7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import assignment7.MyStack;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Emily Dennis, Johnny Le
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public static String checkFile(String filename)
			throws FileNotFoundException {

		String file = readFile(filename);

		Character[] charFile = toCharacter(file);

		// To keep track of the line and column numbers
		int lineNumber = 1;
		int columnNumber = 0;

		// Boolean variables that represent the different modes
		boolean inStringLiteral = false;
		boolean inCharacterLiteral = false;
		boolean inBackSlash = false;
		boolean inLineComment = false;
		boolean inBlockComment = false;

		MyStack<Character> checkStack = new MyStack<Character>();

		for (int i = 0; i < charFile.length; i++) {

			columnNumber++;

			// When there is a new line, increment the line number and reset the
			// column number to 0
			if (charFile[i].equals('\n')) {
				lineNumber++;
				columnNumber = 0;
			}

			// Must determine which mode we are in

			// If we are in a string literal...
			if (inStringLiteral) {
				// If there is a " character, and it is not after a backslash
				if (charFile[i].equals('"') && !inBackSlash) {
					inStringLiteral = false;
					inBackSlash = false;
				}
				// If this is a backslash character, enter backslash mode (to
				// avoid cases such as "\anything")
				else if (charFile[i].equals('\\')) {
					inBackSlash = true;
				} else {
					inBackSlash = false;
				}
			}
			// If we are in a character literal...
			else if (inCharacterLiteral) {
				// If there is a ' character, and it is not after a backslash
				if (charFile[i].equals('\'') && !inBackSlash) {
					inCharacterLiteral = false;
					inBackSlash = false;
				}
				// If this is a backslash character, enter backslash mode (to
				// avoid cases such as "\anything")
				else if (charFile[i].equals('\\')) {
					inBackSlash = true;
				} else {
					inBackSlash = false;
				}
			}
			// If we are in a line commment...
			else if (inLineComment) {
				// If the character is a newline character, set the
				// inLineComment mode to false
				if (charFile[i] == '\n') {
					inLineComment = false;
				}
			}
			// If we are in a block comment...
			else if (inBlockComment) {
				// If the character is an asterisk, and the character after is a
				// forward slash, end the block comment mode and increment i so
				// that the loop will skip over the next character which is
				// irrelevant
				if (charFile[i].equals('*') && charFile[i + 1].equals('/')) {
					inBlockComment = false;
					i++;
				}
			}
			// If we're not in a "special" mode, parse the character normally
			else {
				// If it is a " character, enter StringLiteral mode
				if (charFile[i].equals('"')) {
					inStringLiteral = true;
					inBackSlash = false;
				}
				// If it is a ' character, enter CharacterLiteral mode
				else if (charFile[i].equals('\'')) {
					inCharacterLiteral = true;
					inBackSlash = false;
				}
				// If the character is a forward slash
				else if (charFile[i].equals('/')) {
					// If the next character is a forward slash, enter
					// inLineComment mode, and increment i so that we skip over
					// the next character
					if (charFile[i + 1].equals('/')) {
						inLineComment = true;
						i++;
					}
					// If the next character is an asterisk, enter blockComment
					// mode, and increment i so that we skip the next character
					else if (charFile[i + 1].equals('*')) {
						inBlockComment = true;
						i++;
					}
					// Ignore all other characters
				}
				// If the character is some kind of open bracket, push the
				// character on the stack
				else if (charFile[i].equals('{') || charFile[i].equals('[')
						|| charFile[i].equals('(')) {
					checkStack.push(charFile[i]);
				}
				// If the character is some kind of closed bracket...
				else if (charFile[i].equals('}') || charFile[i].equals(']')
						|| charFile[i].equals(')')) {
					// If the stack is empty, then there is an unmatched closing
					// symbol so call out an error
					if (checkStack.isEmpty()) {
						return unmatchedSymbol(lineNumber, columnNumber, ' ',
								charFile[i]);
					} else {
						// If the character on the stack is opposite of the
						// character we are currently parsing, pop the character
						// off of the stack
						if (charFile[i].equals(symbolConverter(checkStack
								.peek()))) {
							checkStack.pop();
						}
						// Otherwise, there must be an error
						else {
							// Closing symbol does not match the corresponding
							// opening symbol.
							return unmatchedSymbol(lineNumber, columnNumber,
									symbolConverter(checkStack.peek()),
									charFile[i]);
						}
					}
				}
				// Ignore all other characters
			}
		}
		// End of file has occurred, so look at and validate the stack.

		// If we are still in a comment, return the unfinished comment error
		if (inBlockComment == true) {
			return unfinishedComment();
		}
		
		// If the file ends and the stack isn't empty, return an error
		if (!checkStack.isEmpty()) {
			return unmatchedSymbolAtEOF(symbolConverter(checkStack.peek()));
		}
		// Otherwise, all symbols are correct
		else
			return allSymbolsMatch();
	}

	/**
	 * Converts the symbol into the opposite form
	 * 
	 * @param symbol - character input
	 * @return the opposite character
	 */
	private static Character symbolConverter(Character symbol) {
		if (symbol == '{')
			return '}';
		else if (symbol == '[')
			return ']';
		else if (symbol == '(')
			return ')';
		else if (symbol == '}')
			return '{';
		else if (symbol == ']')
			return '[';
		else if (symbol == ')')
			return '(';
		return null;
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber,
			char symbolExpected, char symbolRead) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column "
				+ colNumber + ". Expected " + symbolExpected + ", but read "
				+ symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected "
				+ symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* and // comment.
	 */
	private static String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private static String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}

	
	/**
	 * This method utilizes the BufferedReader which refines the result of FileReader. Subsequently,
	 * each line read from the result is added to the results String with \n partitioning lines.
	 * @param filename
	 * @return results
	 */
	private static String readFile(String filename) {
		String results = new String();
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while (input.ready()) {
				results = results + input.readLine() + '\n';
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Changes the file to an array filled with character objects
	 * 
	 * @param file - string of the filename
	 * @return Character[] - Character array of the file
	 */
	private static Character[] toCharacter(String file) {
		char[] charFile = file.toCharArray();
		Character[] characterFile = new Character[charFile.length];
		for (int i = 0; i < charFile.length; i++) {
			characterFile[i] = new Character(charFile[i]);
		}
		return characterFile;
	}
}

