/**
 * 
 */
package model;

/**
 * @author Matthew Tse
 *
 */
public abstract class CourseDetails {
	public int credits;
	public String title;
	public String letterGrade;
	public int numberGrade;
	public double gradePointAverage;

	/**
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the letterGrade
	 */
	public String getLetterGrade() {
		return letterGrade;
	}

	/**
	 * @param letterGrade the letterGrade to set
	 */
	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
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
	}
	
	public <E> double calcAverage(E[] list){
		double sum = 0.0;
		double avg = 0;		
		for (int i = 0; i < list.length; i++) {
			sum += Double.parseDouble(Integer.toString((Integer) list[i]));	
		}
		avg = (sum/list.length);
		return avg;
	}

}
