/**
 * 
 */
package model;


/**
 * @author CH165496
 *
 */
public class Student extends Person {
	
	protected String id;
	

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
