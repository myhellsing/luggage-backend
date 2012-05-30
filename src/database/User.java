package database;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;


/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 15.05.12
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
@Entity("users")
public class User {
    @Id ObjectId id; // генерируется автоматически, если не задан вручную
    String name; // Типы, передаваемые по значению сохраняются автоматически

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "{\"id\":"+id+",\"name\":"+name+"}";
    }
}