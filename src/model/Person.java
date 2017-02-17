package model;
/**
 * @ClassName Person
 * @Author Matthew Tse 
 *
 */
public abstract class Person {
	
	//Declarations and initializations
	protected String FName	= "";
	protected String LName	= "";
	protected int Age;
	/**
	 * @return the fName
	 */
	public String getFName() {
		return FName;
	}
	/**
	 * @param fName the fName to set
	 */
	public void setFName(String fName) {
		FName = fName;
	}
	/**
	 * @return the lName
	 */
	public String getLName() {
		return LName;
	}
	/**
	 * @param lName the lName to set
	 */
	public void setLName(String lName) {
		LName = lName;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return Age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		Age = age;
	}
	


}//end customer class