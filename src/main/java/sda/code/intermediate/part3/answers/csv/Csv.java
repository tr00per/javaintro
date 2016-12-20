package sda.code.intermediate.part3.answers.csv;

import java.io.IOException;

public class Csv {

	public static void main(String[] args) throws IOException {
		System.out.println("0/2 Podsumowuję...");

		Summarizer traditional = new Traditional("/home/czajka/data/MERGED2012_PP.csv");
		traditional.summarizeToFile("/home/czajka/data/MERGED2012_PP_summary0.csv");

		System.out.println("1/2 Wciąż pracuję...");

		Summarizer streaming = new Streaming("/home/czajka/data/MERGED2012_PP.csv");
		streaming.summarizeToFile("/home/czajka/data/MERGED2012_PP_summary1.csv");

		System.out.println("2/2 Skończyłem!");
	}

}
