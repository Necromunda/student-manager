package com.manager.studentmanager.service;

import com.manager.studentmanager.data.Course;
import com.manager.studentmanager.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {

    @Autowired
    ManagerFileService mFileService;

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public void addStudents(Student p_student) {

        this.students.add(p_student);
        mFileService.writeFileStudents(this.students);
    }
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public void addCourses(Course p_course) {

        this.courses.add(p_course);
        mFileService.writeFileCourses(this.courses);
    }
    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }
}
