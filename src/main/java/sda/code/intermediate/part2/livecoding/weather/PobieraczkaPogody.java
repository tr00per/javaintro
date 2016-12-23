package sda.code.intermediate.part2.livecoding.weather;

import java.net.URI;
import java.util.Optional;

public interface PobieraczkaPogody {

	Optional<String> wykonajZapytanie(URI uri);

}