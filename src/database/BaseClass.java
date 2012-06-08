package database;

import com.google.code.morphia.annotations.Id;
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
    ObjectId id; // генерируется автоматически, если не задан вручную

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
