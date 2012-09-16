package database;

import com.google.code.morphia.annotations.Entity;


/**
 * Created with IntelliJ IDEA.
 * User: integral
 * Date: 15.05.12
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
@Entity("users")
public class User extends BaseClass{

    String login; // Типы, передаваемые по значению сохраняются автоматически

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}