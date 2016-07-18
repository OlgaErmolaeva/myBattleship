package services;


import exceptions.NotActualPlayerException;
import models.Board;
import models.BoardElementState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

public class ShootServiceImpl implements ShootService {

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Autowired
    private ActualPlayerService actualPlayerService;

    @Override
    public BoardElementState shootOn(Player player, Point point) throws NotActualPlayerException {
        if (!actualPlayerService.isActualPlayer(player)) {
            throw new NotActualPlayerException("Not your move. Wait.");
        }

        BoardElementState state;
        if (player == Player.FIRST) {
            BoardElementState stateBeforeShoot = secondPlayerBoard.getCellState(point);
            if (stateBeforeShoot == BoardElementState.EMPTY) {
                actualPlayerService.changeActualPlayer();
            }
            state = secondPlayerBoard.shootOnCell(point);

        } else {
            BoardElementState stateBeforeShoot = firstPlayerBoard.getCellState(point);
            if (stateBeforeShoot == BoardElementState.EMPTY) {
                actualPlayerService.changeActualPlayer();
            }
            state = firstPlayerBoard.shootOnCell(point);

        }

        return state;
    }
}