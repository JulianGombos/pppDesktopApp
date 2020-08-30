package com.playerplusplus;

import com.mongodb.*;
import com.mongodb.client.*;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;


public class Main {
	
	public static void main(String args[]) {
		
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
		rootLogger.setLevel(Level.ALL);
		
		ConnectionString uri = new ConnectionString( "mongodb+srv://Hongee98:Pokemonmaster98@maincluster.vfms7.gcp.mongodb.net/test");
		
		MongoClientSettings settings = MongoClientSettings.builder()
		    .applyConnectionString(uri)
		    .retryWrites(true)
		    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("playerplusplus");	
		MongoCollection collection = database.getCollection("gamepageposts");
		
		FindIterable document = collection.find();
		MongoCursor cursor = document.cursor();
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
}
