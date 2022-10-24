package com.manager.studentmanager.service;

import com.manager.studentmanager.data.Course;
import com.manager.studentmanager.data.Student;

import org.json.JSONException;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class ManagerFileService {

    // Filepaths
    File directory = new File("logs");
    File studentFile = new File(directory + "\\students.json");
    File courseFile = new File(directory + "\\courses.json");

    // Function to convert JSONArray to List<Student>
    public List<Student> StudentJsonToList(JSONArray arr) {
        ArrayList<Student> students = new ArrayList<>();
        JSONObject studentObj;
        JSONObject courseObj;
        try {
            // Loop through student array
            for (int i = 0; i < arr.length(); i++) {
                // Construct a student object from the JSON object
                studentObj = (JSONObject) arr.get(i);
                Student s = new Student();
                s.setStudent_id(studentObj.optInt("Id"));
                s.setStudent_fname(studentObj.optString("First name"));
                s.setStudent_lname(studentObj.optString("Last name"));
                s.setStudent_grade(studentObj.optInt("Grade"));
                // Get JSONArray from "courses" JSON attribute and loop through it
                JSONArray courseArr = new JSONArray(studentObj.get("Courses").toString());
                for (int j = 0; j < courseArr.length(); j++) {
                    // Construct a course object from the JSON object and add it to the student
                    Course c = new Course();
                    courseObj = (JSONObject) courseArr.get(j);
                    c.setCourse_id(courseObj.optInt("Id"));
                    c.setCourse_name(courseObj.optString("Course name"));
                    c.setCourse_teacher(courseObj.optString("Course teacher"));
                    c.setCourse_class(courseObj.optString("Course class"));
                    s.setStudent_courses(c);
                }
                students.add(s);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Function to convert JSONArray to List<Course>
    public List<Course> CourseJsonToList(JSONArray arr) {
        ArrayList<Course> courses = new ArrayList<>();
        JSONObject courseObj;
        try {
            // Loop through the course array
            for (int i = 0; i < arr.length(); i++) {
                // Construct a course object from the JSON object
                courseObj = (JSONObject) arr.get(i);
                Course c = new Course();
                c.setCourse_id(courseObj.optInt("Id"));
                c.setCourse_name(courseObj.optString("Course name"));
                c.setCourse_teacher(courseObj.optString("Course teacher"));
                c.setCourse_class(courseObj.optString("Course class"));
                courses.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return courses;
    }

    // Function to read a .json file
    public JSONArray readFile(File filename) {
        System.out.println("Reading " + filename);
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file and return the content as JSONArray
            Object obj = jsonParser.parse(reader);
            JSONTokener tokener = new JSONTokener(obj.toString());
            return new JSONArray(tokener);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // If filename cant be read, returns an empty JSONArray
        return new JSONArray();
    }

    // Function to write a new student object to a .json file
    public void writeFileStudents(Student student) {
        // Check if "logs" directory exists
        if (directory.mkdir()) {
            System.out.println("Directory created");
        }

        String path = studentFile.toString();

        // Read students.json if it exists or make a new JSONArray
        JSONArray studentArr = studentFile.exists() ? readFile(studentFile) : new JSONArray();
        student.setStudent_id(studentArr.length() + 1);

        // Convert student object to a JSON object and add it to JSONArray
        JSONObject studentObj = new JSONObject();
        try {
            studentObj.put("Id", student.getStudent_id());
            studentObj.put("First name", student.getStudent_fname());
            studentObj.put("Last name", student.getStudent_lname());
            studentObj.put("Grade", student.getStudent_grade());
            studentObj.put("Courses", student.getStudent_courses());
            studentArr.put(studentObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Write the JSONArray to the file
        try (PrintWriter pw = new PrintWriter(path)) {
            pw.write(studentArr.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to write a new course object to a .json file
    public void writeFileCourses(Course course) {
        // Check if "logs" directory exists
        if (directory.mkdir()) {
            System.out.println("Directory created");
        }

        String path = courseFile.toString();

        // Read courses.json if it exists or make a new JSONArray
        JSONArray courseArr = courseFile.exists() ? readFile(courseFile) : new JSONArray();
        course.setCourse_id(courseArr.length() + 1);

        // Convert course object to a JSON object and add it to JSONArray
        JSONObject courseObj = new JSONObject();
        try {
            courseObj.put("Id", course.getCourse_id());
            courseObj.put("Course name", course.getCourse_name());
            courseObj.put("Course teacher", course.getCourse_teacher());
            courseObj.put("Course class", course.getCourse_class());
            courseArr.put(courseObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Write the JSONArray to the file
        try (PrintWriter pw = new PrintWriter(path)) {
            pw.write(courseArr.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to write courses to a students "courses" JSON attribute
    public void writeFileCourseToStudent(int p_id, Course p_course) {
        // Read students.json to get an array of the students
        JSONArray studentArr = readFile(studentFile);
        try {
            // Loop through the students
            for (int i = 0; i < studentArr.length(); i++) {
                JSONObject studentObj = (JSONObject) studentArr.get(i);
                JSONArray courseArr = new JSONArray(studentObj.get("Courses").toString());
                // If given student ID matches, convert course object to JSON object
                if (Integer.parseInt(studentObj.get("Id").toString()) == p_id) {
                    JSONObject courseObj = new JSONObject();
                    courseObj.put("Id", p_course.getCourse_id());
                    courseObj.put("Course name", p_course.getCourse_name());
                    courseObj.put("Course teacher", p_course.getCourse_teacher());
                    courseObj.put("Course class", p_course.getCourse_class());
                    courseArr.put(courseObj);
                    studentObj.put("Courses", courseArr);

                    // Write course info to students.json
                    try (PrintWriter pw = new PrintWriter(studentFile.toString())) {
                        pw.write(studentArr.toString(2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}