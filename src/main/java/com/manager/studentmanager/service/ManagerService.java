package com.manager.studentmanager.service;

import com.manager.studentmanager.data.Course;
import com.manager.studentmanager.data.Student;
import org.json.JSONArray;
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

    // Filepaths
    File studentFile = new File("logs\\students.json");
    File courseFile = new File("logs\\courses.json");

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    // Function to add a student to the list and write it to students.json
    public void addStudents(Student p_student) {
        this.students.add(p_student);
        mFileService.writeFileStudents(p_student);
    }

    // Function to get students from students.json and adding them to the list
    public List<Student> getStudents() {
        JSONArray studentArr = mFileService.readFile(studentFile);
        this.students = mFileService.StudentJsonToList(studentArr);
        return new ArrayList<>(students);
    }

    // Function to add a course to the list and write it to courses.json
    public void addCourses(Course p_course) {
        this.courses.add(p_course);
        mFileService.writeFileCourses(p_course);
    }

    // Function to get courses from courses.json and adding them to the list
    public List<Course> getCourses() {
        JSONArray courseArr = mFileService.readFile(courseFile);
        this.courses = mFileService.CourseJsonToList(courseArr);
        return new ArrayList<>(courses);
    }

    // Function to add a course to a student using student ID and course ID
    public void addCourseToStudent(int p_student_id, int p_course_id) {
        // Get students and courses from the .json files
        List<Student> m_students = getStudents();
        List<Course> m_courses = getCourses();

        // Loop through students. If student ID matches, loop through courses.
        // If course ID matches, add the course to the student and write it in the students.json
        for (Student iterS : m_students) {
            if (iterS.getStudent_id() == p_student_id) {
                for (Course iterC : m_courses) {
                    if (iterC.getCourse_id() == p_course_id) {
                        iterS.setStudent_courses(iterC);
                        System.out.println("Course added!");
                        mFileService.writeFileCourseToStudent(iterS.getStudent_id(), iterC);
                    }
                }
            }
        }
    }
}
