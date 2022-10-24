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
    public String managerAddStudent(@RequestParam String fname,
                                    @RequestParam String lname,
                                    @RequestParam int grade){
        mService.addStudents(new Student(fname, lname, grade));
        return "Student added";
    }

    @PostMapping("/course")
    public String managerAddStudent(@RequestParam String course_name,
                                    @RequestParam String course_teacher_name,
                                    @RequestParam String course_class){
        mService.addCourses(new Course(course_name, course_teacher_name, course_class));
        return "Course added";
    }

    @PostMapping("/courseToStudent")
    public String managerAddCourseToStudent(@RequestParam int student_id,
                                          @RequestParam int course_id) {
        boolean added = mService.addCourseToStudent(student_id, course_id);
        if (added)
            return "Course added to student";
        else
            return "Course could not be added to student. Check your student & course IDs.";
    }
}
