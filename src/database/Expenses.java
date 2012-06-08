package database;

import com.google.code.morphia.annotations.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 08.06.12
 * Time: 23:05
 * To change this template use File | Settings | File Templates.
 */
@Entity("expenses")
public class Expenses extends BaseClass {
    User user;
    ProductItem productItem;
    long quantity;

}
