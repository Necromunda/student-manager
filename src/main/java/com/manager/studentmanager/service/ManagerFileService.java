package com.manager.studentmanager.service;

import com.manager.studentmanager.data.Course;
import com.manager.studentmanager.data.Student;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

@Service
public class ManagerFileService {

    public void writeFileStudents(List<Student> students) {
        File dir = new File("logs");

        if (dir.mkdir()) {
            System.out.println("Directory is created");
        }

        File f = new File(dir + "\\students.json");
        String path = f.toString();

        for (Student s : students) {
            JSONObject json = new JSONObject();
            try {
                json.put("Id", s.getStudent_id());
                json.put("First name", s.getStudent_fname());
                json.put("Last name", s.getStudent_lname());
                json.put("Grade", s.getStudent_grade());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try (PrintWriter out = new PrintWriter(new FileWriter(path, true))) {
                out.write(json.toString(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeFileCourses(List<Course> courses) {
        File dir = new File("logs");

        if (dir.mkdir()) {
            System.out.println("Directory is created");
        }

        File f = new File(dir + "\\courses.json");
        String path = f.toString();

        for (Course c : courses) {
            JSONObject json = new JSONObject();
            try {
                json.put("Id", c.getCourse_id());
                json.put("Course name", c.getCourse_name());
                json.put("Course teacher", c.getCourse_teacher());
                json.put("Course class", c.getCourse_class());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try (PrintWriter out = new PrintWriter(new FileWriter(path, true))) {
                out.write(json.toString(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public JSONArray readFile(String filename) throws Exception {
//        JSONArray ja = new JSONArray();
//        Object obj = new JSONParser().parse(new FileReader(filename));
//        return ja;
//    }

}
