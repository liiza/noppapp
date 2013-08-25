package DataStructures;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class User implements Serializable {
	public ArrayList<Course> courses = new ArrayList<Course>();

	public User() {

	}

	public void addCourse(Course course) {
		//cannot add the same course twice
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCourse_id() == course.getCourse_id()){
				return;
			}
		}
		courses.add(course);
		

	}

}
