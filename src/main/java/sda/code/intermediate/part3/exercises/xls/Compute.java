package sda.code.intermediate.part3.exercises.xls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;

public class Compute {

	public static void main(String[] args) throws IOException {
		// F1 - mnożnik, G1 - wynik
		File spreadsheet = Paths.get(System.getProperty("user.home"), "Desktop", "spreadsheet.xls").toFile();
		try (NPOIFSFileSystem fs = new NPOIFSFileSystem(spreadsheet)) {
			System.out.println("Formuła G1: ");

			System.out.println("Wartość bez uzupełnienia F1: ");
			
			System.out.println("Wartość po uzupełnieniu F1, z pamięci podręcznej: ");

			System.out.println("Wartość po uzupełnieniu F1: ");
		}
	}

}
