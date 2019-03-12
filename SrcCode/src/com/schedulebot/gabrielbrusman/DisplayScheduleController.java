package com.schedulebot.gabrielbrusman;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplayScheduleController {
    private Student myStudent = new Student();
    private HashMap<String, Course> classesByName = new HashMap<String, Course>(55);
    private ArrayList<Course> coursesOffered = new ArrayList<Course>(55);
    private Scene scene;
    private Scene prevScene;
    private Scene nextScene;
    private Schedule scheduleData;
    private HashMap<AcademicTime, ScheduleBlock> schedule;
    ArrayList<TableView<String>> tableList = new ArrayList<TableView<String>>();
    @FXML private VBox displayVBox;



    public void initialize(){
      /* AcademicTime startTime = myStudent.getStartTime();
       AcademicTime gradTime = myStudent.getGradTime();
       AcademicTime tableStartTime = startTime;

       while(tableStartTime.getQuarter() != "Fall"){
           tableStartTime.reverseTime();
       }
       //first year tableview
        TableView<String> table = new TableView<String>();
        AcademicTime curTime = new AcademicTime(tableStartTime);
        while(tableStartTime.getQuarter() != startTime.getQuarter()){ //make blank columns until startTime
            AcademicTime newTime = new AcademicTime(curTime);
            curTime.progressTime();
            TableColumn<String, String> column = new TableColumn<String, String>(newTime.getQuarter() + " " + newTime.getYear()); //set title
        }

        //while(tableStartTime.g)



       while(tableStartTime.getYear() != gradTime.getYear()){ //want a table view for every year

       }


       /* schedule.forEach((time, schedBlock) ->

               );*/

    }

    public void initData(Student student, HashMap<String, Course> classesByName, ArrayList<Course> coursesOffered) {
        this.myStudent = student;
        this.classesByName = classesByName;
        this.coursesOffered = coursesOffered;
        getScheduleData();
    }

    public void getScheduleData(){
        AcademicTime startTime = new AcademicTime(myStudent.getStartTime());
        scheduleData = new Schedule(myStudent, coursesOffered);
        schedule = scheduleData.getSchedule();


        AcademicTime gradTime = myStudent.getGradTime();
        AcademicTime tableStartTime = new AcademicTime(startTime);

        while(tableStartTime.getQuarter() != "Fall"){
            tableStartTime.reverseTime();
        }
        //first year tableview
        TableView<String> table = new TableView<String>();
        AcademicTime curTime = new AcademicTime(tableStartTime);

        for(int i = 0; i < 3; i++){
            AcademicTime newTime = new AcademicTime(curTime);
            curTime.progressTime();
            TableColumn<String, String> column = new TableColumn<String, String>(newTime.getQuarter() + " " + newTime.getYear()); //set title
            table.getColumns().add(column);
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        displayVBox.getChildren().add(0, table);

    }

    public void setPrevScene(Scene scene){
        prevScene = scene;
    }


}
