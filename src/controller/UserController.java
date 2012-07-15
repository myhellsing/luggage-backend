package controller;

import com.google.code.morphia.Datastore;
import database.DatabaseStore;
import database.User;
import org.json.simple.JSONValue;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

import static spark.Spark.*;


/**
 * Created with IntelliJ IDEA.
 * UserController: integral
 * Date: 15.05.12
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class UserController {
    public static void route(){

        get(new Route("/user") {
            @Override
            public Object handle(Request request, Response response) {
                List<User> users = DatabaseStore.getDS().find(User.class).asList();
                String jsonText = JSONValue.toJSONString(users);
                return users;
            }
        });

        get(new Route("/user/:login") {

            @Override
            public Object handle(Request request, Response response) {
                User user = DatabaseStore.getDS().find(User.class,"login",request.params(":login")).get();
                return JSONValue.toJSONString(user);
            }
        }

        );

        post(new Route("/user") {
            @Override
            public Object handle(Request request, Response response) {
                String login = request.queryParams("login");
                User user = new User();
                user.setLogin(login);
                DatabaseStore.getDS().save(user);
                return "ok";
            }
        });

        delete(new Route("/user/:login") {
            @Override
            public Object handle(Request request, Response response) {
                Datastore ds =DatabaseStore.getDS();
                ds.delete(ds.find(User.class,"login",request.params(":login")));
                return "ok";  //To change body of implemented methods use File | Settings | File Templates.
            }
        }

        );



    }
}
