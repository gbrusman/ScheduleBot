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
        FXMLLoader majSelectLoader = new FXMLLoader(getClass().getResource("majselect.fxml"));
        Parent majSelectPane = majSelectLoader.load();
        Scene majSelectScene = new Scene(majSelectPane, 500, 500);

        FXMLLoader courseSelectLoader = new FXMLLoader(getClass().getResource("courseselect.fxml"));
        Parent courseSelectPane = courseSelectLoader.load();
        Scene courseSelectScene = new Scene(courseSelectPane, 500, 500);

        FXMLLoader interestSelectLoader = new FXMLLoader(getClass().getResource("interestselect.fxml"));
        Parent interestSelectPane = interestSelectLoader.load();
        Scene interestSelectScene = new Scene(interestSelectPane, 500, 500);

        //put courseSelect scene in majSelect
        Controller majSelectController = (Controller) majSelectLoader.getController();
        majSelectController.setNextScene(courseSelectScene);

        //put majSelect scene in courseSelect
        CourseSelectController courseSelectController = (CourseSelectController) courseSelectLoader.getController();
        courseSelectController.setPrevScene(majSelectScene);

        //put courseSelect scene in interestSelect
        InterestSelectController interestSelectController = (InterestSelectController) interestSelectLoader.getController();
        interestSelectController.setPrevScene(courseSelectScene);

        //put interestSelect scene in courseSelect
        courseSelectController.setNextScene(interestSelectScene);

        //put courseSelect controller in majSelect
        majSelectController.setNextController(courseSelectController);

        //put majSelect controller in courseSelect
     //   courseSelectController.setPrevController(majSelectController);

        //put interestSelect controller in courseSelect
        courseSelectController.setNextController(interestSelectController);

        //put courseSelect controller in interestSelect
      //  interestSelectController.setPrevController(courseSelectController);

        //stage.show();
        primaryStage.setTitle("Schedulebot a.01");
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
