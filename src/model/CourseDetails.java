/**
 * 
 */
package model;

/**
 * @author CH165496
 *
 */
public abstract class CourseDetails<E> {
	public int credits;
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


}
