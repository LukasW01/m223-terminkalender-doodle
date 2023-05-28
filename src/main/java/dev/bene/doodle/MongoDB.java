package dev.bene.doodle;

import com.mongodb.client.FindIterable;
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

    public FindIterable<Document> getCollection() {
        return database.getCollection("doodle").find();
    }

    public void setCollection(Document param) {
        System.out.println("Inserting: " + param);
        database.getCollection("doodle").insertOne((Document) param);
    }

    public void updateCollection(Document param, Integer id) {
        System.out.println("Updating: " + param);
        database.getCollection("doodle").updateOne(new Document("_id", id), new Document("$set", param));
    }

    public void removeCollection(Document param, Integer id) {
        System.out.println("Removing: " + param);
        database.getCollection("doodle").deleteOne(new Document("id_task", id));
    }
    public Document getDocument(Document param, Integer id) {
        return database.getCollection("doodle").find(new Document("id_task", id)).first();
    }

    public void close() {
        mongoClient.close();
    }
}
