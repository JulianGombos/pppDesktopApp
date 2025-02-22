package com.playerplusplus;

import com.mongodb.*;
import com.mongodb.client.*;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import javafx.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static ConnectionString uri = new ConnectionString( "mongodb+srv://Hongee98:Pokemonmaster98@maincluster.vfms7.gcp.mongodb.net/test");
	public static MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(uri).retryWrites(true).build();
	public static MongoClient mongoClient = MongoClients.create(settings);
	public static MongoDatabase database = mongoClient.getDatabase("playerplusplus");	
	
	public static void main(String args[]){
		
		launch(args);


		
	}
	
	public void start(Stage primaryStage) {
		createLogger();
		
		BorderPane root = new BorderPane();
	
		
		Button button = new Button();
		button.setText("Button 1");
		button.setId("1");
		button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

		Button button2 = new Button();
		button2.setText("Button 2");
		button2.setId("2");
		button2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		
		HBox rightBox = new HBox(button, button2);
		rightBox.setAlignment(Pos.CENTER_RIGHT);	
		root.setRight(rightBox);


		ListView gameListView = new ListView();
		
		MongoCollection collection = database.getCollection("games");
		
		FindIterable document = collection.find();
		MongoCursor cursor = document.cursor();
		while(cursor.hasNext()) {
			gameListView.getItems().add(cursor.next());
		}
		
		HBox leftHBox = new HBox(gameListView);
		root.setLeft(leftHBox);
		
		
		
		Scene scene = new Scene(root,500,500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Desktop Player Plus Plus");
		primaryStage.show();
	}
	

	//Creating the mouse event handler 
	EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		
	   public void handle(MouseEvent e) {
		   
	      Button button = (Button) e.getSource();
	      
	      switch(Integer.parseInt(button.getId())) {
		      case 1:
		    	  System.out.println("Button 1");
		    	  break;
		      case 2:
		    	  System.out.println("Button 2");
		    	  break;
	    	  default:
	    		  System.out.println("Default case");
	    		  break;
	      }
	   } 
	   
	   
	};   //end event handler
	
	
	
	public static void createLogger() {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
		rootLogger.setLevel(Level.ALL);
	} // end createlogger
	
	
} //end of Main Class


