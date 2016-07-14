package services;


import models.Board;
import models.Player;
import models.Ship;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class GameInitializerImpl implements GameInitializer {

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;


    @Override
    public void initGame(Player player, Set<Ship> ships) {
        if(player == Player.FIRST) {
            firstPlayerBoard.init(ships);
        }
        else{
            secondPlayerBoard.init(ships);
        }
    }

    @Override
    public Board getOpponentBoardFor(Player player) {
        return player == Player.FIRST ? secondPlayerBoard : firstPlayerBoard;
    }
}