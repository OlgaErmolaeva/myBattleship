package services;


import model.Board;
import models.Player;
import models.Ship;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class GameInitializerImpl implements GameInitializer {

    private static int playerID;

    @Autowired
    private Board board;

    @Override
    public Player initGame(Set<Ship> ships) {
        board.init(ships);
        return null;
    }
}
