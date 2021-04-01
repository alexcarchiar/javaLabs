package university;

public class Student {
	int id;
	String name, lastName;
	Course coursesRegistered[];
	int numCourses;
	public static final int maxNumCourses = 25;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Student(int id, String name, String lastName) {
		this.coursesRegistered = new Course[maxNumCourses];
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		numCourses = 0;
	}
	
	public void enroll(Course course) {
		if(numCourses == maxNumCourses) {
			System.out.println("This student studies too much already");
			return;
		} else {
			this.coursesRegistered[numCourses] = course;
			numCourses++;
			return;
		}
	}
	
	public int getNumCourses(){
		return numCourses;
	}
	
	public Course[] getCourses() {
		return coursesRegistered;
	}
}
