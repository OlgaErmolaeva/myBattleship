package services;


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

    @Override
    public BoardElementState shootOn(Player player, Point point) {
        if(player==Player.FIRST){
            return firstPlayerBoard.shootOnCell(point);
        }else if(player==Player.SECOND){
            return secondPlayerBoard.shootOnCell(point);
        }
        return null;
    }
}
