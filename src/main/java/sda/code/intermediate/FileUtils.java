package sda.code.intermediate;

import java.io.IOException;
import java.io.InputStream;
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

}
