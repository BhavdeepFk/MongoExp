package com.tengen;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bhavdeeps
 * Date: 1/12/14
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldMongoDbSparkFreeMarkerStyle {
    public static void main(String[] args) throws UnknownHostException {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");

        MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
        DB database = client.getDB("course");
        final DBCollection dbCollection = database.getCollection("hello");


        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter stringWriter = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", "Freemarker");
                    DBObject dbObject = dbCollection.findOne();
                    helloTemplate.process(dbObject, stringWriter);

                    System.out.println(stringWriter);


                } catch (IOException e) {
                    halt(500);
                    e.printStackTrace();
                } catch (TemplateException e) {
                    halt(500);
                    e.printStackTrace();
                }
                return stringWriter;
            }
        });
    }
}
