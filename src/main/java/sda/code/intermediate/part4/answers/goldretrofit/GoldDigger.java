package sda.code.intermediate.part4.answers.goldretrofit;

import static sda.code.intermediate.part4.answers.goldretrofit.Messages.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.BiConsumer;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sda.code.intermediate.part4.answers.goldmodel.GoldPrice;

public class GoldDigger {
	private static final double THRESHOLD_SELL = 1.05;
	private static final double THRESHOLD_BUY = 0.95;
	private static final int SHORT_PERIOD_IN_DAYS = 3;

	private final GoldService service;
	private final SummaryWriter summary;

	public GoldDigger(String baseUrl, String summaryLocation) {
		final Retrofit retrofit = createRetrofit(baseUrl);
		service = retrofit.create(GoldService.class);
		summary = new SummaryWriter(summaryLocation);
	}

	private Retrofit createRetrofit(String baseUrl) {
		return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
	}

	public void execute(int days) {
		validateInput(days);

		System.out.println(WARNING_NOTICE);

		Call<List<GoldPrice>> call = service.byDays(days);
		try {
			runCall(call, summary::write);
		} catch (IOException e) {
			System.err.println(fmt(FAILED_RETRIVAL, e.getMessage()));
		}
	}

	private void runCall(Call<List<GoldPrice>> call, BiConsumer<List<GoldPrice>, String> next) throws IOException {
		Response<List<GoldPrice>> response = call.execute();
		if (response.isSuccessful()) {
			processPrices(response.body(), next);
		} else {
			System.err.println(fmt(FAILED_HTTP, response.code()));
		}
	}

	private void processPrices(List<GoldPrice> prices, BiConsumer<List<GoldPrice>, String> next) {
		OptionalDouble avgAll = average(prices);
		OptionalDouble avgRecent = averageRecent(prices);
		String recommendation = ratioToRecommendation(avgRecent, avgAll);

		System.out.println(fmt(SUMMARY_MSG, avgToString(avgAll), avgToString(avgRecent)));
		System.out.println(fmt(RECOMMEND_MSG, recommendation));

		next.accept(prices, recommendation);
	}

	private String ratioToRecommendation(OptionalDouble avgRecent, OptionalDouble avgAll) {
		if (!avgAll.isPresent()) {
			return FAILED_AVG_COMPUTATION;
		}
		if (!avgRecent.isPresent()) {
			return FAILED_RECENT_AVG_COMPUTATION;
		}

		double ratio = avgRecent.getAsDouble() / avgAll.getAsDouble();
		if (ratio < THRESHOLD_BUY)
			return RECOMMEND_BUY;
		else if (ratio > THRESHOLD_SELL)
			return RECOMMEND_SELL;
		else
			return RECOMMEND_HOLD;
	}

	private static OptionalDouble average(List<GoldPrice> prices) {
		return prices.stream().mapToDouble(x -> x.getPrice()).average();
	}

	private static OptionalDouble averageRecent(List<GoldPrice> prices) {
		if (prices.size() >= SHORT_PERIOD_IN_DAYS) {
			return prices.subList(prices.size() - SHORT_PERIOD_IN_DAYS, prices.size()).stream()
					.mapToDouble(x -> x.getPrice()).average();
		} else {
			return OptionalDouble.empty();
		}
	}

	private static String avgToString(OptionalDouble avg) {
		if (avg.isPresent()) {
			BigDecimal val = new BigDecimal(avg.getAsDouble(), MathContext.DECIMAL32);
			return val.stripTrailingZeros().toPlainString();
		} else {
			return UNKNOWN;
		}
	}

	private static void validateInput(int days) {
		if (days > GoldService.LONGEST_PERIOD_IN_DAYS) {
			throw new IllegalArgumentException(fmt(ERROR_TOO_LARGE, days, GoldService.LONGEST_PERIOD_IN_DAYS));
		}
		if (days <= SHORT_PERIOD_IN_DAYS) {
			throw new IllegalArgumentException(fmt(ERROR_TOO_SMALL, days, SHORT_PERIOD_IN_DAYS));
		}
	}

}
