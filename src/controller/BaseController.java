package controller;

import com.google.code.morphia.Datastore;
import database.DatabaseStore;
import org.bson.types.ObjectId;
import spark.Request;
import spark.Response;
import spark.Route;

import java.lang.reflect.Field;

import static spark.Spark.*;

/**
 * User: integral
 * Date: 15.07.12: 23:56
 */
abstract public class BaseController {

    protected static String baseRoute = "/default";
    protected static Class className = null;

    protected static Object updateFieldsFromRequest(Request request, Object object) throws IllegalAccessException {
        for (Field field:className.getDeclaredFields() ){
            if (request.queryParams(field.getName()) != null){
                field.setAccessible(true);
                field.set(object,request.queryParams(field.getName()));
            }
        }
        return object;
    }

    public static void route(){

        get(new Route(baseRoute) {
            @Override
            public Object handle(Request request, Response response) {
                return DatabaseStore.getDS().find(className).asList();
            }
        });

        get(new Route(baseRoute + "/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return DatabaseStore.getDS().get(className,new ObjectId(request.params(":id")));
            }
        });

        delete(new Route(baseRoute+"/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Datastore ds =DatabaseStore.getDS();
                ds.delete(ds.get(className, new ObjectId(request.params(":id"))));
                return "ok";
            }
        });

        post(new Route(baseRoute) {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    Object object = className.newInstance();
                    object = BaseController.updateFieldsFromRequest(request,object);
                    DatabaseStore.getDS().save(object);
                    return "ok";
                } catch (InstantiationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return "(";

            }
        });

        put(new Route(baseRoute) {
            @Override
            public Object handle(Request request, Response response) {
                if (request.queryParams("id") == null){
                    return "id required";
                }
                Object object =DatabaseStore.getDS().get(className,new ObjectId(request.queryParams("id")));
                try {
                    object = BaseController.updateFieldsFromRequest(request,object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                DatabaseStore.getDS().save(object);
                return "ok";
            }
        });

    }
}
