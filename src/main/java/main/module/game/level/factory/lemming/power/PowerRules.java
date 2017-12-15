package main.module.game.level.factory.lemming.power;


import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.map.Map;

public interface PowerRules {

    void action (Lemming lem, Map map);

}
