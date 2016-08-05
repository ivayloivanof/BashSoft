package bg.softuni.models.SoftUniCourse;

import bg.softuni.exceptions.DuplicateEntryInStructureException;
import bg.softuni.exceptions.InvalidStringException;
import bg.softuni.models.SoftUniCourse.interfaces.Course;
import bg.softuni.models.SoftUniStudent.interfaces.Student;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ivanof on 6/25/16.
 */
public class SoftUniCourse implements Course {

    public static final int NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final int MAX_SCORE_ON_EXAM_TASK = 100;

    private String name;
    private Map<String, Student> studentByName;

    public SoftUniCourse(String name) {
        this.setName(name);
        this.studentByName = new LinkedHashMap<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new InvalidStringException();
        }
        this.name = name;
    }

    @Override
    public Map<String, Student> getStudentByName() {
        return Collections.unmodifiableMap(this.studentByName);
    }

    @Override
    public void enrollStudent(Student softUniStudent) {
        if (this.studentByName.containsKey(softUniStudent.getUserName())) {
            throw new DuplicateEntryInStructureException(softUniStudent.getUserName(), this.getName());
        }

        this.studentByName.put(softUniStudent.getUserName(), softUniStudent);
    }

    @Override
    public int compareTo(Course course) {
        return this.getName().compareTo(course.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
