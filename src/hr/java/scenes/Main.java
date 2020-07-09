package hr.java.scenes;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class
Main extends Application {

    private static Stage stage;

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setMainPage(BorderPane root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("PocetnaStranica.fxml"));
        Scene scene = new Scene(root);

        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(480);

        primaryStage.setScene(scene);
        primaryStage.setTitle("HTML Gallery Creator");

        //MAKIVA OBRUB PROZORA
        //primaryStage.initStyle(StageStyle.UNDECORATED);

        //grab your root here
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.show();
        stage = primaryStage;
    }
}
