package sda.code.intermediate.part4.answers.gameoflife;

import java.util.Optional;
import java.util.function.Consumer;

public interface GameWorld {

    GameWorld step(Filler stepFiller);

    void forEach(Consumer<GameEntity> entityEnjoyer);

    Optional<GameEntity> get(int x, int y);

}