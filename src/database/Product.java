package database;

import com.google.code.morphia.annotations.Entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 08.06.12
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
@Entity("product")
public class Product extends BaseClass{
    String name;
    List<Tag> tags;
}
