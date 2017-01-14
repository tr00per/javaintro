package sda.code.intermediate.part4.answers.goldretrofit;

public class Messages {
	static final String PROMPT = "Enter number of days [%d]: ";
	static final String WARNING_NOTICE = "Warning, this is not an investment advice.";
	static final String ERROR_TOO_LARGE = "%d days > %d days";
	static final String ERROR_TOO_SMALL = "%d days <= %d days";
	static final String UNKNOWN = "unknown";
	static final String FAILED_HTTP = "Call was unsuccessful, HTTP code: %d";
	static final String FAILED_RETRIVAL = "Failed during price retrival: %s";
	static final String SUMMARY_MSG = "Overall average: %s, recent average: %s";
	static final String RECOMMEND_MSG = "Recommendation: %s";
	static final String FAILED_AVG_COMPUTATION = "No results found, can't compute the average";
	static final String FAILED_RECENT_AVG_COMPUTATION = "Not enough result, can't compute the recent average";
	static final String RECOMMEND_BUY = "BUY";
	static final String RECOMMEND_SELL = "SELL";
	static final String RECOMMEND_HOLD = "HOLD";
	static final String FAILED_SAVING_SUMMARY = "Failed to save the summary file: %s";
	static final String SUCCESSFUL_SUMMARY_SAVE = "Saved summary at: %s";
	static final String SHEET_NAME_DATA = "Data";
	static final String SHEET_NAME_PLOT = "Plot";

	public static String fmt(String format, Object... objects) {
		return String.format(format, objects);
	}

}
