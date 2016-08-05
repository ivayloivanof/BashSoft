package bg.softuni.models.SoftUniStudent;

import bg.softuni.exceptions.DuplicateEntryInStructureException;
import bg.softuni.exceptions.InvalidNumberOfScoresException;
import bg.softuni.exceptions.InvalidStringException;
import bg.softuni.exceptions.KeyNotFoundException;
import bg.softuni.models.SoftUniCourse.SoftUniCourse;
import bg.softuni.models.SoftUniCourse.interfaces.Course;
import bg.softuni.models.SoftUniStudent.interfaces.Student;

import java.util.*;

public class SoftUniStudent implements Student {

    private String userName;
    private HashMap<String, Course> enrolledCourses;
    private HashMap<String, Double> marksByCourseName;

    public SoftUniStudent(String userName) {
        this.setUserName(userName);
        this.enrolledCourses = new LinkedHashMap<>();
        this.marksByCourseName = new LinkedHashMap<>();
    }

    @Override
    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        if (userName == null || userName.trim().length() == 0) {
            throw new InvalidStringException();
        }
        this.userName = userName;
    }

    private double calculateMark(int[] scores) {
        double percentageOfSolvedExam = Arrays.stream(scores).sum() /
                (double) (SoftUniCourse.NUMBER_OF_TASKS_ON_EXAM * SoftUniCourse.MAX_SCORE_ON_EXAM_TASK);

        double mark = percentageOfSolvedExam * 4 + 2;
        return mark;
    }

    @Override
    public Map<String, Course> getEnrolledCourses() {
        return Collections.unmodifiableMap(enrolledCourses);
    }

    @Override
    public Map<String, Double> getMarksByCourseName() {
        return Collections.unmodifiableMap(marksByCourseName);
    }

    @Override
    public void enrollInCourse(Course softUniCourse) {
        if (this.enrolledCourses.containsKey(softUniCourse.getName())) {
            throw new DuplicateEntryInStructureException(this.userName, softUniCourse.getName());
        }

        this.enrolledCourses.put(softUniCourse.getName(), softUniCourse);
    }

    @Override
    public void setMarksInCourse(String courseName, int... scores) {
        if (!this.enrolledCourses.containsKey(courseName)) {
            throw new KeyNotFoundException();
        }
        if (scores.length > SoftUniCourse.NUMBER_OF_TASKS_ON_EXAM) {
            throw new InvalidNumberOfScoresException();
        }

        double mark = this.calculateMark(scores);
        this.marksByCourseName.put(courseName, mark);
    }

    @Override
    public int compareTo(Student student) {
        return this.getUserName().compareTo(student.getUserName());
    }

    @Override
    public String toString() {
        return this.getUserName();
    }
}