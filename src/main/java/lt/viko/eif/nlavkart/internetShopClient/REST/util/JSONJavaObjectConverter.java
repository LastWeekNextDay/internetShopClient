package lt.viko.eif.nlavkart.internetShopClient.REST.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONJavaObjectConverter {
    public static <T> T convertJson(String json, Class<T> clazz) {
        T result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String convertObject(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
