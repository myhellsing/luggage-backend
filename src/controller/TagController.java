package controller;

import database.Tag;


/**
 * Created with IntelliJ IDEA.
 * UserController: integral
 * Date: 15.05.12
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class TagController extends BaseController {

    public TagController() {
        baseRoute = "/tag";
        className =Tag.class;
    }

    public static void route(){

       BaseController.route();

 /*       post(new Route("/user") {
            @Override
            public Object handle(Request request, Response response) {
                String login = request.queryParams("login");
                User user = new User();
                user.setLogin(login);
                DatabaseStore.getDS().save(user);
                return "ok";
            }
        });
        */



    }
}
