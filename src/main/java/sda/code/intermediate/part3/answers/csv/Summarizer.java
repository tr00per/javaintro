package sda.code.intermediate.part3.answers.csv;

import java.io.IOException;

public interface Summarizer {
	void summarizeToFile(String output) throws IOException;
}
