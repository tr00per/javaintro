package sda.code.intermediate.part4.answers.gameoflife;

import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public class Populations {
    private static Random rng = new Random();

    private GameEntityFactory entityFactory;

    public Populations(GameEntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    public Filler random(double newbornRatio) {
        return (w, x, y) -> rng.nextDouble() < newbornRatio ? entityFactory.newborn() : entityFactory.dead();
    }

    public Filler classic() {
        return cellularAutomaton(ns -> ns == 3, ns -> ns == 2 || ns == 3);
    }

    public Filler highlife() {
        return cellularAutomaton(ns -> ns == 3 || ns == 6, ns -> ns == 2 || ns == 3);
    }

    public Filler dayAndNight() {
        return cellularAutomaton(ns -> ns == 3 || ns == 6 || ns == 7 || ns == 8,
                ns -> ns == 3 || ns == 4 || ns == 6 || ns == 7 || ns == 8);
    }

    private Filler cellularAutomaton(Function<Integer, Boolean> shouldBeBorn,
                                     Function<Integer, Boolean> shouldSurvive) {
        return (w, x, y) -> {
            final int neighbours = countMooreNeighbours(w, x, y);
            final Optional<GameEntity> current = w.get(x, y);
            if (current.isPresent() && current.get().isAlive()) {
                if (shouldSurvive.apply(neighbours)) {
                    return current.get().descendant();
                } else {
                    return entityFactory.dead();
                }
            } else {
                if (shouldBeBorn.apply(neighbours)) {
                    return entityFactory.newborn();
                } else {
                    return entityFactory.dead();
                }
            }
        };
    }

    private static int countMooreNeighbours(GameWorld w, int x, int y) {
        int count = 0;
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                final Optional<GameEntity> neighbour = w.get(x + dx, y + dy);
                if (neighbour.isPresent() && neighbour.get().isAlive()) {
                    count += 1;
                }
            }
        }
        return count;
    }
}
