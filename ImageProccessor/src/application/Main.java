package application;

/**
 * Main driver for application
 * @author Johnathan Dewey
 * @version September 13th 2023
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application 
{ 
	Picture PIC = new Picture();
	Momento momento = new Momento();
	InterfaceManager interfaceManager = new InterfaceManager(PIC, momento);

	
	@Override
	public void start(Stage primaryStage) {
		//initialize scene
		Scene scene = interfaceManager.buildApp();
		
		//show scene
		primaryStage.setTitle(Constants.TITLE_APPLICATION);
		primaryStage.setScene(scene);
		primaryStage.show();
	} 

	public static void main(String[] args) {
		launch(args);
	}
} 