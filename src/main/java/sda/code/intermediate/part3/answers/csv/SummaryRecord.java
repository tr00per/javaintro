package sda.code.intermediate.part3.answers.csv;

public class SummaryRecord {
	public int count;
	public Double minTIV;
	public Double maxTIV;
	public double totalTIV;

	public SummaryRecord() {
		this.count = 0;
		this.minTIV = null;
		this.maxTIV = null;
		this.totalTIV = 0.0;
	}

	public SummaryRecord(int count, double minTIV, double maxTIV, double totalTIV) {
		this.count = count;
		this.minTIV = minTIV;
		this.maxTIV = maxTIV;
		this.totalTIV = totalTIV;
	}

	public static SummaryRecord plus(SummaryRecord sr1, SummaryRecord sr2) {
		return new SummaryRecord(sr1.count + sr2.count, Math.min(sr1.minTIV, sr2.minTIV),
				Math.max(sr1.maxTIV, sr2.maxTIV), sr1.totalTIV + sr2.totalTIV);
	}
}
