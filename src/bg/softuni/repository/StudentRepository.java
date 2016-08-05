package bg.softuni.repository;

import bg.softuni.collections.SimpleSortedList;
import bg.softuni.exceptions.CannotAccessFileException;
import bg.softuni.exceptions.DataAlreadyInitializedException;
import bg.softuni.exceptions.DataNotInitializedException;
import bg.softuni.io.OutputWriter;
import bg.softuni.models.SoftUniCourse.SoftUniCourse;
import bg.softuni.models.SoftUniCourse.interfaces.Course;
import bg.softuni.models.SoftUniStudent.SoftUniStudent;
import bg.softuni.models.SoftUniStudent.interfaces.Student;
import bg.softuni.repository.interfaces.DataFilter;
import bg.softuni.repository.interfaces.DataSorter;
import bg.softuni.repository.interfaces.Database;
import bg.softuni.staticData.ExceptionMessages;
import bg.softuni.staticData.SessionData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentRepository implements Database {

    private boolean isDataInitialized = false;
    private Map<String, Course> courses;
    private Map<String, Student> students;
    private DataFilter repositoryFilter;
    private DataSorter repositorySorter;

    public StudentRepository(DataFilter repositoryFilter, DataSorter repositorySorter) {
        this.setDataFilter(repositoryFilter);
        this.setDataSorter(repositorySorter);
    }

    protected DataFilter getDataFilter() {
        return this.repositoryFilter;
    }

    private void setDataFilter(DataFilter repositoryFilter) {
        this.repositoryFilter = repositoryFilter;
    }

    protected DataSorter getDataSorter() {
        return this.repositorySorter;
    }

    private void setDataSorter(DataSorter repositorySorter) {
        this.repositorySorter = repositorySorter;
    }

    private boolean isQueryForCoursePossible(String courseName) {
        if (!this.isDataInitialized) {
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }
        if (!this.courses.containsKey(courseName)) {
            OutputWriter.displayException(ExceptionMessages.NOT_EXISTING_COURSE);
            return false;
        }

        return true;
    }

    private boolean isQueryForStudentPossible(String courseName, String studentName) {
        if (!this.isQueryForCoursePossible(courseName)) {
            return false;
        }
        if (!this.courses.get(courseName).getStudentByName().containsKey(studentName)) {
            OutputWriter.displayException(ExceptionMessages.NOT_EXISTING_STUDENT);
            return false;
        }

        return true;
    }

    private void readData(String fileName) throws IOException {
        String regex = "([A-Z][a-zA-Z#\\+]*_[A-Z][a-z]{2}_\\d{4})\\s+([A-Za-z]+\\d{2}_\\d{2,4})\\s([\\s0-9]+)";
        Pattern pattern = Pattern.compile(regex);

        String path = SessionData.currentPath + "\\" + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (line.isEmpty() || !matcher.find()) {
                continue;
            }

            String courseName = matcher.group(1);
            String studentName = matcher.group(2);
            String scoresStr = matcher.group(3);
            try {
                int[] scores = Arrays.stream(scoresStr.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                if (Arrays.stream(scores).anyMatch(score -> score > 100 || score < 0)) {
                    OutputWriter.displayException(ExceptionMessages.INVALID_SCORE);
                    continue;
                }
                if (scores.length > SoftUniCourse.NUMBER_OF_TASKS_ON_EXAM) {
                    OutputWriter.displayException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
                    continue;
                }
                if (!this.students.containsKey(studentName)) {
                    this.students.put(studentName, new SoftUniStudent(studentName));
                }
                if (!this.courses.containsKey(courseName)) {
                    this.courses.put(courseName, new SoftUniCourse(courseName));
                }

                Course softUniCourse = this.courses.get(courseName);
                Student softUniStudent = this.students.get(studentName);
                softUniStudent.enrollInCourse(softUniCourse);
                softUniStudent.setMarksInCourse(softUniCourse.getName(), scores);
                softUniCourse.enrollStudent(softUniStudent);
            } catch (NumberFormatException e) {
                OutputWriter.displayException(e.getMessage());
            }
        }

        OutputWriter.writeMessageOnNewLine("Data read.");
    }

    @Override
    public void unloadData() {
        if (!this.isDataInitialized) {
            throw new DataNotInitializedException();
        }

        this.courses = null;
        this.students = null;
        this.isDataInitialized = false;
    }

    @Override
    public void loadData(String fileName) {
        if (this.isDataInitialized) {
            throw new DataAlreadyInitializedException();
        }

        this.courses = new LinkedHashMap<>();
        this.students = new LinkedHashMap<>();
        try {
            this.readData(fileName);
            this.isDataInitialized = true;
        } catch (IOException e) {
            throw new CannotAccessFileException();
        }
    }

    @Override
    public void getStudentMarksInCourse(String course, String student) {
        if (this.isQueryForStudentPossible(course, student)) {
            return;
        }
        double mark = this.courses.get(course).getStudentByName().get(student).getMarksByCourseName().get(course);
        OutputWriter.printStudent(student, mark);
    }

    @Override
    public void getStudentsByCourse(String course) {
        if (!this.isQueryForCoursePossible(course)) {
            return;
        }
        OutputWriter.writeMessageOnNewLine(course + ":");
        for (Map.Entry<String, Student> student : this.courses.get(course).getStudentByName().entrySet()) {
            this.getStudentMarksInCourse(course, student.getKey());
        }
    }

    @Override
    public SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> cmp) {
        SimpleSortedList<Course> courseSortedList = new SimpleSortedList<>(Course.class, cmp);
        courseSortedList.addAll(this.courses.values());

        return courseSortedList;
    }

    @Override
    public SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> cmp) {
        SimpleSortedList<Student> studentSortedList = new SimpleSortedList<>(Student.class, cmp);
        studentSortedList.addAll(this.students.values());

        return studentSortedList;
    }

    @Override
    public void filterAndTake(String courseName, String filter) {
        int studentsToTake = this.courses.get(courseName).getStudentByName().size();
        this.filterAndTake(courseName, filter, studentsToTake);
    }

    @Override
    public void filterAndTake(String courseName, String filter, int studentsToTake) {
        if (!this.isQueryForCoursePossible(courseName)) {
            return;
        }
        HashMap<String, Double> marks = new LinkedHashMap<>();
        for (Map.Entry<String, Student> entry : this.courses.get(courseName).getStudentByName().entrySet()) {
            marks.put(entry.getKey(), entry.getValue().getMarksByCourseName().get(courseName));
        }
        this.getDataFilter().printFilteredStudents(marks, filter, studentsToTake);
    }

    @Override
    public void orderAndTake(String courseName, String orderType) {
        int studentsToTake = this.courses.get(courseName).getStudentByName().size();
        this.orderAndTake(courseName, orderType, studentsToTake);
    }

    @Override
    public void orderAndTake(String courseName, String orderType, int studentsToTake) {
        if (!this.isQueryForCoursePossible(courseName)) {
            return;
        }
        HashMap<String, Double> marks = new LinkedHashMap<>();
        for (Map.Entry<String, Student> entry : this.courses.get(courseName).getStudentByName().entrySet()) {
            marks.put(entry.getKey(), entry.getValue().getMarksByCourseName().get(courseName));
        }
        this.getDataSorter().printSortedStudents(marks, orderType, studentsToTake);
    }
}