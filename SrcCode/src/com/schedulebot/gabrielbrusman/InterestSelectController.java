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

public class InterestSelectController {
    private Student myStudent = new Student();
    private HashMap<String, Course> classesByName = new HashMap<String, Course>(55);
    private ArrayList<Course> coursesOffered = new ArrayList<Course>(55);
    private Scene scene;
    @FXML
    private Button interestSelectNxt;
    @FXML
    private Button interestSelectBack;
    @FXML
    private GridPane interestSelectGPane;
    private Set interestCBoxList;
    private CheckBox[] interestCBoxArr = new CheckBox[7];


    public void initialize() {
    }

    public void initData(Student student, HashMap<String, Course> classesByName, ArrayList<Course> coursesOffered) {
        this.myStudent = student;
        this.classesByName = classesByName;
        this.coursesOffered = coursesOffered;
    }


    public void addInterests() {
        interestCBoxList = interestSelectGPane.lookupAll("CheckBox"); //get all checkboxes in scene
        interestCBoxList.toArray(interestCBoxArr);
        for (int i = 0; i < 7; i++) { //FIXME: put exact size in i < x otherwise get nullpointer exception
            if (interestCBoxArr[i].isSelected()) { //look at all selected checkboxes
                myStudent.getInterests().add(interestCBoxArr[i].getText()); //add their text to classesTaken
            }
        }
        //switchSceneForwards();
    }


    public void switchSceneBackwards() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("courseselect.fxml"));
            Stage stage = (Stage) interestSelectBack.getScene().getWindow();
            scene = new Scene(loader.load());
            stage.setScene(scene);
            //Controller controller = loader.<Controller>getController();
            //controller.initData(myStudent, classesByName, coursesOffered);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    public void switchSceneForwards() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interestselect.fxml"));
            Stage stage = (Stage) interestSelectNxt.getScene().getWindow();
            scene = new Scene(loader.load());
            stage.setScene(scene);
            InterestSelectController controller = loader.<InterestSelectController>getController();
            controller.initData(myStudent, classesByName, coursesOffered);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}




