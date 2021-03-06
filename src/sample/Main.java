package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Platform.setImplicitExit(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/sample.fxml"));
        Parent root = loader.load();
        root.setId("background");
        Scene scene = new Scene(root,1310,894);
        scene.getStylesheets().add(getClass().getResource("/stylesheets.css").toExternalForm());



        primaryStage.setScene(scene);
        primaryStage.setTitle("Dodger");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("IconDodger.png"));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }


    void displayApplication() {
        Platform.runLater(() -> {
            try{
                start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}