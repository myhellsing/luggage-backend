package test;

import common.Config;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * UserController: integral
 * Date: 15.05.12
 * Time: 21:31
 * To change this template use File | Settings | File Templates.
 */
public class BaseTest {
    static TestUtils testUtil;


    @BeforeClass
    public static void setup() {
        testUtil = new TestUtils(Config.TEST_PORT);
        Config.DATABASE_NAME = Config.TEST_DATABASE_NAME;
    }

    @Test
    public void testHello() {
        try {
            TestUtils.UrlResponse response = testUtil.doMethod("GET", "/hello", null);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.body);
            Assert.assertEquals("Hello World!",response.body);
            Assert.assertEquals(200, response.status);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
