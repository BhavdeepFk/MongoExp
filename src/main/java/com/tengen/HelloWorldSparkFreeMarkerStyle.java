package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldSparkFreeMarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter stringWriter = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name","Freemarker");
                    helloTemplate.process(map, stringWriter);

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
