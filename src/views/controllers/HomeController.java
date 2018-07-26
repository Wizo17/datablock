package views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	@FXML
	private TableView<String> tbv_transaction;
	@FXML
	private TableColumn<String, String> tbc_date_trans, tbc_type_trans, tbc_adresse_trans, tbc_montant_trans;

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
	
	private void displayDataTransaction() {
		//Config tableview
		tbv_transaction = new TableView<String>();
		tbv_transaction.setEditable(true);
		
		//Config tablecolumn
		tbc_date_trans = new TableColumn<String, String>("Date");
		tbc_type_trans = new TableColumn<String, String>("Type");
		tbc_adresse_trans = new TableColumn<String, String>("Adresse");
		tbc_montant_trans = new TableColumn<String, String>("Montant");
		tbc_date_trans.setCellValueFactory(new PropertyValueFactory<>("Date"));
		tbc_type_trans.setCellValueFactory(new PropertyValueFactory<>("Type"));
		tbc_adresse_trans.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
		tbc_montant_trans.setCellValueFactory(new PropertyValueFactory<>("Montant"));
		
		//Load data transaction
		List<String> list = new ArrayList<>();
        list.add("String1");
        list.add("String2");
        list.add("String3");
        
        //Store data transaction¨
        ObservableList<String> observableList = FXCollections.observableArrayList(list);
        
        //Display data transaction to table view
        tbv_transaction.getColumns().addAll(tbc_date_trans, tbc_type_trans, tbc_adresse_trans, tbc_montant_trans);
        tbc_date_trans.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tbv_transaction.setItems(observableList);
	}

}
