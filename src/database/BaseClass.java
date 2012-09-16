package database;

import com.google.code.morphia.annotations.Id;
import common.GsonUtils;
import org.bson.types.ObjectId;

/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 08.06.12
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public class BaseClass {

    @Id
    public String id = ObjectId.get().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString(){
        return GsonUtils.gson().toJson(this).toString();
    }

}
