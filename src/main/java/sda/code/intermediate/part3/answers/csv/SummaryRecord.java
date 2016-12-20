package sda.code.intermediate.part3.answers.csv;

public class SummaryRecord {
	public int count;
	public Double minValue;
	public Double maxValue;
	public double totalValue;

	public SummaryRecord() {
		this.count = 0;
		this.minValue = null;
		this.maxValue = null;
		this.totalValue = 0.0;
	}

	public SummaryRecord(int count, double minValue, double maxValue, double totalValue) {
		this.count = count;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.totalValue = totalValue;
	}

	public static SummaryRecord plus(SummaryRecord sr1, SummaryRecord sr2) {
		return new SummaryRecord(sr1.count + sr2.count, Math.min(sr1.minValue, sr2.minValue),
				Math.max(sr1.maxValue, sr2.maxValue), sr1.totalValue + sr2.totalValue);
	}
}
