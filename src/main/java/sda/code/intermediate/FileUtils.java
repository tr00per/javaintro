package sda.code.intermediate;

import java.io.*;
import java.util.Properties;

public class FileUtils {

    public Properties loadDefaultProperties() {
        Properties prop = new Properties();
        InputStream config = FileUtils.class.getResourceAsStream("/default.conf");
        try {
            prop.load(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    public static Reader getContents(String name) {
        InputStream is = FileUtils.class.getResourceAsStream(name);
        if (is == null) {
            throw new RuntimeException(new FileNotFoundException(name));
        }
        return new BufferedReader(new InputStreamReader(is));
    }

}
