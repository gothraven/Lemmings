package module.game.level.lemming.state;

import module.game.level.lemming.Lemming;
import module.game.level.map.Map;

public interface LemmingState {

    void action (Lemming lem);

    void changeState(LemmingState state);
}
