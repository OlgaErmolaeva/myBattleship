package services;


import models.Board;
import models.Player;
import models.Ship;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class GameInitializerImpl implements GameInitializer {

    private Player player;

    @Autowired
    private Board board;

    @Override
    public void initGame(Player player, Set<Ship> ships) {
        this.player = player;
        board.init(ships);
        board.setPlayer(player);
    }
}
