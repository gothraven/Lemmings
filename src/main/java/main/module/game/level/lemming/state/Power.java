package main.module.game.level.lemming.state;


import main.module.game.level.lemming.Lemming;

public interface Power {

    void action (Lemming lem);

    void changePower (Power power);
}
