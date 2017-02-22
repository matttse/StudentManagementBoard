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
	public Course<Course> course = new Course<Course>();
	
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
	public Course<Course> getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course<Course> course) {
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
				
				studentRec.student.setId((String) processInput.getAlphaNum("Student ID: "));
				while (studentRec.student.id.length() == 0) {
					System.out.println("Did not Enter Any Value");
					studentRec.student.setId((String) processInput.getAlphaNum("Student ID: "));
				}
				
				studentRec.student.setFName(processInput.getString("Enter First Name: "));
				while (studentRec.student.getFName().length() == 0) {
					System.out.println("Did not Enter Any Value");
					studentRec.student.setFName((String) processInput.getString("Enter First Name: "));
				}
				studentRec.student.setLName(processInput.getString("Enter Last Name: "));
				while (studentRec.student.getLName().length() == 0) {
					System.out.println("Did not Enter Any Value");
					studentRec.student.setLName((String) processInput.getString("Enter Last Name: "));
				}
				StudentCollection.put(studentRec.student.id, studentRec);
//				course.addToHead(student);
//				course.printList();
				
			
			} else if (select == 2) {//Add a Course to Student Record

				//course alphaNum
				StudentRecord<Student> studentRec = new StudentRecord<Student>();
				//enter id
				studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
				//Check Student ID
				while (studentRec == null) {
					System.out.println("Student does not exist");
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));

				} 
				//enter Credits
				studentRec.course.setCredits(Integer.parseInt(processInput.getNum("Number of Credits: ", 1)));
				//check Credits
				while (studentRec.course.credits > 4 || studentRec.course.credits < 1) {
					System.out.println("Credits cannot be more than 4.0 or less than 1.0");
					studentRec.course.setCredits(Integer.parseInt(processInput.getNum("Number of Credits: ", 1)));
//					studentRec.course.credits = Integer.parseInt(processInput.getNum("Number of Credits: ", 1));
				}
//				Course<Course> course = new Course<Course>();
				//Add Course to List//
				Course<Course> newCourse = new Course<Course>();
				studentRec.course.setTitle((String) (processInput.getAlphaNum("Course Name/ID: ")));
				newCourse.convertInstanceOfObject(studentRec.course, Course.class);
				studentRec.course.addToHead(studentRec.course);
				//TODO
				
				
				studentRec.setStud(studentRec.student);

				StudentCollection.put(studentRec.student.id, studentRec); 
				
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
//				studentRec.course.printCourses();
				studentRec.course.printCourseDetails();
//				for (int i = 0; i < studentRec.course.size(); i++) {	
//					System.out.println("Course: " + studentRec.course + "\t" + studentRec.course.credits);
//					
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
