package sda.code.intermediate.part2;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import sda.code.intermediate.FileUtils;
import sda.code.intermediate.part2.answers.json.gson.WeatherGson;
import sda.code.intermediate.part2.answers.json.jackson.WeatherJackson;

import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.fail;

public class JSON {

    private Gson gson;
    private ObjectMapper jackson;

    @Before
    public void setup() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        jackson = new ObjectMapper();
        jackson.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @Test
    public void serializeUsingGson() {
        gson.toJson(new Object());
    }

    @Test
    public void deserializeUsingGson() {
        gson.fromJson("{}", Object.class);
    }

    @Test
    public void deserializeFileUsingGson() {
        Reader input = FileUtils.getContents("/model.json");
        WeatherGson weather = gson.fromJson(input, WeatherGson.class);
        System.out.println(weather.toString());
    }

    @Test
    public void serializeUsingJackson() {
        try {
            jackson.writeValueAsString(new Object());
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deserializeUsingJackson() {
        try {
            jackson.readValue("{}", Object.class);
        } catch (JsonParseException e) {
            fail(e.getMessage());
        } catch (JsonMappingException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deserializeFileUsingJackson() {
        Reader input = FileUtils.getContents("/model.json");
        WeatherJackson weather = null;
        try {
            weather = jackson.readValue(input, WeatherJackson.class);
        } catch (JsonParseException e) {
            fail(e.getMessage());
        } catch (JsonMappingException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
        System.out.println(weather.toString());
    }
}
