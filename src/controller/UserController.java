package controller;

import database.User;


/**
 * Created with IntelliJ IDEA.
 * UserController: integral
 * Date: 15.05.12
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class UserController extends BaseController {

    public UserController() {
        baseRoute = "/user";
        className =User.class;
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
