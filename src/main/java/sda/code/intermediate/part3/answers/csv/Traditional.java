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
		CSVPrinter printer = CSVFormat.RFC4180.withHeader("County", "Avg. TIV", "Min TIV", "Max TIV").print(writer);
		try {
			for (Entry<String, SummaryRecord> entry : summarizer.entrySet()) {
				SummaryRecord summary = entry.getValue();
				printer.printRecord(entry.getKey(), summary.count, summary.totalTIV / summary.count, summary.minTIV,
						summary.maxTIV);
			}
		} finally {
			printer.close();
		}
	}

	private void fillSummary(Map<String, SummaryRecord> summarizer) throws IOException {
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(reader);
		for (CSVRecord record : records) {
			String county = record.get("county");
			double tiv = Double.parseDouble(record.get("tiv_2012"));
			// String construction = record.get("construction");
			// String line = record.get("line");

			SummaryRecord summary = summarizer.getOrDefault(county, new SummaryRecord());
			summary.totalTIV += tiv;
			summary.count += 1;
			if (summary.minTIV == null || summary.minTIV > tiv) {
				summary.minTIV = tiv;
			}
			if (summary.maxTIV == null || summary.maxTIV < tiv) {
				summary.maxTIV = tiv;
			}
			summarizer.put(county, summary);
		}
	}
}
