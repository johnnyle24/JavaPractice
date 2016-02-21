//*********************************************************************
// CS2420 |Introduction to Algorithms and Data Structures | Spring 2015 
// Instructor: Dr. Miriah Meyer 
// Author: Tony Chow & Johnny Le
//
// Assignment2
//*********************************************************************
package assignment2;

// Imports Gregorian Calendar

import java.util.GregorianCalendar;

// In this class, all bookHolder types "String" are generalized to "type".

public class LibraryBookGeneric<Type> extends Book {
	Type holder;
	private GregorianCalendar dueDate;

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
	}
	
	public Type getHolder() {
		return holder;
	}
	
	public void setDueDate(int month, int day, int year){
		dueDate = new GregorianCalendar(year, month, day);
	}
	
	public GregorianCalendar getDueDate() {
		return dueDate;
	}
	public void checkOut(long isbn, Type bookHolder) {
		holder = bookHolder;
	}
	
	public void checkIn(long isbn) {
		holder = null;
		dueDate = null;
	}
	
	public void checkIn(Type bookHolder) {
		holder = null;
		dueDate = null;
	}

}
