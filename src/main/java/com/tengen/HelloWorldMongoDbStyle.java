package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

public class HelloWorldMongoDbStyle {
    public static void main(String args[]){
        try {
            MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
            DB database = client.getDB("course");
            DBCollection dbCollection = database.getCollection("hello");

            DBObject dbObject = dbCollection.findOne();
            System.out.println("Doc:"+dbObject);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
