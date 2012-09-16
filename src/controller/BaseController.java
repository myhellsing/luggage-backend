package controller;

import com.google.code.morphia.Datastore;
import common.GsonUtils;
import database.DatabaseStore;
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

    protected String baseRoute = "/default";
    protected Class className = null;


    protected Object updateFieldsFromRequest(Request request, Object object) throws IllegalAccessException {
        for (Field field : className.getDeclaredFields()) {
            if (request.queryParams(field.getName()) != null) {
                field.setAccessible(true);
                field.set(object, request.queryParams(field.getName()));
            }
        }
        return object;
    }

    public void route() {

        get(new Route(baseRoute + "/checkController") {
            @Override
            public Object handle(Request request, Response response) {
                return baseRoute;
            }
        });


        get(new Route(baseRoute) {
            @Override
            public Object handle(Request request, Response response) {
                return DatabaseStore.getDS().find(className).asList();
            }
        });

        get(new Route(baseRoute + "/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return DatabaseStore.getDS().get(className, request.params(":id"));
            }
        });

        //TODO:: разобраться со случаем, когда сущность не найдена
        delete(new Route(baseRoute + "/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Datastore ds = DatabaseStore.getDS();
                Object obj = ds.get(className, request.params(":id"));
                if (obj != null) {
                    ds.delete(obj);
                    return "ok";
                }
                return "not found";

            }
        });

        post(new Route(baseRoute) {
            @Override
            public Object handle(Request request, Response response) {
                Object object = GsonUtils.gson().fromJson(request.body(), className);
                DatabaseStore.getDS().save(object);
                return "ok";
            }
        });

        put(new Route(baseRoute) {
            @Override
            public Object handle(Request request, Response response) {
                Object object = GsonUtils.gson().fromJson(request.body(), className);
                DatabaseStore.getDS().save(object);
                return "ok";
            }
        });

    }
}
