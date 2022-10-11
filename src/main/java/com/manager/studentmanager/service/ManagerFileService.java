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

    File dir = new File("logs");

    public List<Student> JsonToList(JSONArray arr) {
        ArrayList<Student> students = new ArrayList<>();
        JSONObject jObj;
        try {
            for (int i=0, l=arr.length(); i<l; i++){
                jObj = (JSONObject) arr.get(i);
                students.add(new Student(
                        jObj.get("First name").toString(),
                        jObj.get("Last name").toString(),
                        Integer.parseInt(jObj.get("Grade").toString())
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return students;
    }

    public JSONArray readFile(File filename) {
        if (filename.exists()) {
            System.out.println("Reading " + filename);
            //JSON parser object to parse read file
            JSONParser jsonParser = new JSONParser();

            try (FileReader reader = new FileReader(filename))
            {
                //Read JSON file
                Object obj = jsonParser.parse(reader);
                //System.out.println(obj.toString());
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
        } else {
            System.out.println("Error accessing " + filename);
        }
        return new JSONArray();
    }

    public void writeFileStudents(List<Student> students) {
        if (dir.mkdir()) {
            System.out.println("Directory created");
        }

        File f = new File(dir + "\\students.json");
        String path = f.toString();

        JSONArray jsonArr = f.exists() ? readFile(f) : new JSONArray();
        List <Student> fileStudents = JsonToList(jsonArr);
        fileStudents.addAll(students);
        JSONArray courseArr = new JSONArray();

        for (Student s : fileStudents) {
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
            System.out.println("Directory created");
        }

        File f = new File(dir + "\\courses.json");
        String path = f.toString();

        JSONArray jsonArr = f.exists() ? readFile(f) : new JSONArray();

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
