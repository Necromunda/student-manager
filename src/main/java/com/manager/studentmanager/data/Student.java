package com.manager.studentmanager.data;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String student_fname;
    private String student_lname;
    private int student_grade;
    private int student_id;
    private static int student_id_counter = 1;
    private List<Course> student_courses = new ArrayList<>();

    public Student() {
    }

    public Student(String p_student_fname, String p_student_lname, int p_student_grade) {
        setStudent_id(Student.student_id_counter++);
        setStudent_fname(p_student_fname);
        setStudent_lname(p_student_lname);
        setStudent_grade(p_student_grade);
    }
    
    public String getStudent_fname() {
        return student_fname;
    }

    public void setStudent_fname(String student_fname) {
        this.student_fname = student_fname;
    }

    public String getStudent_lname() {
        return student_lname;
    }

    public void setStudent_lname(String student_lname) {
        this.student_lname = student_lname;
    }

    public int getStudent_grade() {
        return student_grade;
    }

    public void setStudent_grade(int student_grade) {
        this.student_grade = student_grade < 1 ? 1 : (student_grade > 5 ? 5 : student_grade);
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public List<Course> getStudent_courses() {
        return new ArrayList<>(student_courses);
    }

    public void setStudent_courses(Course new_course) {
        this.student_courses.add(new_course);
    }
}
