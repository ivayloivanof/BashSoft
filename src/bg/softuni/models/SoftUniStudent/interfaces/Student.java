package bg.softuni.models.SoftUniStudent.interfaces;

import bg.softuni.models.SoftUniCourse.interfaces.Course;

import java.util.Map;

/**
 * Created by ivanof on 7/14/16.
 */
public interface Student extends Comparable<Student> {

    String getUserName();

    Map<String, Course> getEnrolledCourses();

    Map<String, Double> getMarksByCourseName();

    void enrollInCourse(Course softUniCourse);

    void setMarksInCourse(String courseName, int... scores);

}
