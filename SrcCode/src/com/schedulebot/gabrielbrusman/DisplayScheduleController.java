package com.schedulebot.gabrielbrusman;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayScheduleController {
    private Student myStudent = new Student();
    private HashMap<String, Course> classesByName = new HashMap<String, Course>(55);
    private ArrayList<Course> coursesOffered = new ArrayList<Course>(55);
    private Scene scene;
    private Scene prevScene;
    private Scene nextScene;
    private Schedule scheduleData;
    private HashMap<AcademicTime, ScheduleBlock> schedule;


    public void initialize(){
       /*scheduleData = new Schedule(myStudent, coursesOffered);
       schedule = scheduleData.getSchedule();

       schedule.forEach((time, schedBlock) ->

               );*/

    }

    public void initData(Student student, HashMap<String, Course> classesByName, ArrayList<Course> coursesOffered) {
        this.myStudent = student;
        this.classesByName = classesByName;
        this.coursesOffered = coursesOffered;
    }

    public void setPrevScene(Scene scene){
        prevScene = scene;
    }


}
