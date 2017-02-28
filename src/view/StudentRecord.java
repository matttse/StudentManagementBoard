package view;

import model.CourseList;
import model.CourseList.Course;
import model.Student;

import java.util.ArrayList;
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
				//instantiate new student record
				StudentRecord<Student> studentRec = new StudentRecord<Student>();	
				
				studentRec.student.setId(processInput.getAlphaNum("Student ID: "));
				//validate student id input
				while (studentRec.student.id.length() == 0) {
					System.out.println("Did not Enter Any Value");
					studentRec.student.setId(processInput.getAlphaNum("Student ID: "));
				}
				//validate if student already exists
				if (StudentCollection.containsKey(studentRec.student.getId()) == false) {					
					studentRec.student.setFName(processInput.getString("Enter First Name: "));
					//validate student fName
					while (studentRec.student.getFName().length() == 0) {
						System.out.println("Did not Enter Any Value");
						studentRec.student.setFName((String) processInput.getString("Enter First Name: "));
					}
					studentRec.student.setLName(processInput.getString("Enter Last Name: "));
					//validate student lName
					while (studentRec.student.getLName().length() == 0) {
						System.out.println("Did not Enter Any Value");
						studentRec.student.setLName((String) processInput.getString("Enter Last Name: "));
					}
					StudentCollection.put(studentRec.student.getId(), studentRec);
				} else {
					System.out.println("Student ID already Exists");
				}// end if student exists validation				
			
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
					//enter course number
					String title = processInput.getAlphaNum("Course Name/ID: ");
					//retrieve course list on student record
					CourseList<Object> courses = new CourseList<Object>(studentRec.courses);
					//find course pointer
					Course<Object> courseFind = courses.findCourse(courses.head, title);
					if (courseFind == null) {//validate new course
						//instantiate new course
						Course<String> newCourse = new Course<String>(title.toString());
						//Number of Credits
						int numCredits = Integer.parseInt(processInput.getNum("Number of Credits: ", 1));
						//validate Credits
						while (numCredits > 4 || numCredits < 1) {
							System.out.println("Credits cannot be more than 4.0 or less than 1.0");
							numCredits = Integer.parseInt(processInput.getNum("Number of Credits: ", 1));
						}
						
						//Letter Grade
						ArrayList<String> letterGradeList = new ArrayList<String>();
						letterGradeList.add("A");
						letterGradeList.add("B");
						letterGradeList.add("C");
						letterGradeList.add("D");
						letterGradeList.add("F");
						
						String letterGrade = processInput.getString("Enter Letter Grade (i.e. A-F)");
						//validate letterGrade
						while (!letterGradeList.contains(letterGrade.toUpperCase())) {
							System.out.println("Must be A through F");
							letterGrade = processInput.getString("Enter Letter Grade (i.e. A-F)");
						}
						
						//set new course values
						newCourse.setTitle((String) title);
						newCourse.setCredits(numCredits);
						newCourse.setLetterGrade(letterGrade.toUpperCase());
						newCourse.setNumberGrade(newCourse.evalNumberGrade(letterGrade.toUpperCase()));
						
						//add new course back to courses
						courses.addToHead(newCourse);
						courses.head.setCredits(numCredits);
						courses.head.setTitle((String) title);
						courses.head.setLetterGrade(letterGrade.toUpperCase());
						courses.head.setNumberGrade(newCourse.evalNumberGrade(letterGrade.toUpperCase()));
						//add/update courses to the student record
						studentRec.setCourses(courses);
						//update collection
						StudentCollection.put(studentRec.student.id, studentRec); 
					} else {
						System.out.println("Course already exists on this student record");
					}//end if new course validation

				}//end if student collection size check

			} else if (select == 3) {//Delete
				//validate option
				if (StudentCollection.size() == 0) {
					System.out.println("Need to Add a Student before Deleting a Course.");
				} else {
					//instantiate new student record
					StudentRecord<Student> studentRec = new StudentRecord<Student>();
					//enter id to set student record file
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					//validate student
					while (studentRec == null) {
						System.out.println("Student does not exist");
						studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					} 
					//enter course number
					String title = processInput.getAlphaNum("Course Name/ID: ");
					//retrieve course list on student record
					CourseList<Object> courses = new CourseList<Object>(studentRec.courses);
					//find course pointer
					Course<Object> courseFind = courses.findCourse(courses.head, title);
					//validate course
					if (courseFind != null) {
						//delete course
						courses.deleteCourse(courses.head, courseFind);
						//update courses
						studentRec.setCourses(courses);
						//update collection
						StudentCollection.put(studentRec.student.id, studentRec); 						
					} else {
						System.out.println("Did not find course");
					}//end if course exists validation
					
				}//end if student collection size check
			
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
					//retrieve course list on student record
					CourseList<Student> courses = new CourseList<Student>(studentRec.courses);
					studentRec.printStudentRecord(courses);					
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
	
	/*
	 * @Name: printStudentRecord
	 * 
	 * @Function/Purpose: prints a single student record
	 * 
	 * @Parameters:
	 * 		{object class CourseList} courseList
	 * @Additionl Comments: All input must be passed as CourseList
	 * 
	 * @Return void
	 */
	public void printStudentRecord(CourseList<E> courseList){
		//list to store integers
		ArrayList<Integer> cnt = new ArrayList<Integer>();
		//instantiate with 10 the list of grades
		//TODO make list size dynamic
		if (courseList.size > 0) {
			ArrayList<Integer> cntr = new ArrayList<Integer>();
			//print size
			System.out.print("Number of nodes = " + courseList.size + "\n");
			if (courseList != null){
				//get current
				Course<E> current = courseList.head;	
				
			    while (current != null){
			    	//add each grade to list
			    	cnt.add(current.getNumberGrade());
			    	//print title
			    	System.out.println(current.title + " ");
			    	//reset to next 
			    	current = (Course<E>) current.getNext();
			    }

			    //set values in grade list
			    for (int i = 0; i < cnt.size(); i++) {
			    	cntr.add(cnt.get(i));
			    }
			    //reset rec
			    Course<E> newRec = courseList.head;
			    //calc avg
			    newRec.setGradePointAverage(newRec.calcAverage(cntr));
			    //print gpa
			    System.out.println("\nGPA: " + newRec.getGradePointAverage());

			}
			
		} else {
			System.out.println("The list is empty");
		}

		System.out.println();
	}

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
