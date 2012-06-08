package database;

import com.google.code.morphia.annotations.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 08.06.12
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
@Entity("product_item")
public class ProductItem  extends BaseClass{

    private String location;
    private double price;
    private Product product;

}
