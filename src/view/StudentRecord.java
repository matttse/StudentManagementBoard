package view;

import model.CourseList;
import model.CourseList.Course;
import model.Student;

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
	public CourseList<E> courses = new CourseList<E>();

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
		UserInputHandler<String> processInput = new UserInputHandler<String>();
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
				
				studentRec.student.setId(processInput.getAlphaNum("Student ID: "));
				while (studentRec.student.id.length() == 0) {
					System.out.println("Did not Enter Any Value");
					studentRec.student.setId(processInput.getAlphaNum("Student ID: "));
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
				StudentCollection.put(studentRec.student.getId(), studentRec);
				
			
			} else if (select == 2) {//Add a Course to Student Record
				//validate option
				if (StudentCollection.size() == 0) {
					System.out.println("Need to Add a Student before Adding a Course.");
				} else {
					//course alphaNum
					StudentRecord<Student> studentRec = new StudentRecord<Student>();
					//enter id
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					//Check Student ID
					while (studentRec == null) {
						System.out.println("Student does not exist");
						studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));

					} 
					//Add Course to List//
					Object title = processInput.getAlphaNum("Course Name/ID: ");
					Course<String> newCourse = new Course<String>(title.toString());
					CourseList<Course> courses = new CourseList<Course>(studentRec.courses);

					//Number of Credits
					int numCredits = Integer.parseInt(processInput.getNum("Number of Credits: ", 1));
					//Check Credits
					while (numCredits > 4 || numCredits < 1) {
						System.out.println("Credits cannot be more than 4.0 or less than 1.0");
						numCredits = Integer.parseInt(processInput.getNum("Number of Credits: ", 1));
					}
					
					//Letter Grade
					String letterGrade = processInput.getString("Enter Letter Grade (i.e. A-F)");
//					while (letterGrade == "F" || letterGrade == "D" || letterGrade != "C" || letterGrade != "B" || letterGrade != "A") {
//					while (letterGrade == "F" || letterGrade == "D" || letterGrade != "C" || letterGrade != "B" || letterGrade != "A") {
//						System.out.println("Must be A through F");
//						letterGrade = processInput.getString("Enter Letter Grade (i.e. A-F)");
//					}
					
					//set new course values
					newCourse.setTitle((String) title);
					newCourse.setCredits(numCredits);
					newCourse.setLetterGrade(letterGrade);
					newCourse.setNumberGrade(newCourse.evalNumberGrade(letterGrade));
					
					//add new course back to courses
					courses.addToHead(newCourse);
					courses.head.setCredits(numCredits);
					courses.head.setTitle((String) title);
					courses.head.setLetterGrade(letterGrade);
					courses.head.setNumberGrade(newCourse.evalNumberGrade(letterGrade));
					//add/update courses to the student record
					studentRec.setCourses(courses);
					//update collection
					StudentCollection.put(studentRec.student.id, studentRec); 
				}


				
			} else if (select == 3) {//Delete
				//validate option
				if (StudentCollection.size() == 0) {
					System.out.println("Need to Add a Student before Deleting a Course.");
				} else {
					StudentRecord<Student> studentRec = new StudentRecord<Student>();
					//enter id
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					//validate student
					while (studentRec == null) {
						System.out.println("Student does not exist");
						studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					} 
					//enter course number
					String title = processInput.getAlphaNum("Course Name/ID: ");
					Course temp = new Course(title.toString());
					CourseList courses = new CourseList(studentRec.courses);
					//retreive course
//					Course courseFind = courses.findCourse(courses.head, temp);
					Course courseFind = courses.findCourse(courses.head, title);
					if (courseFind != null) {
						//delete course
						courses.deleteCourse(courseFind);
						//update courses
						CourseList<Object> newCourses = new CourseList<Object>(courses);
						studentRec.setCourses(newCourses);
						//update collection
						StudentCollection.put(studentRec.student.id, studentRec); 						
					} else {
						System.out.println("Did not find course");
					}
					
				}
			
			} else if (select == 4) {//Print Record
				//enter id
				
				if (StudentCollection.size() == 0) {
					System.out.println("No Students added yet");
				} else {
					StudentRecord<Student> studentRec = new StudentRecord<Student>();
					//enter id
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					//validate student
					while (studentRec == null) {
						System.out.println("Student does not exist");
						studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));

					} 
					studentRec.courses.printCourseRecord();					
				}
				
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
	 * @return the courses
	 */
	public CourseList getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(CourseList courses) {
		this.courses = courses;
	}
	
	

}//end student record class
