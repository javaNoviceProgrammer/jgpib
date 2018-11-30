package jgpib.gui.tests;

import org.controlsfx.control.PropertySheet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jgpib.gui.data.Agilent34401APropertySheet;
import static mathLib.util.MathUtils.*;

public class TestPropertySheet extends Application {

	Button button ;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		PropertySheet psheet = new PropertySheet() ;
		
		Agilent34401APropertySheet item1 = new Agilent34401APropertySheet("GPIB.Bus") ;
		Agilent34401APropertySheet item2 = new Agilent34401APropertySheet("GPIB.Address") ;
		Agilent34401APropertySheet item3 = new Agilent34401APropertySheet("Voltage.Max Value (V)") ;
		Agilent34401APropertySheet item4 = new Agilent34401APropertySheet("Voltage.Resolution (V)") ;
		
		psheet.getItems().addAll(item1, item2, item3, item4) ;
		
		AnchorPane pane = new AnchorPane() ;
		VBox vBox = new VBox(psheet) ;
		pane.getChildren().addAll(vBox) ;
		Scene scene = new Scene(new ScrollPane(pane), 600, 400) ;
		primaryStage.setScene(scene);
		primaryStage.show();
		
		button = new Button("apply") ;
		button.setOnAction(e -> {
			System.out.println(evaluate((String)item1.getValue()));
			System.out.println(evaluate((String)item2.getValue()));
			System.out.println(evaluate((String)item3.getValue()));
			System.out.println(evaluate((String)item4.getValue()));
		});
		
		Stage stage = new Stage() ;
		AnchorPane pane1 = new AnchorPane() ;
		pane1.getChildren().add(button) ;
		Scene newScene = new Scene(pane1, 200, 100) ;
		stage.setScene(newScene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
