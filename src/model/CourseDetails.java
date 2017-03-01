/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author Matthew Tse
 *
 */
public abstract class CourseDetails {
	public String courseNumber;
	public String courseName;	
	public int numberOfCredits;
	public char grade;
	public int numberGrade;
	public double gradePointAverage;


	/*
	 * @Name: evalNumberGrade
	 * 
	 * @Function/Purpose: sets a numerical grade from a letter grade
	 * 
	 * @Parameters:
	 * 		{vc} letterGrade
	 * 
	 * @Additionl Comments: All input must be passed as a string
	 * @Return {i4} integer number grade
	 */
	public int evalNumberGrade(String letterGrade) {
		int numberGrade;
		
		switch (letterGrade) {

		case "F":
			numberGrade = 0;
			break;
		case "D":
			numberGrade = 1;
			break;
		case "C":
			numberGrade = 2;
			break;
		case "B":
			numberGrade = 3;
			break;
		case "A":
			numberGrade = 4;
			break;			
		case "f":
			numberGrade = 0;
			break;
		case "d":
			numberGrade = 1;
			break;
		case "c":
			numberGrade = 2;
			break;
		case "b":
			numberGrade = 3;
			break;
		case "a":
			numberGrade = 4;
			break;
			
		default:
			numberGrade = -1;			
			break;

		}
		return numberGrade;
	}//end evalNumberGrade method
	/*
	 * @Name: calcAverage
	 * 
	 * @Function/Purpose: Calculates average
	 * 
	 * @Parameters:
	 * 		{ArrayList Integer} list of integers
	 * @Additionl Comments: All input must be passed as an ArrayList
	 * 
	 * @Return double average value
	 */
	public double calcAverage(ArrayList<Integer> cntr){
		double sum = 0.0;
		double avg = 0;		
		for (int i = 0; i < cntr.size(); i++) {
			sum += Double.parseDouble(Integer.toString(cntr.get(i)));	
		}
		avg = (sum/cntr.size());
		return avg;
	}//end calcAverage method
	/**
	 * @return the courseNumber
	 */
	public String getCourseNumber() {
		return courseNumber;
	}
	/**
	 * @param courseNumber the courseNumber to set
	 */
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the numberOfCredits
	 */
	public int getNumberOfCredits() {
		return numberOfCredits;
	}
	/**
	 * @param numberOfCredits the numberOfCredits to set
	 */
	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
	/**
	 * @return the grade
	 */
	public char getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(char grade) {
		this.grade = grade;
	}
	/**
	 * @return the numberGrade
	 */
	public int getNumberGrade() {
		return numberGrade;
	}
	/**
	 * @param numberGrade the numberGrade to set
	 */
	public void setNumberGrade(int numberGrade) {
		this.numberGrade = numberGrade;
	}
	/**
	 * @return the gradePointAverage
	 */
	public double getGradePointAverage() {
		return gradePointAverage;
	}
	/**
	 * @param gradePointAverage the gradePointAverage to set
	 */
	public void setGradePointAverage(double gradePointAverage) {
		this.gradePointAverage = gradePointAverage;
	}

}//end CourseDetails class
