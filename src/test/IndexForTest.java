package test;

import common.Config;
import controller.TagController;
import controller.UserController;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;
import static spark.Spark.setPort;

/**
 * Created with IntelliJ IDEA.
 * UserController: integral
 * Date: 15.05.12
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */
public class IndexForTest {

    public static void main(String[] args) {

        setPort(Config.TEST_PORT);

        Config.DATABASE_NAME = Config.TEST_DATABASE_NAME;

        new TagController().route();
        new UserController().route();


        get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World!";
            }
        });
    }
}
