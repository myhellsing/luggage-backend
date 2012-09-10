package controller;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;

/**
 * Created with IntelliJ IDEA.
 * UserController: integral
 * Date: 15.05.12
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */
public class Index {

    public static void main(String[] args) {


        new UserController().route();
        new TagController().route();

        get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World!";
            }
        });
    }
}
