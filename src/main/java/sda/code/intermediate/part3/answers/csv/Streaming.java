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
		CSVPrinter printer = CSVFormat.RFC4180.withHeader("Group", "Size", "Avg. Value", "Min. Value", "Max. Value")
				.print(writer);

		try {
			saveStatistics(records, printer);
		} finally {
			printer.close();
		}
	}

	private void saveStatistics(Iterable<CSVRecord> records, CSVPrinter printer) {
		Stream<CSVRecord> stream = StreamSupport.stream(records.spliterator(), false);

		stream.filter(csv -> !csv.get("SAT_AVG_ALL").equalsIgnoreCase("null"))
				.collect(
						Collectors.toMap(csv -> csv.get("STABBR"), Streaming::toSummaryRecord, SummaryRecord::plus))
				.forEach(Streaming.print(printer));
	}

	private static SummaryRecord toSummaryRecord(CSVRecord csv) {
		double value = Double.parseDouble(csv.get("SAT_AVG_ALL"));
		return new SummaryRecord(1, value, value, value);
	}

	private static BiConsumer<String, SummaryRecord> print(CSVPrinter printer) {
		return (String key, SummaryRecord summary) -> {
			try {
				printer.printRecord(key, summary.count, summary.totalValue / summary.count, summary.minValue,
						summary.maxValue);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		};
	}
}
