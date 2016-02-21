//*********************************************************************
// CS2420 |Introduction to Algorithms and Data Structures | Spring 2015 
// Instructor: Dr. Miriah Meyer 
// Author: Tony Chow & Johnny Le
//
// Assignment2
//*********************************************************************
package assignment2;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 * 
 */
public class Book {
	
	// Instantiates three new variables. 

	private long isbn;

	private String author;

	private String title;
	
	// Creates the method book for which books will be assigned variables.

	public Book(long _isbn, String _author, String _title) {
		this.isbn = _isbn;
		this.author = _author;
		this.title = _title;
	}

	/**
	 * @return the author
	 */
	
	// Returns the name of the author when the getter is called.
	
	public String getAuthor() {
		return this.author;
	}

	/**
	 * @return the ISBN
	 */
	
	// Returns the isbn when the getter is called.
	
	public long getIsbn() {
		return this.isbn;
	}

	/**
	 * @return the title
	 */
	
	// Returns the title of the book when the getter is called.
	
	public String getTitle() {
		return this.title;
	}

	/**
	 * Two books are considered equal if they have the same ISBN, author, and
	 * title.
	 * 
	 * @param other
	 *            -- the object begin compared with "this"
	 * @return true if "other" is a Book and is equal to "this", false otherwise
	 */
	
	// Overcomes the equals method and will return True or False based on equality.
	
	public boolean equals(Object other) {
		// FILL IN -- do not return false unless appropriate
		
		
		//Instantiates a book object
		Book otherBook = null;
		
		//Checks if object other is a book. If it is, otherBook is equal to it. If not, returns false.
		if(other instanceof Book) 
			otherBook = (Book) other;
		else 
			return false;
		
		//Checks if the variables of the object are equal to the object input.
		
		if (this.isbn == otherBook.isbn &&
			this.author == otherBook.author &&
			this.title == otherBook.title)
			return true;
		else
			return false;
	}

	/**
	 * Returns a string representation of the book.
	 */
	public String toString() {
		return isbn + ", " + author + ", \"" + title + "\"";
	}
}
