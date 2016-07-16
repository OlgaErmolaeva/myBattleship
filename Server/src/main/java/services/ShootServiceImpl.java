package services;


import exceptions.NotActualPlayerException;
import models.Board;
import models.BoardElementState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

public class ShootServiceImpl implements ShootService{

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Autowired
    private ActualPlayerService actualPlayerService;

    @Override
    public BoardElementState shootOn(Player player, Point point) throws NotActualPlayerException {
        if(!actualPlayerService.isActualPlayer(player)) {
            throw new NotActualPlayerException("Not your move. Wait.");
        }

        BoardElementState state = null;
        if(player==Player.FIRST){
            state = secondPlayerBoard.shootOnCell(point);
        }else if(player==Player.SECOND) {
            state = firstPlayerBoard.shootOnCell(point);
        }
        if(state == BoardElementState.SHOOTEDEMPTY) {
            actualPlayerService.changeActualPlayer();
        }

        return state;
    }
}