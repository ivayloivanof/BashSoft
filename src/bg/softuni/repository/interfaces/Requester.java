package bg.softuni.repository.interfaces;

import bg.softuni.collections.SimpleSortedList;
import bg.softuni.models.SoftUniCourse.interfaces.Course;
import bg.softuni.models.SoftUniStudent.interfaces.Student;

import java.util.Comparator;

/**
 * Created by ivanof on 7/14/16.
 */
public interface Requester {

    void getStudentMarksInCourse(String course, String student);

    void getStudentsByCourse(String course);

    SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> cmp);

    SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> cmp);
}
