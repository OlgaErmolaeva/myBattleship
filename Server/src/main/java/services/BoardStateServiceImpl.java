package services;

import models.Board;
import models.BoardElementState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.Map;

public class BoardStateServiceImpl implements BoardStateService {

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Override
    public Map<Point, BoardElementState> getBoardState(Player player) {
        if (player == Player.FIRST) {
            return firstPlayerBoard.getBoardStates();
        } else {
            return secondPlayerBoard.getBoardStates();
        }

    }
}
