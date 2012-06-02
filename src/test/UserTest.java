package test;


import com.google.code.morphia.Datastore;
import database.DatabaseStore;
import database.User;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * UserController: integral
 * Date: 15.05.12
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */

public class UserTest extends BaseTest{



    @Test
    public void testGetUsers() {
        try {
            User user = new User();
            user.setName("Darya");
            Datastore ds = DatabaseStore.getDS();
            ds.save(user);
            TestUtils.UrlResponse response = testUtil.doMethod("GET", "/users", null);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.body);
            String expected = "[{\"id\":"+user.getId()+",\"name\":Darya}]";
            Assert.assertEquals(expected,response.body);
            ds.delete(user);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddUser() {
        try {
            TestUtils.UrlResponse response = testUtil.doMethod("POST", "/users?name=Cat",null);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.body);
            Assert.assertEquals("ok",response.body);
            Datastore ds = DatabaseStore.getDS();
            List<User> users = ds.find(User.class).asList();
            Assert.assertEquals(1,users.size());
            User user = users.get(0);
            Assert.assertEquals("Cat",user.getName());
            ds.delete(user);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


}
