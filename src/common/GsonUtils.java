package common;

import com.google.gson.Gson;

/**
 * User: integral
 * Date: 16.09.12: 19:56
 */
public class GsonUtils {
    private static Gson gson;

    public static Gson gson(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }

}
