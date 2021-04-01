package university;

import java.util.HashMap;

public class StudentExt extends Student {

	private HashMap<Integer, Integer> grades = new HashMap<Integer, Integer>();
	private float score;
	public StudentExt(int id, String name, String lastName) {
		super(id, name, lastName);
		// TODO Auto-generated constructor stub
		score = 0;
	}
	public float getScore() {
		return score;
	}
	public boolean registerExam(int examCode, int grade) {
		boolean examAlreadyGraded = grades.containsValue(examCode);
		if(examAlreadyGraded) {
			return false;
		} else {
			grades.put(examCode, grade);
			return true;
		}
	}
	
	public float studentAvg() {
		int numExams = grades.size();
		if(numExams == 0) {
			return 0;
		} else {
			float avg = 0;
			
			for(int k: grades.keySet()) {
				avg += grades.get(k);
			}
			
			return avg/numExams;
		}
	}
	
	public int getGrade(int courseCode) {
		Object flag = grades.get(courseCode);
		if(flag == null) {
			return 0;
		} else {
			int gr = (int)grades.get(courseCode);
			return gr;
		}
	}
	
	public float computeScore() {
		if(numCourses == 0) {
			return 0;
		}
		
		int numPassedExams = grades.size();
		if(numPassedExams == 0) {
			return 0;
		}
		score = this.studentAvg() + numPassedExams / this.numCourses * 10;
		return score;
	}
}
