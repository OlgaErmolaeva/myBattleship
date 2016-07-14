package services;


import models.Board;
import models.Player;
import models.Ship;

import java.util.Set;

public interface GameInitializer {
    void initGame(Player player, Set<Ship> ships);

    Board getOpponentBoardFor(Player player);
}
