package controller;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import database.DatabaseStore;
import database.User;
import org.json.simple.*;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;


/**
 * Created with IntelliJ IDEA.
 * UserController: integral
 * Date: 15.05.12
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class UserController {
    public static void route(){

        get(new Route("/users") {
            @Override
            public Object handle(Request request, Response response) {
                Morphia morphia = new Morphia();
                morphia.map(User.class);
                Datastore ds = morphia.createDatastore(DatabaseStore.getMongo(), "test_database");
                List<User> users =ds.find(User.class).asList();
                String jsonText = JSONValue.toJSONString(users);
                return users;
            }
        });

        post(new Route("/users") {
            @Override
            public Object handle(Request request, Response response) {
                String name = request.queryParams("name");
                User user = new User();
                user.setName(name);
                Morphia morphia = new Morphia();
                morphia.map(User.class);
                Datastore ds = morphia.createDatastore(DatabaseStore.getMongo(), "test_database");

                ds.save(user);
                return "ok";
            }
        });

    }
}
