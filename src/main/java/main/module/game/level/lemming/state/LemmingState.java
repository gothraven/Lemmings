package main.module.game.level.lemming.state;


import main.module.game.level.lemming.Lemming;

public interface LemmingState {

    void action (Lemming lem);

    void changeState(LemmingState state);
}
