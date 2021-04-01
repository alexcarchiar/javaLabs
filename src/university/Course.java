package university;

public class Course {
	String courseName, teacherName;
	StudentExt studentsRegistered[];
	int numStudents;
	int courseCode;
	public static final int maxNumStud = 100;
	public Course(String courseName, String teacherName, int courseCode) {
		this.studentsRegistered = new StudentExt[maxNumStud];
		this.courseName = courseName;
		this.teacherName = teacherName;
		this.courseCode = courseCode;
		this.numStudents = 0;
	}
	public void enroll(StudentExt stud) {
		if(numStudents == maxNumStud) {
			System.out.println("Error: there are too many students enrolled");
			return;
		} else {
			studentsRegistered[numStudents] = stud;
			numStudents++;
			return;
		}
	}
	
	public StudentExt[] getStudents() {
		return studentsRegistered;
	}
	
	public int getNumStudents() {
		return numStudents;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}
	
}
