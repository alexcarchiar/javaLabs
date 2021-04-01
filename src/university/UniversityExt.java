package university;

import java.util.logging.Logger;

/**
 * This class is an extended version of the {@Link University} class.
 * 
 *
 */
public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University");

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}

	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		int i = studentId-minStudId;
		if(students[i] == null) {
			logger.info("Error: insert valid student id and course code");
			return;
		}
		boolean examRegistered = students[i].registerExam(courseID, grade);
		if(examRegistered) {
			logger.info("Registered the exam " + courseID + "for student " + studentId);
		}
	}
	
	@Override
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		int studIndex = studentID - minStudId;
		int courseIndex = courseCode - minCourseCode;
		
		if(students[studIndex] == null || courses[courseIndex] == null) {
			logger.info("Error: insert valid student id and course code");
			return;
		} else {
			if(courses[courseIndex].getNumStudents() < Course.maxNumStud && students[studIndex].getNumCourses() < Student.maxNumCourses)
			students[studIndex].enroll(courses[courseIndex]);
			courses[courseIndex].enroll(students[studIndex]);
			logger.info("Student " + studentID + " registered at course " + courseCode);
			return;
		}
	}
	
	@Override
	public int activate(String title, String teacher){
		//TODO: to be implemented
		if(numCourses == maxNumCourse) {
			logger.info("Sorry, you already have too many courses");
			return -1;
		} else {
			int code;
			code = numCourses + minCourseCode;
			courses[numCourses] = new CourseExt(title, teacher, code);
			numCourses++;
			logger.info("Activated the new " + title + " course");
			return code;
		}
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		int i = studentId-minStudId;
		float avg = students[i].studentAvg();
		if(avg == 0) {
			logger.info("The student never took an exam");
			return "Student " + studentId + " hasn't taken any exams";
		} else {
			logger.info("Found the average for student " + studentId);
			return "Student" + studentId + " : " + avg;
		}
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		int courseIndex = courseId - minCourseCode;
		
		if(courses[courseIndex] == null) {
			logger.info("Error: insert valid course code");
			return null;
		}
		float avg = courses[courseIndex].courseAvg();
		if(avg == 0) {
			logger.info("No student has taken the exam " + courses[courseIndex].getCourseName());
			return "No student has taken the exam " + courses[courseIndex].getCourseName();
		} else {
			logger.info("Computed average for course " + courses[courseIndex].getCourseName());
			return "The average for the course " + courses[courseIndex].getCourseName() + " is: " + avg;
		}
	}
	
	@Override
	public int enroll(String first, String last){
		//TODO: to be implemented
		if(numStudents == maxNumStud) {
			logger.info("Exceeded maximum number of students already");
			return -1;
		} else {
			int id = numStudents + minStudId;
			students[numStudents] = new StudentExt(id, first, last);
			numStudents++;
			logger.info("Student " + first + " " + last + "enrolled");
			return id;
		}
	}
	
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info of the best three students.
	 */
	public String topThreeStudents() {
		
		StudentExt[] topStuds = new StudentExt[3];
		float currScore;
		for(int i = 0; i<numStudents; i++) {
			currScore = students[i].computeScore();
			if(currScore == 0) {
				continue;
			}
			if(topStuds[0] == null) {
				topStuds[0] = students[i];
				continue;
			} else if(currScore > topStuds[0].getScore()) {
				topStuds[2] = topStuds[1];
				topStuds[1] = topStuds[0];
				topStuds[0] = students[i];
			} else if(topStuds[1] == null) {
				topStuds[1] = students[i];
				continue;
			} else if(currScore > topStuds[1].getScore()){
				topStuds[2] = topStuds[1];
				topStuds[1] = students[i];
			} else if(topStuds[2] == null) {
				topStuds[2] = students[i];
			} else if(currScore > topStuds[2].getScore()) {
				topStuds[2] = students[i];
			}
		}
		logger.info("Found the top students");
		/*
		 * This was in origin. The tester counts the occurences of \n.
		 * If there are only two top students, it expects only  2 \n
		 */
		 String str = "";
		 
		 for(int i = 0; i<3; i++) {
			 if(topStuds[i] != null) {
				 str += topStuds[i].getName() + " " + topStuds[i].getLastName() + " : " + topStuds[i].getScore() + "\n";
			 }
		   }

		return str;
	}
}
