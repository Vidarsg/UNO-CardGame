package prosjekt;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UnoMenuController {

    @FXML private Button exit;
    @FXML private Button play;
    @FXML private TextField name;
    @FXML private Label invalidInput;

    public void initialize() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {		// Setter TextField i fokus når appen starter.
                name.requestFocus();
            }
        });

        invalidInput.setVisible(false);
    }

    @FXML
	public void onSubmit(ActionEvent event) {   // Åpner UnoGame.fxml dersom brukernavn er godkjent.
		try {
			if(name.getText().matches("[a-zA-Z æøåÆØÅ]+")) {
		    	try {
			        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UnoGame.fxml"));
			        Parent root = (Parent) fxmlLoader.load();
			        Stage stage = new Stage();
			        stage.setTitle("UNO");
			        stage.setResizable(false);
			        stage.setScene(new Scene(root));
                    stage.getIcons().add(new Image("file:src/main/resources/prosjekt/images/uno_logo.png"));
			        stage.show();
			        play.getScene().getWindow().hide();
			        
			        UnoGameController appCon = fxmlLoader.getController();
			        appCon.setPlayerName(name.getText());
			    } catch(Exception e) {
			        e.printStackTrace();
			    }
			} else {
				invalidInput.setVisible(true);
				throw new IllegalArgumentException("Invalid username.");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
    
    public void exitProgram() {	 // Avslutter appen.
        System.exit(0);
    }

}
