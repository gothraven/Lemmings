package main.util.power;


import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;

import java.util.ArrayList;

public interface PowerRules {

    void action (Lemming lem, Map map, ArrayList<Lemming> lems);


}
