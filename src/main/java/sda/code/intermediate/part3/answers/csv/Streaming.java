package sda.code.intermediate.part3.answers.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Streaming implements Summarizer {

	private FileReader reader;

	public Streaming(String input) throws FileNotFoundException {
		reader = new FileReader(input);
	}

	@Override
	public void summarizeToFile(String output) throws IOException {
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(reader);
		Writer writer = new FileWriter(output);
		CSVPrinter printer = CSVFormat.RFC4180.withHeader("County", "Avg. TIV", "Min TIV", "Max TIV").print(writer);

		try {
			Stream<CSVRecord> stream = StreamSupport.stream(records.spliterator(), false);

			stream.collect(Collectors.toMap(csv -> csv.get("county"), Streaming::toSummaryRecord, SummaryRecord::plus))
					.forEach(Streaming.print(printer));
		} finally {
			printer.close();
		}
	}

	private static SummaryRecord toSummaryRecord(CSVRecord csv) {
		double tiv = Double.parseDouble(csv.get("tiv_2012"));
		// String construction = csv.get("construction");
		// String line = csv.get("line");
		return new SummaryRecord(1, tiv, tiv, tiv);
	}

	private static BiConsumer<String, SummaryRecord> print(CSVPrinter printer) {
		return (String key, SummaryRecord summary) -> {
			try {
				printer.printRecord(key, summary.count, summary.totalTIV / summary.count, summary.minTIV,
						summary.maxTIV);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		};

	}
}
