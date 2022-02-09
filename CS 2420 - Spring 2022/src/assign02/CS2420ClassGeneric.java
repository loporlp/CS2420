package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This Java class represents an unordered collection of University of Utah students enrolled in CS 2420.
 * 
 * NOTE: The word "Class" in the name of this Java class means a collection of students and should not 
 *       be confused with the Java term class, which is a blueprint for making objects.
 * 
 * @author Erin Parker and Mason Sansom and Aidan Wilde
 * @version January 20, 2022
 */
public class CS2420ClassGeneric<Type> {
	
	
	

	private ArrayList<CS2420StudentGeneric<Type>> studentList;
	
	/**
	 * Creates an empty CS 2420 class.
	 */
	public CS2420ClassGeneric() {
		this.studentList = new ArrayList<>();
	}
	
	/**
	 * Adds the given student to the collection of students in CS 2420, avoiding duplicates.
	 * 
	 * @param student - student to be added to this CS 2420 class
	 * @return true if the student was added, 
	 *         false if the student was not added because they already exist in the collection
	 */
	public boolean addStudent(CS2420StudentGeneric<Type> student) {
		if(lookup(student.getUNID()) == null)
		{
			studentList.add(student);
			return true;
		}
		return false;
	}
	
	/**
	 * Retrieves the CS 2420 student with the given uNID.
	 * 
	 * @param uNID - uNID of student to be retrieved
	 * @return the CS 2420 student with the given uNID, or null if no such student exists in the collection
	 */
	public CS2420StudentGeneric<Type> lookup(int uNID) {
	//	System.out.println(uNID + " ----");
	/*	for(CS2420StudentGeneric s: studentList)
		{
			
			if(s.getUNID() == uNID);
			{
			//	System.out.println(s.getUNID());
			//	System.out.println(s.getFirstName());
				return s;
			}
		}
		*/
		for(int x = 0; x < studentList.size(); x++)
		{
			if(studentList.get(x).getUNID() == uNID)
			{
				System.out.println(studentList.get(x).getFirstName());
				return studentList.get(x);
			}
		}
		return null;
	}
	
	/**
	 * Retrieves the CS 2420 student(s) with the given contact information.
	 * 
	 * @param contactInfo - contact information of student(s) to be retrieved
	 * @return a list of the CS 2420 student(s) with the given contact information (in any order), 
	 * 	     or an empty list if no such students exist in the collection
	 */
	public ArrayList<CS2420StudentGeneric<Type>> lookup(Type contactInfo) {
		ArrayList<CS2420StudentGeneric<Type>> returnList = new ArrayList<CS2420StudentGeneric<Type>>();
		
		for(CS2420StudentGeneric<Type> s: studentList)
		{
			if(s.getContactInfo().equals(contactInfo))
			{
			returnList.add(s);
			}
		}
			
		return returnList;
	}
	
	/**
	 * Adds an assignment, exam, lab, or quiz score for the CS 2420 student with the given uNID.
	 * 
	 * NOTE: If the category string is not one of "assignment", "exam", "lab", or "quiz", or
	 *       if no student with the uNID exists in the collection, then this method has no effect.
	 * 
	 * @param uNID - uNID of student whose score is to be added
	 * @param score - the score to be added
	 * @param category - the category in which to add the score
	 */
	public void addScore(int uNID, double score, String category) {
		if(lookup(uNID) != null)	
			lookup(uNID).addScore(score, category);
	}
	
	/**
	 * Computes the average score of all CS 2420 students in the collection.
	 * 
	 * @return the average score, or 0 if there are no students in the collection
	 */
	public double computeClassAverage() {
		if(studentList.size() == 0)
		{
			return 0.0;
		}
		double totalScore = 0;
		for(CS2420StudentGeneric<Type> s: studentList)
		{
			totalScore += s.computeFinalScore();
		}
		
		return totalScore /studentList.size();
	}
	
