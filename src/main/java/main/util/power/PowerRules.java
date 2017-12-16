package main.util.power;


import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.map.Map;
import main.util.geometry.Direction;
import main.util.geometry.Position;

public interface PowerRules {

    void action (Lemming lem, Map map);


}
