package jgpib.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

public class GpibGuiController {

	@FXML TreeView<String> treeView ;
	@FXML AnchorPane propertyPane ;
	
	public void initialize() {
		TreeItem<String> root = new TreeItem<String>("Root") ;
		TreeItem<String> instruments = new TreeItem<String>("Instruments") ;
		TreeItem<String> measure = new TreeItem<String>("Measure") ;
		TreeItem<String> results = new TreeItem<String>("Results") ;
		root.getChildren().add(instruments) ;
		root.getChildren().add(measure) ;
		root.getChildren().add(results) ;
		treeView.setRoot(root);
	}

}
