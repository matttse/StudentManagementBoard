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
	

	/**
	 * 
	 */
	public Student() {
		
	}


	/**
	 * @return the id
	 */
	protected String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	protected void setId(String id) {
		this.id = id;
	}
	

}
