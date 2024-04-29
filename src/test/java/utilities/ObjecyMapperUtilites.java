package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.BookingPojo;

public class ObjecyMapperUtilites {
    //new TypeRef<>() {} --> This takes the data type and assign json to that data type.
    public static <T> T conversJsonToJava(String json, Class<T> cls){
        try {
            return new ObjectMapper().readValue(json,cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
