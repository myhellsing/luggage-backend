package database;

import com.google.code.morphia.annotations.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 08.06.12
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
@Entity("tags")
public class Tag extends BaseClass{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
