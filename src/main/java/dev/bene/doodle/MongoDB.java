package dev.bene.doodle;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public MongoDB() {
        connectDB();
    }

    public MongoDatabase connectDB() {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("doodle");
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e);
        }
        return database;
    }

    public com.mongodb.client.MongoCollection<org.bson.Document> getCollection() {
        return database.getCollection("doodle");
    }

    public com.mongodb.client.FindIterable<Document> getCollection(String param) {
        return database.getCollection("doodle")
                .find(new Document("name", param));

    }

    public void setCollection(Document param) {
        System.out.println("Inserting: " + param);
        database.getCollection("doodle").insertOne((Document) param);
    }

    public void close() {
        mongoClient.close();
    }
}
