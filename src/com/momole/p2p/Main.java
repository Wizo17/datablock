package com.momole.p2p;

import java.io.PrintStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	private TextField msgTxField ; 
	private TextArea responseTxArea;
	private TextArea serverOutTxArea;
	private Button sendButton;

	
	public static void main(String[] args) {
		launch( args );
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		msgTxField = new TextField( );
		responseTxArea = new TextArea( );
		serverOutTxArea = new TextArea( );
		sendButton = new Button("Envoyer");
		
		PrintStream printStream = new PrintStream( new CustomOutputStream(serverOutTxArea) );
		System.setOut( printStream );
		sendButton.setOnAction(e->{
			String msg = msgTxField.getText();
			String[] list = msg.split(" ");
			
			if( list.length >=3 ) {
				P2PClient client = new P2PClient( list[0] , Integer.parseInt(list[1]) );
				msgTxField.appendText(client.request( list[2] ));
			}
		});
		// TODO Auto-generated method stub
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll( new Label("Reponses : ") ,  responseTxArea , new Label("Message : ") ,  msgTxField , sendButton);
		VBox.setVgrow(responseTxArea, Priority.ALWAYS);
		
		VBox vbox2 = new VBox(10);
		vbox2.getChildren().addAll( new Label("Log du serveur") , serverOutTxArea  );
		VBox.setVgrow( serverOutTxArea , Priority.ALWAYS );
		
		HBox hbox = new HBox( 5 );
		hbox.getChildren().addAll( vbox , vbox2 );
		HBox.setHgrow(  vbox , Priority.ALWAYS);
		HBox.setHgrow(  vbox2 , Priority.ALWAYS);
		
		Scene sc = new Scene( hbox , 700 , 500 );
		primaryStage.setScene(sc);
		primaryStage.setTitle("IHM_DataBlock");
		primaryStage.show( );


		P2P.startServer(RoutingTable.TCP_PORT);
		
	}

}
