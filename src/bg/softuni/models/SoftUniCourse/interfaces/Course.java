package bg.softuni.models.SoftUniCourse.interfaces;

import bg.softuni.models.SoftUniStudent.interfaces.Student;

import java.util.Map;

/**
 * Created by ivanof on 7/14/16.
 */
public interface Course extends Comparable<Course> {

    String getName();

    Map<String, Student> getStudentByName();

    void enrollStudent(Student softUniStudent);
}
