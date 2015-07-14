package com.rest.test.data;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import lwm2m.server.BootstrapServer.BootstrapConfig;
import lwm2m.server.RegisterServer.Client;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Created by jilongsun on 6/22/15.
 */
public class MongoConnection {
    private static MongoClient mongoClient;

    public static DB mongoConnection() throws Exception {

        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB database = mongoClient.getDB("config");

        return database;

    }

    public static Datastore getDataStore() throws Exception {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        Morphia morphia = new Morphia();
        morphia.map(BootstrapConfig.class);

        Datastore ds = morphia.createDatastore(mongoClient, "config");
        return ds;

    }

    public static Datastore getDataStore1() throws Exception{
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        Morphia morphia = new Morphia();
        morphia.map(Client.class);
        Datastore ds = morphia.createDatastore(mongoClient, "client");

        return ds;
    }

//    public static void main(String args[]) throws Exception {
//
////        Gson gson = new Gson();
////        BootstrapConfig config = gson.fromJson("{'shortId':0}", BootstrapConfig.class);
////
//        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
////        DB database = MongoConnection.mongoConnection();
//        RegisterObjects registerObjects = new RegisterObjects(1, 86400, "2.0", BindingMode.U,
//                null,1, 1, 1);
//        Morphia morphia = new Morphia();
//        morphia.map(RegisterObjects.class);
//
//        Datastore ds = morphia.createDatastore(mongoClient, "objects");
////        final Query<BootstrapConfig> query = ds.createQuery(BootstrapConfig.class);
////        final List<BootstrapConfig> employees = query.asList();
//
////   DBCollection collection = database.getCollection("BootstrapConfig");
//
//        ds.save(registerObjects);
////        ds.getCollection(BootstrapConfig.class);





//       DBObject query = new BasicDBObject("_id", "mo");
//       DBCursor cursor = collection.find(query);
//        DBObject query = new BasicDBObject("shortId",1);
//        DBCursor cursor = collection.find(query);
//      System.out.println(ds.getCollection(BootstrapConfig.class));

//MongoConnection.close();

//    }
    public static void close(){
        mongoClient.close();
    }
}
