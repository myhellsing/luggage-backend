/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 30.04.12
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */

import static spark.Spark.*;

import com.mongodb.DB;
import com.mongodb.Mongo;
import spark.*;

import java.net.UnknownHostException;
import java.util.*;

public class HelloWorld {

    public static void main(String[] args) {

       new HelloWorld2().main(args);



        get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {

                DB db=MongoDB.getDB();

                String result="";
                Set<String> colls = db.getCollectionNames();
                for (String s : colls ){
                    result +=s;
                }
                if (result.length()>0) return result;
                return "Hello World! It really work!";
            }
        });

    }

}