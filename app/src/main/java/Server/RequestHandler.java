package Server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class RequestHandler {
    private MongoClient mongoClient;
    
    public RequestHandler(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
      }
}
