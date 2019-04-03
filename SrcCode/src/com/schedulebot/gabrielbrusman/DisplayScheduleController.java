package com.schedulebot.gabrielbrusman;


import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import static java.lang.Double.MAX_VALUE;

public class DisplayScheduleController {
    private static Student cleanStudent = new Student();
    private Student myStudent = new Student();
    private HashMap<String, Course> classesByName = new HashMap<String, Course>(55);
    private ArrayList<Course> coursesOffered = new ArrayList<Course>(55);
    private Scene scene;
    private Scene prevScene;
    private Schedule scheduleData;
    private HashMap<AcademicTime, ScheduleBlock> schedule;
    @FXML private VBox displayVBox;
    @FXML private Button displayBackButton;




    public void initialize(){
    }

    public void initData(Student student, HashMap<String, Course> classesByName, ArrayList<Course> coursesOffered) {
        this.cleanStudent = new Student(student);
        this.myStudent = student;
        this.classesByName = classesByName;
        this.coursesOffered = coursesOffered;
        getScheduleData();
    }

    public void getScheduleData(){
        myStudent = cleanStudent;
        AcademicTime startTime = new AcademicTime(myStudent.getStartTime());
        scheduleData = new Schedule(myStudent, coursesOffered);
        schedule = scheduleData.getSchedule();


        AcademicTime gradTime = new AcademicTime(myStudent.getGradTime());
        AcademicTime tableStartTime = new AcademicTime(startTime);

        while(tableStartTime.getQuarter() != "Fall"){
            tableStartTime = tableStartTime.reverseTime();
        }

        AcademicTime curTime = new AcademicTime(startTime);

        HBox yearBox = new HBox();
        boolean start = true;
        boolean firstYear = true;
        while(curTime.getYear() != gradTime.getYear() || (curTime.getYear() == gradTime.getYear() && !curTime.getQuarter().equals("Fall"))){
           if(curTime.getQuarter().equals("Spring")) { //if set to Fall it displays first year but is off by one, if Spring it just doesn't display first year
               if(firstYear){
                   displayVBox.getChildren().add(yearBox);
                   firstYear = false;
               }
               yearBox = new HBox();
               displayVBox.getChildren().add(yearBox);
           }
           curTime = new AcademicTime(curTime.progressTime());

           if(startTime.getQuarter().equals("Spring")){
               start = false;
           }
           if(start) {
               while (!tableStartTime.equals(curTime)) {
                   VBox blockBox = new VBox();
                   Label title = new Label(tableStartTime.getQuarter() + " " + tableStartTime.getYear());
                   title.setFont(Font.font("Modena", FontWeight.BOLD, 16));
                   title.setMaxWidth(MAX_VALUE);
                   title.setAlignment(Pos.CENTER);
                   title.setVisible(false);
                   blockBox.getChildren().add(title);

                   for (int i = 0; i < 2; i++) {
                       TextField blank = new TextField();
                       blank.setEditable(false);
                       blank.setVisible(false);
                       blockBox.getChildren().add(blank);
                   }

                   tableStartTime = tableStartTime.progressTime();
                   yearBox.getChildren().add(blockBox);
               }
               start = false;
           }

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
    }

    public void setPrevScene(Scene scene){
        prevScene = scene;
    }

    public void switchSceneBackwards(){
        Stage stage = (Stage) displayBackButton.getScene().getWindow();
        stage.setScene(prevScene);

    }

}
