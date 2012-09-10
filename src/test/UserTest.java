package test;


import database.User;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Ignore;
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

    @After
    public void tearDown() throws Exception {
        List<User> list =  ds.find(User.class).asList();
        for (User user:list){
            ds.delete(user);
        }
    }


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
            Assert.assertEquals("["+user.toString()+"]",response.body);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        finally {
            delete(user);
        }
    }

    @Test
    public void testAddUser() throws Exception{
            TestUtils.UrlResponse response = testUtil.doMethod("POST", "/user?login=catty",null);
            Assert.assertEquals("ok",response.body);
            List<User> users = ds.find(User.class).asList();
            Assert.assertEquals(1,users.size());
            User user = users.get(0);
            Assert.assertEquals("catty",user.getLogin());
            delete(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = addNewUser();
        TestUtils.UrlResponse response = testUtil.doMethod("PUT", "/user?login=cat2&id="+user.getId(), null);
        List<User> users = ds.find(User.class).asList();
        Assert.assertEquals("ok",response.body);
        Assert.assertEquals(1,users.size());
        Assert.assertEquals("cat2",users.get(0).getLogin());
        delete(users.get(0));
    }


    @Test
    public void testGetUserById() {
        User user = addNewUser();
        try{
            TestUtils.UrlResponse response = testUtil.doMethod("GET", "/user/"+user.getId(), null);
            Assert.assertEquals(user.toString(),response.body);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        finally {
            delete(user);
        }
    }


    @Test
    public void testDeleteById(){
        User user = addNewUser();
        try{
            TestUtils.UrlResponse response = testUtil.doMethod("DELETE", "/user/"+user.getId(), null);
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

/*    @Test
    public void testDeleteByIdNotExist(){
        try{
            TestUtils.UrlResponse response = testUtil.doMethod("DELETE", "/user/"+"504e28fc84aec0ff09ab9aed", null);
            Assert.assertEquals("not found",response.body);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        finally {
        }

    }*/

    @Test
    @Ignore
    public void testDeleteByIdString(){
        String id ="504e2b1984aecefa97c656cf";
        //String id ="504e28fc84aec0ff09ab9aed"; 504e29ec84ae82bab2246915 504e2b1984aecefa97c656cf
        TestUtils.UrlResponse response = null;
        try {
            response = testUtil.doMethod("DELETE", "/user/"+id, null);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertEquals("ok",response.body);
    }


}
