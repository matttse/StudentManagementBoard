/**
 * 
 */
package model;


/**
 * @ClassName Student
 * @author Matthew Tse
 *
 */
public class Student extends Person {
	
	public String id;
	public String major;
	

	/**
	 * 
	 */
	public Student() {
		
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}


	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	

}
