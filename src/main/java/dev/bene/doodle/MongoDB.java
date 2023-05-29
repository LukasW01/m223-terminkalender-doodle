package dev.bene.doodle;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import com.mongodb.MongoWriteException;
import org.bson.Document;

public class MongoDB {
    private static MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public MongoDB() {
        connectDB();

        database = mongoClient.getDatabase("doodle");
        collection = database.getCollection("doodle");
    }

    public void connectDB() {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            System.out.println("Connected to database");
        } catch (MongoException e) {
            System.out.println("Error connecting to database: " + e);
        }
    }

    public FindIterable<Document> getCollectionEvents() {
        return collection.find(new Document("reservation", true));
    }

    public FindIterable<Document> getCollectionRooms() {
        return collection.find(new Document("room", true));
    }

    public FindIterable<Document> getCollectionParticipant() {
        return collection.find(new Document("participant", true));
    }

    public FindIterable<Document> getReservationByPublicID(String id) {
        return collection.find(new Document("id_public", id));
    }

    public void setCollection(Document param) {
        try {
            collection.insertOne(param);
        } catch (MongoWriteException e) {
            if (e.getCode() == 11000) {
                System.err.println("Duplicate key error: " + e.getMessage());
            } else {
                System.err.println("Write exception: " + e.getMessage());
            }
        }
    }


    public void updateCollection(Document param, Integer id) {
        collection.updateOne(new Document("id", id), new Document("$set", param));
    }

    public void removeCollection(Document param, Integer id) {
        collection.deleteOne(new Document("id_task", id));
    }

    public void close() {
        mongoClient.close();
    }
}
