package sda.code.intermediate.part3.answers.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Traditional implements Summarizer {

	private Reader reader;

	public Traditional(String input) throws FileNotFoundException {
		reader = new FileReader(input);
	}

	public void summarizeToFile(String output) throws IOException {
		Map<String, SummaryRecord> summarizer = new HashMap<>();
		fillSummary(summarizer);
		saveSummary(summarizer, output);
	}

	private void saveSummary(Map<String, SummaryRecord> summarizer, String output) throws IOException {
		Writer writer = new FileWriter(output);
		CSVPrinter printer = CSVFormat.RFC4180.withHeader("Group", "Size", "Avg. Value", "Min. Value", "Max. Value")
				.print(writer);
		try {
			printEntries(printer, summarizer);
		} finally {
			printer.close();
		}
	}

	private void printEntries(CSVPrinter printer, Map<String, SummaryRecord> summarizer) throws IOException {
		for (Entry<String, SummaryRecord> entry : summarizer.entrySet()) {
			printEntry(printer, entry);
		}
	}

	private void printEntry(CSVPrinter printer, Entry<String, SummaryRecord> entry) throws IOException {
		SummaryRecord summary = entry.getValue();
		printer.printRecord(entry.getKey(), summary.count, summary.totalValue / summary.count, summary.minValue,
				summary.maxValue);
	}

	private void fillSummary(Map<String, SummaryRecord> summarizer) throws IOException {
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(reader);
		for (CSVRecord record : records) {
			updateWithRecord(summarizer, record);
		}
	}

	private void updateWithRecord(Map<String, SummaryRecord> summarizer, CSVRecord record) {
		double value;
		try {
			value = Double.parseDouble(record.get("SAT_AVG_ALL"));
		} catch (NumberFormatException e) {
			return;
		}
		final String group = record.get("STABBR");

		SummaryRecord summary = summarizer.getOrDefault(group, new SummaryRecord());

		summary.totalValue += value;
		summary.count += 1;
		if (summary.minValue == null || summary.minValue > value) {
			summary.minValue = value;
		}
		if (summary.maxValue == null || summary.maxValue < value) {
			summary.maxValue = value;
		}
		summarizer.put(group, summary);
	}
}
