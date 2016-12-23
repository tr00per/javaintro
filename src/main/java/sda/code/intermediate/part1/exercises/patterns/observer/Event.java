package sda.code.intermediate.part1.exercises.patterns.observer;

/**
 * Interfejs zdarzenia będzie implementowany przez konkretne klasy zdarzeń,
 * które zawsze będą ze sobą niosły pewien kontekst - wartość typu T. O klasach
 * implementujących można myśleć, że przenoszą wartość typu T opatrzoną jakąś
 * etykietą, stanowiącą meta-dane dla komunikujących się obiektów.
 */
public interface Event<T> {

	T getContext();
}
