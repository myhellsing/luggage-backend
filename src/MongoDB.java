import com.mongodb.DB;
import com.mongodb.Mongo;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 04.05.12
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public class MongoDB {
    private static Mongo m =null;
    private static DB db = null;

    public static DB getDB(){
        if (db == null){
            db= createDB();
        }
        return db;
    }

    private static DB createDB(){
        return  getMongo().getDB("test");
    }
    private static Mongo getMongo(){
        if (m==null){
            try {
                m = new Mongo( "localhost");
            } catch (UnknownHostException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return m;
    }

}
