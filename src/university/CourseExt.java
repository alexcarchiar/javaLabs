package university;

public class CourseExt extends Course{

	public CourseExt(String courseName, String teacherName, int courseCode) {
		super(courseName, teacherName, courseCode);
		// TODO Auto-generated constructor stub
	}

	public float courseAvg() {
		
		if(this.getNumStudents() == 0) {
			return 0;
		}
		float avg = 0;
		int cnt = 0;
		for(int i = 0; i<this.getNumStudents(); i++) {
			StudentExt stud = this.studentsRegistered[i];
			Integer gr = stud.getGrade(this.courseCode);
			if(gr != 0) {
				avg += gr;
				cnt++;
			}
		}
		if(cnt != 0) {
			return avg/cnt;
		} else {
			return 0;
		}
	}
}
