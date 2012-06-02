package database;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import common.Config;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * UserController: integral
 * Date: 04.05.12
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseStore {

    private static Mongo m =null;
    private static Datastore ds = null;
    private static Morphia morphia = null;

    public static Datastore getDS(){
        if (ds==null){
            ds = DatabaseStore.getMorphia().createDatastore(DatabaseStore.getMongo(), Config.DATABASE_NAME);
        }
        return ds;
    }

    public static Mongo getMongo(){
        if (m==null){
            try {
                m = new Mongo( Config.DBHOST, Config.DBPORT);
            } catch (UnknownHostException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return m;
    }

    private static Morphia getMorphia(){
        if (morphia==null){
                morphia = new Morphia();
                morphia.map(User.class);
        }
        return morphia;
    }

}
