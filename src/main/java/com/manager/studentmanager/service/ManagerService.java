package com.manager.studentmanager.service;

import com.manager.studentmanager.data.Course;
import com.manager.studentmanager.data.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public void addStudents(Student p_student) {
        this.students.add(p_student);
    }
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public void addCourses(Course p_course) {
        this.courses.add(p_course);
    }
    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }
}
