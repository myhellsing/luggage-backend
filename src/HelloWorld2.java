/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 30.04.12
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */


import com.mongodb.*;
import spark.Request;
import spark.Response;
import spark.Route;

import java.net.UnknownHostException;
import java.util.Set;

import static spark.Spark.get;

public class HelloWorld2 {

    public static void main(String[] args) {


        get(new Route("/hello2") {
            @Override
            public Object handle(Request request, Response response) {

                DB db=MongoDB.getDB();

                String result="";
                DBCollection coll = db.getCollection("bill");
                DBCursor cur = coll.find();

                while(cur.hasNext()) {
                   result+=cur.next().toString();
                }
                if (result.length()>0) return result;
                return "Hello World! It really work!";
            }
        });

    }

}