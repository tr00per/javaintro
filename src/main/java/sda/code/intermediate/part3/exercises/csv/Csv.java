package sda.code.intermediate.part3.exercises.csv;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Paths;

public class Csv {

	public static void main(String[] args) throws IOException {
		File csv = Paths.get(System.getProperty("user.home"), "Desktop", "MERGED2012_PP.csv").toFile();
		File summary = Paths.get(System.getProperty("user.home"), "Desktop", "MERGED2012_PP_summary0.csv").toFile();
		Reader reader = new FileReader(csv);
		Writer writer = new FileWriter(summary);

		try {
			summarize(reader, writer);
		} finally {
			if (writer != null) {
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
	}

	private static void summarize(Reader reader, Writer writer) {
		// code goes here...
	}

}
