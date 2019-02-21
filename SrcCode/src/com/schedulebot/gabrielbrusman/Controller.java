package com.schedulebot.gabrielbrusman;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {
    public Button myButton;
    public TextArea myTextArea;
    public Student myStudent = new Student();

    public void handleButtonPress(ActionEvent event){
        myTextArea.appendText("Button clicked");
    }
}
