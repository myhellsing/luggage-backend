package test;

import spark.utils.IOUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class TestUtils {

    private int port;

    public TestUtils(int port) {
        this.port = port;
    }

    public UrlResponse doMethod(String requestMethod, String path, String body)
            throws Exception {
        URL url = new URL("http://localhost:" + port + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);

        if ( (requestMethod.equals("POST") ||requestMethod.equals("PUT") ) && body != null) {
            connection.setDoOutput(true);
            connection.getOutputStream().write(body.getBytes());
        }

        connection.connect();

        String res = IOUtils.toString(connection.getInputStream());
        UrlResponse response = new UrlResponse();
        response.body = res;
        response.status = connection.getResponseCode();
        response.headers = connection.getHeaderFields();
        return response;
    }

    public int getPort() {
        return port;
    }

    public static class UrlResponse {

        public Map<String, List<String>> headers;
        public String body;
        public int status;
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {}
    }

}