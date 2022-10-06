package com.manager.studentmanager.service;

import com.manager.studentmanager.data.Course;
import com.manager.studentmanager.data.Student;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerFileService {

    File dir = new File("logs");

    public JSONArray readFile(String filename) {
        System.out.println("Reading " + filename);
        return new JSONArray();
    }

    public void writeFileStudents(List<Student> students) {
        if (dir.mkdir()) {
            System.out.println("Directory is created");
        }

        File f = new File(dir + "\\students.json");
        String path = f.toString();

        JSONArray jsonArr = f.exists() ? readFile(path) : new JSONArray();
        JSONArray courseArr = new JSONArray();

        for (Student s : students) {
            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("Id", s.getStudent_id());
                jsonObj.put("First name", s.getStudent_fname());
                jsonObj.put("Last name", s.getStudent_lname());
                jsonObj.put("Grade", s.getStudent_grade());
                for (Course iterC : s.getStudent_courses()) {
                    JSONObject courseObj = new JSONObject();
                    courseObj.put("Id", iterC.getCourse_id());
                    courseObj.put("Course name", iterC.getCourse_name());
                    courseObj.put("Course teacher", iterC.getCourse_teacher());
                    courseObj.put("Course class", iterC.getCourse_class());
                    courseArr.put(courseObj);
                }
                jsonObj.put("Courses", courseArr);
                jsonArr.put(jsonObj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try (PrintWriter pw = new PrintWriter(path)) {
            pw.write(jsonArr.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFileCourses(List<Course> courses) {
        if (dir.mkdir()) {
            System.out.println("Directory is created");
        }

        File f = new File(dir + "\\courses.json");
        String path = f.toString();

        JSONArray jsonArr = f.exists() ? readFile(path) : new JSONArray();

        for (Course c : courses) {
            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("Id", c.getCourse_id());
                jsonObj.put("Course name", c.getCourse_name());
                jsonObj.put("Course teacher", c.getCourse_teacher());
                jsonObj.put("Course class", c.getCourse_class());
                jsonArr.put(jsonObj);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try (PrintWriter pw = new PrintWriter(path)) {
                pw.write(jsonArr.toString(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
