package com.manager.studentmanager.data;

public class Course {

    private String course_name;
    private String course_teacher;
    private String course_class;
    private int course_id;
    private static int course_id_counter = 1;

    public Course() {

    }

    public Course(String p_course_name, String p_course_teacher, String p_course_class) {
        setCourse_id(Course.course_id_counter++);
        setCourse_name(p_course_name);
        setCourse_teacher(p_course_teacher);
        setCourse_class(p_course_class);
    }

    // Setters and getters for course attributes
    public String getCourse_name() {
        return course_name;
    }
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_teacher() {
        return course_teacher;
    }
    public void setCourse_teacher(String course_teacher) {
        this.course_teacher = course_teacher;
    }

    public String getCourse_class() {
        return course_class;
    }
    public void setCourse_class(String course_class) {
        this.course_class = course_class;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
