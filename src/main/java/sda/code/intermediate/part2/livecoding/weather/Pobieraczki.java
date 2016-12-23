package sda.code.intermediate.part2.livecoding.weather;

public class Pobieraczki {

	public static PobieraczkaPogody dlaTypu(String klient) {
		if (klient == null) {
			throw new IllegalArgumentException("Klient musi być określony!");
		}
		switch (klient.toLowerCase()) {
		case "sync":
			return new SynchornicznaPobieraczkaPogody("metric", "pl");
		default:
			throw new IllegalArgumentException("Nierozpoznany typ klienta: " + klient);
		}
	}

}
