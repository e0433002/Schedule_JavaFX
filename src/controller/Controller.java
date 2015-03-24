package controller;

import java.net.URL;
import java.util.ResourceBundle;

import model.TableCreater;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
	@FXML
	private Pane pane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableCreater creater = new TableCreater();
		
		TableView<String[]> table = creater.createTableByStringArray();
		
		table.setEditable(true);
		pane.setMinWidth(creater.getWidth());
		pane.setMinHeight(creater.getHeight());
		pane.getChildren().add(table);
		System.out.println("finish");
	}
}
