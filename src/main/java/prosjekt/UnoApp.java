package prosjekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UnoApp extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {      // Åpner UnoMenu.fxml.
        primaryStage.setScene(new Scene(FXMLLoader.load(UnoApp.class.getResource("UnoMenu.fxml"))));
        primaryStage.setTitle("UNO");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:src/main/resources/prosjekt/images/uno_logo.png")); 
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        UnoApp.launch(args);
    }
}
