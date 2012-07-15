package test;


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




    private User addNewUser(){
        User user = new User();
        user.setLogin("cat");
        ds.save(user);
        return user;

    }

    private void delete(User user){
        ds.delete(user);
    }

    @Test
    public void testGetUsers() {
        User user = addNewUser();
        try {
            TestUtils.UrlResponse response = testUtil.doMethod("GET", "/user", null);
            String expected = "[{\"id\":"+user.getId()+",\"login\":cat}]";
            Assert.assertEquals(expected,response.body);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        finally {
            delete(user);
        }
    }

    @Test
    public void testAddUser() {
        try {
            TestUtils.UrlResponse response = testUtil.doMethod("POST", "/user?login=catty",null);
            Assert.assertEquals("ok",response.body);
            List<User> users = ds.find(User.class).asList();
            Assert.assertEquals(1,users.size());
            User user = users.get(0);
            Assert.assertEquals("catty",user.getLogin());
            delete(user);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetUserByLogin(){
        User user = addNewUser();
        try{
            TestUtils.UrlResponse response = testUtil.doMethod("GET", "/user/cat", null);
            String expected = "{\"id\":"+user.getId()+",\"login\":cat}";
            Assert.assertEquals(expected,response.body);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        finally {
            delete(user);
        }
    }
    @Test
    public void testDeleteByLogin(){
        User user = addNewUser();
        try{
            TestUtils.UrlResponse response = testUtil.doMethod("DELETE", "/user/cat", null);
            Assert.assertEquals("ok",response.body);
            List<User> users = ds.find(User.class).asList();
            Assert.assertEquals(0,users.size());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        finally {
            delete(user);
        }

    }


}
