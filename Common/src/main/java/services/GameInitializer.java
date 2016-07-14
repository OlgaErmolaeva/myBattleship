package services;


import models.Player;
import models.Ship;

import java.util.Set;

public interface GameInitializer {
    void initGame(Player player, Set<Ship> ships);
}
