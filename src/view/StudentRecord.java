package view;

import model.Student;
import model.Course;
import java.util.TreeMap;

import dhl.UserInputHandler;

/**
 * @ClassName StudentRecord
 * @author Matthew Tse
 * @Method main
 * @Method exit
 *
 */
public class StudentRecord<E> {
	/**
	 * @return the stud
	 */
	protected Student getStud() {
		return student;
	}

	/**
	 * @param stud the stud to set
	 */
	protected void setStud(Student student) {
		this.student = student;
	}

	/**
	 * @return the course
	 */
	protected Course<Student> getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	protected void setCourse(Course<Student> course) {
		this.course = course;
	}

	public Student student = new Student();
	public Course<Student> course = new Course<Student>();
	// Main method
	public static void main(String[] args) {

		// initialize and declare global vars
		int completeFlag = 0;
		String optionOne = "(1) Add a Student";
		String optionTwo = "(2) Add a Course to Student Record";
		String optionThree = "(3) Delete a Course from Student Record";
		String optionFour = "(4) Print Student Record";
		String optionZero = "(0) Exit the program";
		String option = "";

		// instantiate the handler
		UserInputHandler processInput = new UserInputHandler();

		// Option selector
		while (completeFlag == 0) {

			System.out.println("Please select an option.");

			option = processInput.getNum((optionOne.concat("\n").concat(optionTwo).concat("\n").concat(optionThree).concat("\n").concat(optionFour).concat("\n")
					.concat("\n").concat(optionZero).concat("\t")), 0);

			int select = Integer.parseInt(option);
			TreeMap<Integer, StudentRecord> StudentCollection = new TreeMap<Integer, StudentRecord>();
			
			StudentRecord<Student> student = new StudentRecord<Student>();
			

			
			if (select > 5 || select < 0) {// Valid options

				System.out.print("Please select a valid option");
			
			} else if (select == 1) {//Add a Student
				//name
				//id
				
//				student.id = processInput.getAlphaNum("Student ID: ");
//				student.setFName(processInput.getString("Enter First Name: "));
//				student.setLName(processInput.getString("Enter Last Name: "));
				
//				course.addToHead(student);
//				course.printList();
				
			
			} else if (select == 2) {//Add a Course to Student Record
				//enter id
				//course alphaNum
			
			} else if (select == 3) {//Delete
				//enter id
				//enter course number
			
			} else if (select == 4) {//Print Record
				//enter id

				
			} else { //exit
				completeFlag += 1;
				if (select == 0) {

					System.out.println("Thank you and goodbye");
					exit(0);

				} // end if processing options

			} // end if valid menu options

		} // end Option menu

	}// end main method

	// standard system exit.
	public static void exit(int status) {
		System.exit(status);
		return;
	}//end system exit
	
	

}//end student record class
