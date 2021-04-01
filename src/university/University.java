package university;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

	String universityName;
	Rector rector;
	StudentExt students[];
	int numStudents;
	CourseExt courses[];
	int numCourses;
	protected static final int minStudId = 10000;
	protected static final int maxNumStud = 1000;
	protected static final int minCourseCode = 10;
	protected static final int maxNumCourse = 50;
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		//TODO: to be implemented
		universityName = name;
		students = new StudentExt[maxNumStud];
		numStudents = 0;
		numCourses = 0;
		courses = new CourseExt[maxNumCourse];
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		//TODO: to be implemented
		return universityName;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		if(rector == null) {
			rector = new Rector(first, last);
		} else {
			rector.setName(first);
			rector.setLastName(last);
		}
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		//TODO: to be implemented
		return rector.getName() + " " + rector.getLastName();
	}
	
	/**
	 * Enrol a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		//TODO: to be implemented
		if(numStudents == maxNumStud) {
			System.out.println("Exceeded maximum number of students already");
			return -1;
		} else {
			int id = numStudents + minStudId;
			students[numStudents] = new StudentExt(id, first, last);
			numStudents++;
			return id;
		}
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		//TODO: to be implemented
		int i = id-minStudId;
		return students[i].getId() + " " + students[i].getName() + " " + students[i].getLastName();
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		//TODO: to be implemented
		if(numCourses == maxNumCourse) {
			System.out.println("Sorry, you already have too many courses");
			return -1;
		} else {
			int code;
			code = numCourses + minCourseCode;
			courses[numCourses] = new CourseExt(title, teacher, code);
			numCourses++;
			return code;
		}
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		int i = code - minCourseCode;
		return courses[i].getCourseCode() + ", " + courses[i].getCourseName() + ", " + courses[i].getTeacherName();
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		int studIndex = studentID - minStudId;
		int courseIndex = courseCode - minCourseCode;
		
		if(students[studIndex] == null || courses[courseIndex] == null) {
			System.out.println("Error: insert valid student id and course code");
			return;
		} else {
			if(courses[courseIndex].getNumStudents() < Course.maxNumStud && students[studIndex].getNumCourses() < Student.maxNumCourses)
			students[studIndex].enroll(courses[courseIndex]);
			courses[courseIndex].enroll(students[studIndex]);
			return;
		}
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		//TODO: to be implemented
		int courseIndex = courseCode - minCourseCode;
		
		if(courses[courseIndex] == null) {
			System.out.println("Error: insert valid course code");
			return null;
		} else {
			int numStudents = courses[courseIndex].getNumStudents();
			Student[] attendees = courses[courseIndex].getStudents();
			String listAtt = "";
			for(int i = 0; i<numStudents; i++) {
				listAtt += this.student(attendees[i].getId()) + "\n";
			}
			return listAtt;
		}
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		int studIndex = studentID - minStudId;
		if(students[studIndex] == null) {
			System.out.println("Error: insert valid student id");
			return null;
		} else {
			int numCourses = students[studIndex].getNumCourses();
			Course[] registeredCourses = students[studIndex].getCourses();
			String listCourses = "";
			for(int i = 0; i<numCourses; i++) {
				listCourses += this.course(registeredCourses[i].getCourseCode());
				listCourses += "\n";
			}
			return listCourses;
		}
	}
}
