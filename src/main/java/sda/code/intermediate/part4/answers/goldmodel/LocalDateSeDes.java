package sda.code.intermediate.part4.answers.goldmodel;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSeDes extends TypeAdapter<LocalDate> {

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        final JsonToken next = in.peek();
        switch (next) {
            case NULL:
                return null;
            case STRING:
                return LocalDate.parse(in.nextString(), DateTimeFormatter.ISO_DATE);
            default:
                throw new IllegalArgumentException("Expected JSON String, got: " + next.toString());
        }
    }

}
