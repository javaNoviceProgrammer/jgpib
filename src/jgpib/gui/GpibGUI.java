package jgpib.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GpibGUI extends Application {
	
	final String FXML_PATH = "/jgpib/gui/Jgpib_gui.fxml" ;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH)) ;
		Parent root = loader.load() ;
		
		Scene scene = new Scene(root) ;
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