	/**
	 * Creates a list of contact information for all CS 2420 students in the collection.
	 *
	 * @return the duplicate-free list of contact information, in any order
	 */
	public ArrayList<Type> getContactList() {
		ArrayList<Type> returnList = new ArrayList<Type>();
		if(this.studentList.isEmpty()) {
			return returnList;
		}
		returnList.add(this.studentList.get(0).getContactInfo());
		for(CS2420StudentGeneric<Type> s: this.studentList) 
		{
				if(!returnList.contains(s.getContactInfo()))
					returnList.add(s.getContactInfo());
		}		
		return returnList;
	}
	/**
	 * Returns the list of CS 2420 students in this class, sorted by 
	uNID in ascending order.
	 */
	public ArrayList<CS2420StudentGeneric<Type>> getOrderedByUNID() {
	    ArrayList<CS2420StudentGeneric<Type>> studentListCopy = 
	new ArrayList<CS2420StudentGeneric<Type>>();
	    for(CS2420StudentGeneric<Type> student : studentList)
	    studentListCopy.add(student);
	    sort(studentListCopy, new OrderByUNID());
	    return studentListCopy;
	}
	/**
	 * Returns the list of CS 2420 students in this class, sorted by 
	last name in lexicographical order.
	 * Breaks ties in last names using first names (lexicographical 
	order).
	 * Breaks ties in first names using uNIDs (ascending order).
	 */
	public ArrayList<CS2420StudentGeneric<Type>> getOrderedByName() {
	    ArrayList<CS2420StudentGeneric<Type>> studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
	    for(CS2420StudentGeneric<Type> student : studentList)
	    	studentListCopy.add(student);
	    sort(studentListCopy, new OrderByName());
	    return studentListCopy;
	}
	/**
	 * Returns the list of CS 2420 students in this class with a final 
	score of at least cutoffScore,
	 * sorted by final score in descending order.
	 * Breaks ties in final scores using uNIDs (ascending order).
	 *
	 * @param cutoffScore - value that a student's final score must be 
	at or above to be included
	 *                      in the returned list
	 */
	public ArrayList<CS2420StudentGeneric<Type>> 
	getOrderedByScore(double cutoffScore) {
	    ArrayList<CS2420StudentGeneric<Type>> studentListCopy= new ArrayList<CS2420StudentGeneric<Type>>();
	    for(CS2420StudentGeneric<Type> student: studentList) {
	    	if(student.computeFinalScore() >= cutoffScore)
	    		studentListCopy.add(student);
	    }
	    sort(studentListCopy, new OrderByScore());
	    return studentListCopy;
	}
	
	
	/**
	 * Returns a negative value if lhs (left-hand side) is 
	smaller than rhs (right-hand side).
	 * Returns a positive value if lhs is larger than rhs.
	 * if value is zero tie will be broken by UID
	 */
	protected class OrderByScore implements 
	Comparator<CS2420StudentGeneric<Type>> {
	public int compare(CS2420StudentGeneric<Type> lhs, 
	CS2420StudentGeneric<Type> rhs) {
		if((int)(lhs.computeFinalScore() - rhs.computeFinalScore()) != 0)
			return (int)(lhs.computeFinalScore() - rhs.computeFinalScore());
		return lhs.getUNID() - rhs.getUNID();
	}
	}
	/**
	 * Performs a SELECTION SORT on the input ArrayList.
	 *
	 * 1. Finds the smallest item in the list.
	 * 2. Swaps the smallest item with the first item in the list.
	 * 3. Reconsiders the list be the remaining unsorted portion (second 
	item to Nth item) and
	 *    repeats steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, 
	Comparator<ListType> c) {
	for(int i = 0; i < list.size() - 1; i++) {
	int j, minIndex;
	for(j = i + 1, minIndex = i; j < list.size(); 
	j++)
	if(c.compare(list.get(j), 
	list.get(minIndex)) < 0)
	minIndex = j;
	ListType temp = list.get(i);
	list.set(i, list.get(minIndex));
	list.set(minIndex, temp);
	}
	}
	/**
	 * Comparator that defines an ordering among CS 2420 students using 
	their uNIDs.
	 * uNIDs are guaranteed to be unique, making a tie-breaker 
	unnecessary.
	 */
	protected class OrderByUNID implements 
	Comparator<CS2420StudentGeneric<Type>> {
	/**
	 * Returns a negative value if lhs (left-hand side) is 
	smaller than rhs (right-hand side).
	 * Returns a positive value if lhs is larger than rhs.
	 * Returns 0 if lhs and rhs are equal.
	 */
	public int compare(CS2420StudentGeneric<Type> lhs, 
	CS2420StudentGeneric<Type> rhs) {
	return lhs.getUNID() - rhs.getUNID();
	}
	}
	/**
	 * Comparator that defines an ordering among CS 2420 students using 
	their names.
	 * Compares by last name, then first name (if last names are the 
	same), then uNID
	 * (if both names are the same).  uNIDs are guaranteed to be unique.
	 */
	protected class OrderByName implements 
	Comparator<CS2420StudentGeneric<Type>> {

		@Override
		public int compare(CS2420StudentGeneric<Type> o1, CS2420StudentGeneric<Type> o2) {
			if(o1.getLastName().compareTo(o2.getLastName()) != 0) {
				return o1.getLastName().compareTo(o2.getLastName());
			}
			if(o1.getFirstName().compareTo(o2.getFirstName()) != 0) {
				return o1.getFirstName().compareTo(o2.getFirstName());
			}
			return o1.getUNID() - o2.getUNID();
		}
	// FILL IN
	}
}