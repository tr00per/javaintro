package sda.code.intermediate.part4.answers.gameoflife;

public interface GameEntity {

    boolean isAlive();

    GameEntity descendant();

}