package sda.code.intermediate.part3.answers.csv;

import java.io.IOException;

public class Csv {

	public static void main(String[] args) throws IOException {
		System.out.println("0/2 Podsumowuję...");

		Summarizer traditional = new Traditional("/home/czajka/data/insurance_sample.csv");
		traditional.summarizeToFile("/home/czajka/data/insurance_sample_summary0.csv");

		System.out.println("1/2 Wciąż pracuję...");

		Summarizer streaming = new Streaming("/home/czajka/data/insurance_sample.csv");
		streaming.summarizeToFile("/home/czajka/data/insurance_sample_summary1.csv");

		System.out.println("2/2 Skończyłem!");
	}

}
