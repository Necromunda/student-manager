package com.manager.studentmanager.controller;

import com.manager.studentmanager.data.Course;
import com.manager.studentmanager.data.Student;
import com.manager.studentmanager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerRestController {

    @Autowired
    ManagerService mService;

    @GetMapping("/students")
    public List<Student> managerGetStudents() {
        return mService.getStudents();
    }

    @GetMapping("/courses")
    public List<Course> managerGetCourses() {
        return mService.getCourses();
    }

    @PostMapping("/student")
    public void managerAddStudent(@RequestParam String fname,
                                    @RequestParam String lname,
                                    @RequestParam int grade){
        mService.addStudents(new Student(fname, lname, grade));
    }

    @PostMapping("/course")
    public void managerAddStudent(@RequestParam String course_name,
                                    @RequestParam String course_teacher_name,
                                    @RequestParam String course_class){
        mService.addCourses(new Course(course_name, course_teacher_name, course_class));
    }
}
