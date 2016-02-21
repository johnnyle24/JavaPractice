//*********************************************************************
// CS2420 |Introduction to Algorithms and Data Structures | Spring 2015 
// Instructor: Dr. Miriah Meyer 
// Author: Tony Chow & Johnny Le
//
// Assignment2
//*********************************************************************
package assignment2;

// Imports the java class Gregorian Calendar.
import java.util.GregorianCalendar;


// Creates the class and makes it an extension of book allowing it to use the methods and instantiated variables made public by that class.
public class LibraryBook extends Book {
	
	// Creates variables to store data into.
	
	String holder;
	private GregorianCalendar dueDate;
	int month;
	int day;
	int year;
	
	// Super allows the parameters of the Book class to be drawn out of private.
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
	}
	
	// If getter is used, the name of the holder is returned.
	
	public String getHolder() {
		return holder;
	}
	
	
	// Sets the dueDate to the input parameters after placing them into the Gregorian Calendar class.
	
	public void setDueDate(int month, int day, int year) {
		dueDate = new GregorianCalendar(year, month, day);
	}
	
	// If getter is sued, the dueDate is returned.
	
	public GregorianCalendar getDueDate() {
		return dueDate;
	}
	
	// The holder variable for the object is set to the bookHolder name imported.
	
	public void checkOut(long isbn, String bookHolder) {
		holder = bookHolder;
	}
	
	// The holder and the dueDate are set to null for use with isbn.
	
	public void checkIn(long isbn) {
		holder = null;
		dueDate = null;
	}
	
	// The holder and the dueDate are set to null for use with bookHolder.
	
	public void checkIn(String bookHolder) {
		holder = null;
		dueDate = null;
	}
}
