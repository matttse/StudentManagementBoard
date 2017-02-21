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
	public Student student = new Student();
	public Course<String> course = new Course<String>();
	/**
	 * @return the stud
	 */
	public Student getStud() {
		return student;
	}

	/**
	 * @param stud the stud to set
	 */
	public void setStud(Student student) {
		this.student = student;
	}

	/**
	 * @return the course
	 */
	public Course<String> getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course<String> course) {
		this.course = course;
	}


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
		TreeMap<String, StudentRecord> StudentCollection = new TreeMap<String, StudentRecord>();
		// Option selector
		while (completeFlag == 0) {

			System.out.println("Please select an option.");

			option = processInput.getNum((optionOne.concat("\n").concat(optionTwo).concat("\n").concat(optionThree).concat("\n").concat(optionFour).concat("\n")
					.concat("\n").concat(optionZero).concat("\t")), 0);

			int select = Integer.parseInt(option);
			
			if (select > 5 || select < 0) {// Valid options

				System.out.print("Please select a valid option");
			
			} else if (select == 1) {//Add a Student
				//name
				//id
				StudentRecord<Student> studentRec = new StudentRecord<Student>();				
				studentRec.student.id = (String) processInput.getAlphaNum("Student ID: ");
				studentRec.student.setFName(processInput.getString("Enter First Name: "));
				studentRec.student.setLName(processInput.getString("Enter Last Name: "));
				StudentCollection.put(studentRec.student.id, studentRec);
//				course.addToHead(student);
//				course.printList();
				
			
			} else if (select == 2) {//Add a Course to Student Record
				//enter id
				//course alphaNum
				StudentRecord<Student> studentRec = new StudentRecord<Student>();
				studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
				while (studentRec == null) {
					System.out.println("Student does not exist");
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));

				} 
				Course<String> course = new Course<String>();				
				course.addToHead((String) processInput.getAlphaNum("Course Name/ID: "));
				studentRec.setStud(studentRec.student);
				studentRec.setCourse(course);
				StudentCollection.put(studentRec.student.id, studentRec);

//				processInput.getAlphaNum("Course Name/ID: "));
			
			} else if (select == 3) {//Delete
				//enter id
				//enter course number
			
			} else if (select == 4) {//Print Record
				//enter id
				StudentRecord<Student> studentRec = new StudentRecord<Student>();
				studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
				while (studentRec == null) {
					System.out.println("Student does not exist");
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));

				} 
//				for (int i = 0; i < studentRec.course.size(); i++) {
					studentRec.course.printList();	
//				}
				

				
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
