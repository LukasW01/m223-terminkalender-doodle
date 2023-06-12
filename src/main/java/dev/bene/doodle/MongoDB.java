package dev.bene.doodle;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoWriteException;
import static com.mongodb.client.model.Filters.eq;
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

    public Document getReservationByPublicID(String id) {
        return collection.find(eq("id_public", id)).first();
    }

    public Document getReservationByPrivateID(String id) {
        return collection.find(eq("id_private", id)).first();
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

    public void setRoom(String roomName) {
        collection.insertOne(new Document("room", true).append("roomName", roomName));
    }

    public void setParticipant(String participantName) {
        collection.insertOne(new Document("participant", true).append("participantName", participantName));
    }

    public void updateCollection(Document params, String id) {
        collection.replaceOne(eq("id_private", id), params);
    }

    public void removeCollection(String id) {
        collection.deleteOne(eq("id_private", id));
    }
}
