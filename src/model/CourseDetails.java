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
	/**
	 * 
	 */
	public CourseDetails() {
		// TODO Auto-generated constructor stub
	}
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


}
