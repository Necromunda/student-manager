package com.manager.studentmanager.service;

import com.manager.studentmanager.data.Course;
import com.manager.studentmanager.data.Student;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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

    public void addCourseToStudent(int p_student_id, int p_course_id) {
        List<Course> m_courses = this.getCourses();
        List<Student> m_students = this.getStudents();

        for (Student iterS : m_students) {
            if (iterS.getStudent_id() == p_student_id) {
                for (Course iterC : m_courses) {
                    if (iterC.getCourse_id() == p_course_id) {
                        iterS.setStudent_courses(iterC);
                        System.out.println("Course added!");
                        mFileService.writeFileStudents(this.students);
                    }
                }
            }
        }
    }
}
