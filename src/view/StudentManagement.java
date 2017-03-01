package view;

import model.CourseList.Course;
import model.CourseList;
import model.Student;

import java.util.ArrayList;
import java.util.HashMap;

import dhl.UserInputHandler;

/**
 * @ClassName StudentManagement
 * @author Matthew Tse
 * @Method main
 * @Method exit
 *
 */
public class StudentManagement<E> {

	public Student student = new Student();
	public CourseList<E> courses = new CourseList<E>();

	// Main method
	public static void main(String[] args) {

		// initialize and declare global vars
		int completeFlag = 0;
		String optionOne = "(1) Add a Student";
		String optionTwo = "(2) Add a Course to Student Record";
		String optionThree = "(3) Delete a Student";
		String optionFour = "(4) Find a Student";
		String optionFive = "(5) Display Student Information";
		String optionZero = "(0) Exit the program";
		String option = "";

		// instantiate the handler
		UserInputHandler<String> processInput = new UserInputHandler<String>();
		HashMap<String, StudentManagement> StudentCollection = new HashMap<String, StudentManagement>();
		// Option selector
		while (completeFlag == 0) {

			System.out.println("Please select an option.");

			option = processInput.getNum(
					(optionOne.concat("\n")
							.concat(optionTwo).concat("\n")
							.concat(optionThree).concat("\n")
							.concat(optionFour).concat("\n")
							.concat(optionFive).concat("\n")
					.concat("\n").concat(optionZero).concat("\t")), 0);

			int select = Integer.parseInt(option);
			
			if (select > 6 || select < 0) {// Valid options

				System.out.print("Please select a valid option");
			
			} else if (select == 1) {//Add a Student
				//instantiate new student record
				StudentManagement<Student> studentRec = new StudentManagement<Student>();	
				
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
					studentRec.student.setMajor(processInput.getString("Enter Major Name: "));
					//validate student Major
					while (studentRec.student.getMajor().length() == 0) {
						System.out.println("Did not Enter Any Value");
						studentRec.student.setMajor((String) processInput.getString("Enter Major: "));
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
					StudentManagement<Student> studentRec = new StudentManagement<Student>();
					//enter id
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					//Check Student ID
					if (studentRec == null) {
						System.out.println("Student does not exist");
						
					} else {
						//enter course number
						String courseNumber = processInput.getAlphaNum("Course Number (i.e. CS342): ");
						//retrieve course list on student record
						CourseList<Object> courses = new CourseList<Object>(studentRec.courses);
						//find course pointer
						Course<Object> courseFind = courses.findCourse(courses.head, courseNumber);
						if (courseFind != null) {//validate new course
							System.out.println("Course Number already exists on this student record");
						} else {
							String courseName = processInput.getString("Enter Course Name: ");
							while (courseName.length() == 0) {
								System.out.println("Did not Enter Any Value");
								courseName = (processInput.getString("Enter Course Name: "));
							}
							//instantiate new course
							Course<String> newCourse = new Course<String>(courseNumber.toString());
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
							newCourse.setCourseNumber(((String) courseNumber));
							newCourse.setCourseName(courseName);
							newCourse.setNumberOfCredits(numCredits);
							newCourse.setGrade(letterGrade.toUpperCase().charAt(0));
							newCourse.setNumberGrade(newCourse.evalNumberGrade(letterGrade.toUpperCase()));
							
							//add new course back to courses
							courses.addToHead(newCourse);
							courses.head.setNumberOfCredits(numCredits);
							courses.head.setCourseNumber((String) courseNumber);
							courses.head.setCourseName(courseName);
							courses.head.setGrade(letterGrade.toUpperCase().charAt(0));
							courses.head.setNumberGrade(newCourse.evalNumberGrade(letterGrade.toUpperCase()));
							//add/update courses to the student record
							studentRec.setCourses(courses);
							//update collection
							StudentCollection.put(studentRec.student.id, studentRec);
							System.out.println("Successful");
							
						}//end if new course validation
						
					}//end student validation
					

				}//end if student collection size check

			} else if (select == 3) {//Delete
				//validate option
				if (StudentCollection.size() == 0) {
					System.out.println("Need to Add a Student before Deleting.");
				} else {
					//instantiate new student record
					StudentManagement<Student> studentRec = new StudentManagement<Student>();
					//enter id to set student record file
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					//validate student
					if (studentRec != null) {
						//delete Student
						StudentCollection.remove(studentRec.student.id);
						System.out.println("Successful");						

					} else {
						System.out.println("Student does not exist");
						
					}//end student validation
					
				}//end if student collection size check
			
			} else if (select == 4) {//Find Student
				//enter id				
				if (StudentCollection.size() == 0) {
					System.out.println("No Students Found");
				} else {
					StudentManagement<Student> studentRec = new StudentManagement<Student>();
					//enter id
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					//validate student
					if (studentRec != null) {
						studentRec.printStudent(studentRec.student);			
						
					} else {
						System.out.println("Student does not exist");
					}//end student validation

				}//end if student collection size check
			} else if (select == 5) {//Display Student Information
				//enter id				
				if (StudentCollection.size() == 0) {
					System.out.println("No Students Found");
				} else {
					StudentManagement<Student> studentRec = new StudentManagement<Student>();
					//enter id
					studentRec = StudentCollection.get(processInput.getAlphaNum("Student ID: "));
					//validate student
					if (studentRec != null) {
						//retrieve course list on student record
						CourseList<Student> courses = new CourseList<Student>(studentRec.courses);
						studentRec.printStudentRecord(courses);							
					} else {
						System.out.println("Student does not exist");
										
					}//end student validation

				}//end if student collection size check	
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
	 * @Name: printStudent
	 * 
	 * @Function/Purpose: prints a single student entry
	 * 
	 * @Parameters:
	 * 		{object class Student} student
	 * @Additionl Comments: All input must be passed as student
	 * 
	 * @Return void
	 */
	private void printStudent(Student student) {
		System.out.println("Student ID: " + student.id 
				+ ", Student Name: " + student.getFName() + " " + student.getLName()
				+ ", Student Major: " + student.major);
		
	}
	
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
		//check list size
		if (courseList.size > 0) {
			ArrayList<Integer> cntr = new ArrayList<Integer>();
			//print size
			System.out.print("Number of classes = " + courseList.size + "\n");
			printStudent(student);
			if (courseList != null){
				//get current
				Course<E> current = courseList.head;	
				
			    while (current != null){
			    	//add each grade to list
			    	cnt.add(current.getNumberGrade());
			    	//print title
			    	System.out.println(current.courseNumber
			    			+ ", " + current.courseName
			    			+ ", " + current.grade);
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
			    System.out.println("\nCumulative GPA: " + newRec.getGradePointAverage());

			}
			
		} else {
			System.out.println("No Courses to Display");
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
