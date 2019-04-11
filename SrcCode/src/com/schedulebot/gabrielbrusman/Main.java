package com.schedulebot.gabrielbrusman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
       /* stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("majselect.fxml"));
        stage.setTitle("ScheduleBot");
        stage.setScene(new Scene(root, 300, 275));
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setWidth(500);
        stage.setHeight(500);*/



        //FIXME: Working on loading scenes into each other (NEED TO INCLUDE SETTER METHODS IN CONTROLLERS AND RECONFIGURE SWITCHING SCENE METHODS)
        FXMLLoader majSelectLoader = new FXMLLoader(Main.class.getResource("majselect.fxml"));
        Parent majSelectPane = majSelectLoader.load();
        Scene majSelectScene = new Scene(majSelectPane, 500, 500);

        FXMLLoader courseSelectLoader = new FXMLLoader(Main.class.getResource("courseselect.fxml"));
        Parent courseSelectPane = courseSelectLoader.load();
        Scene courseSelectScene = new Scene(courseSelectPane, 500, 500);

        FXMLLoader interestSelectLoader = new FXMLLoader(Main.class.getResource("interestselect.fxml"));
        Parent interestSelectPane = interestSelectLoader.load();
        Scene interestSelectScene = new Scene(interestSelectPane, 500, 500);

        FXMLLoader displayScheduleLoader = new FXMLLoader(Main.class.getResource("displayschedule.fxml"));
        Parent displaySchedulePane = displayScheduleLoader.load();
        Scene displayScheduleScene = new Scene(displaySchedulePane, 500, 500);

        //put courseSelect scene in majSelect
        Controller majSelectController = (Controller) majSelectLoader.getController();
        majSelectController.setNextScene(courseSelectScene);

        //put majSelect scene in courseSelect
        CourseSelectController courseSelectController = (CourseSelectController) courseSelectLoader.getController();
        courseSelectController.setPrevScene(majSelectScene);

        //put courseSelect scene in interestSelect
        InterestSelectController interestSelectController = (InterestSelectController) interestSelectLoader.getController();
        interestSelectController.setPrevScene(courseSelectScene);

        //put interestSelect scene in displaySchedule
        DisplayScheduleController displayScheduleController = (DisplayScheduleController) displayScheduleLoader.getController();
        displayScheduleController.setPrevScene(interestSelectScene);

        //put displaySchedule scene in interestSelect
        interestSelectController.setNextScene(displayScheduleScene);

        //put interestSelect scene in courseSelect
        courseSelectController.setNextScene(interestSelectScene);

        //put courseSelect controller in majSelect
        majSelectController.setNextController(courseSelectController);


        //put interestSelect controller in courseSelect
        courseSelectController.setNextController(interestSelectController);

        //put displaySchedule controller in interestSelect
        interestSelectController.setNextController(displayScheduleController);


        //stage.show();
        primaryStage.setTitle("ScheduleBot a.01");
        primaryStage.setScene(majSelectScene);
        primaryStage.show();
    }

    public Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(getClass().getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page,700, 450);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
