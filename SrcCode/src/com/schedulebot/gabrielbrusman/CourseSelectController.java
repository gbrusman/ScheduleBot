package com.schedulebot.gabrielbrusman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CourseSelectController {
    private Scene scene;
    private Student myStudent = new Student();
    private HashMap<String, Course> classesByName = new HashMap<String, Course>(55);
    private ArrayList<Course> coursesOffered = new ArrayList<Course>(55);
    private Set courseCBoxList;
    private CheckBox[] courseCBoxArr = new CheckBox[60];
    @FXML
    private GridPane courseSelectGPane;
    @FXML private Button courseSelectNxt;
    @FXML private Button courseSelectBack;
    private Scene prevScene;

    public void initialize(){}

    public void initData(Student student, HashMap<String, Course> classesByName, ArrayList<Course> coursesOffered){
        this.myStudent = student;
        this.classesByName = classesByName;
        this.coursesOffered = coursesOffered;
    }

    //for checkboxes, make list of all of them, loop through, if true, get its text and add it to classesTaken from classesByName
    public void addClassesToTaken(){
        courseCBoxList = courseSelectGPane.lookupAll("CheckBox"); //get all checkboxes in scene
        courseCBoxList.toArray(courseCBoxArr);
        for(int i = 0; i < 53; i++){ //FIXME: put exact size in i < x otherwise get nullpointer exception
            if(courseCBoxArr[i].isSelected()){ //look at all selected checkboxes
                myStudent.getClassesTaken().put(courseCBoxArr[i].getText(), classesByName.get(courseCBoxArr[i].getText())); //add their text to classesTaken
            }
        }
        switchSceneForwards();
    }

    //FIXME: This one works
    /*public void switchSceneBackwards(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("majselect.fxml"));
            Stage stage = (Stage) courseSelectBack.getScene().getWindow();
            scene = new Scene(loader.load());
            stage.setScene(scene);
            //Controller controller = loader.<Controller>getController();
            //controller.initData(myStudent, classesByName, coursesOffered);
        }catch (IOException io){
            io.printStackTrace();
        }

    }*/

    public void switchSceneBackwards(){
        Stage stage = (Stage) courseSelectBack.getScene().getWindow();
        stage.setScene(prevScene);

    }

    public void switchSceneForwards(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interestselect.fxml"));
            Stage stage = (Stage) courseSelectNxt.getScene().getWindow();
            scene = new Scene(loader.load());
            stage.setScene(scene);
            InterestSelectController controller = loader.<InterestSelectController>getController();
            controller.initData(myStudent, classesByName, coursesOffered);
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    public void setPrevScene(Scene scene){
        prevScene = scene;
    }


    //switches to new scene based on fxml file name input
   /* public void switchScene(String fxml){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Stage stage = (Stage) majSelectNxt.getScene().getWindow();
            scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }*/
}
