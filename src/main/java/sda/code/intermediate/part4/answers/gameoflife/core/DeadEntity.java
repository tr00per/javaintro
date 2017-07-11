package sda.code.intermediate.part4.answers.gameoflife.core;

import sda.code.intermediate.part4.answers.gameoflife.GameEntity;

class DeadEntity extends Entity {

    DeadEntity() {
        super(-1);
    }

    @Override
    public GameEntity descendant() {
        throw new IllegalStateException("Dead entities cannot have descendants");
    }
}
