package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	
	@FXML
	private AnchorPane root;
	@FXML
	private JFXDrawer drawer;
	@FXML
	private Pane pan_home, pan_trans, pan_file, pan_host, pan_smct;
	@FXML
	private JFXButton btn_menu_home, btn_menu_trans, btn_menu_file, btn_menu_host, btn_menu_smct;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pan_home.toFront();
	}
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		if(event.getSource() == btn_menu_home) {
			pan_home.toFront();
		}else if(event.getSource() == btn_menu_trans) {
			pan_trans.toFront();
		}else if(event.getSource() == btn_menu_file) {
			pan_file.toFront();
		}else if(event.getSource() == btn_menu_host) {
			pan_host.toFront();
		}else if(event.getSource() == btn_menu_smct) {
			pan_smct.toFront();
		}
	}
	
	@FXML
	private void close_app(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	private void minimize_app(ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

}
