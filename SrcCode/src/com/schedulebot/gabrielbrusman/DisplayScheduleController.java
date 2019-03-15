package com.schedulebot.gabrielbrusman;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Double.MAX_VALUE;

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


        AcademicTime gradTime = new AcademicTime(myStudent.getGradTime());
        AcademicTime tableStartTime = new AcademicTime(startTime);

        while(tableStartTime.getQuarter() != "Fall"){
            tableStartTime = tableStartTime.reverseTime();
        }
        ///////////////////////////////Label/Textfield attempt below (should be easier to engineer)
        AcademicTime curTime = new AcademicTime(tableStartTime);

        /*for(int i = 0; i < 3; i++) {
            AcademicTime newTime = new AcademicTime(curTime);
            curTime.progressTime();
            Label column = new Label(newTime.getQuarter() + " " + newTime.getYear()); //set title
            displayVBox.getChildren().add(column);
        }*/
        HBox yearBox = new HBox();
       //while(!curTime.equals(gradTime) || !curTime.getQuarter().equals("Spring")){
        while(curTime.getYear() != gradTime.getYear() || (curTime.getYear() == gradTime.getYear() && !curTime.getQuarter().equals("Fall"))){
           if(curTime.getQuarter().equals("Fall")) {
               yearBox = new HBox();
               //yearBox.setMaxWidth();
               displayVBox.getChildren().add(yearBox);
           }
           AcademicTime newTime = new AcademicTime(curTime);
           curTime = new AcademicTime(curTime.progressTime());
           //Label column = new Label(newTime.getQuarter() + " " + newTime.getYear()); //set title
           //column.setFont(Font.font("Modena", FontWeight.BOLD, 16));
           //column.setMaxWidth(MAX_VALUE);
           //column.setAlignment(Pos.CENTER);
           //yearBox.setHgrow(column, Priority.ALWAYS);
           //yearBox.getChildren().add(column);
            VBox blockBox = new VBox();
            Label title = new Label(curTime.getQuarter() + " " + curTime.getYear());
            title.setFont(Font.font("Modena", FontWeight.BOLD, 16));
            title.setMaxWidth(MAX_VALUE);
            title.setAlignment(Pos.CENTER);
            blockBox.getChildren().add(title);
           if(schedule.containsKey(curTime)){
               TextField course0 = new TextField(schedule.get(curTime).getCourses().get(0).getName());
               course0.setEditable(false);
               blockBox.getChildren().add(course0);
               if(schedule.get(curTime).getCourses().get(1) != null){
                   TextField course1 = new TextField(schedule.get(curTime).getCourses().get(1).getName());
                   course1.setEditable(false);
                   blockBox.getChildren().add(course1);
               }
               yearBox.getChildren().add(blockBox);
           }
        }


        ///////////////////////////////////////////////////// Table attempt below (hard to engineer)
        //first year tableview
       /* TableView<String> table = new TableView<String>();
        AcademicTime curTime = new AcademicTime(tableStartTime);

        for(int i = 0; i < 3; i++){
            AcademicTime newTime = new AcademicTime(curTime);
            curTime.progressTime();
            TableColumn<String, String> column = new TableColumn<String, String>(newTime.getQuarter() + " " + newTime.getYear()); //set title

            //need to add classes to the column here

            table.getColumns().add(column);
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        displayVBox.getChildren().add(0, table);
        */



    }

    public void setPrevScene(Scene scene){
        prevScene = scene;
    }


}
