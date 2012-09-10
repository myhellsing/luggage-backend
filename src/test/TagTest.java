package test;


import database.Tag;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;

import java.util.List;



public class TagTest extends BaseTest{


    @After
    public void tearDown() throws Exception {
        List<Tag> list =  ds.find(Tag.class).asList();
        for (Tag tag:list){
            ds.delete(tag);
        }
    }

    private Tag addNewTag(){
        Tag tag = new Tag();
        tag.setName("test_tag");
        ds.save(tag);
        return tag;

    }

    private void delete(Tag tag){
        ds.delete(tag);
    }


    @Test
    public void testGetTags() {
        Tag tag = addNewTag();
        try {
            TestUtils.UrlResponse response = testUtil.doMethod("GET", "/tag", null);
            Assert.assertEquals("["+tag.toString()+"]",response.body);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        finally {
            delete(tag);
        }
    }

    @Test
    public void testAddTag() throws Exception{
            TestUtils.UrlResponse response = testUtil.doMethod("POST", "/tag?name=catty",null);
            Assert.assertEquals("ok",response.body);
            List<Tag> tags = ds.find(Tag.class).asList();
            Assert.assertEquals(1,tags.size());
            Tag tag = tags.get(0);
            Assert.assertEquals("catty",tag.getName());
            delete(tag);
    }

    @Test
    public void testUpdateUser() throws Exception {
        Tag tag = addNewTag();
        TestUtils.UrlResponse response = testUtil.doMethod("PUT", "/tag?name=cat2&id="+tag.getId(), null);
        List<Tag> tags = ds.find(Tag.class).asList();
        Assert.assertEquals("ok",response.body);
        Assert.assertEquals(1,tags.size());
        Assert.assertEquals("cat2",tags.get(0).getName());
        delete(tags.get(0));
    }


    @Test
    public void testGetUserById() {
        Tag tag = addNewTag();
        try{
            TestUtils.UrlResponse response = testUtil.doMethod("GET", "/tag/"+tag.getId(), null);
            Assert.assertEquals(tag.toString(),response.body);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        finally {
            delete(tag);
        }
    }


    @Test
    public void testDeleteById(){
        Tag tag = addNewTag();
        try{
            TestUtils.UrlResponse response = testUtil.doMethod("DELETE", "/tag/"+tag.getId(), null);
            Assert.assertEquals("ok",response.body);
            List<Tag> tags = ds.find(Tag.class).asList();
            Assert.assertEquals(0,tags.size());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        finally {
            delete(tag);
        }

    }


}
