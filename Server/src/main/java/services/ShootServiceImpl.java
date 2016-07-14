package services;


import models.Board;
import models.BoardElementState;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

public class ShootServiceImpl implements ShootService{

    @Autowired
    Board board;

    @Override
    public BoardElementState shootOn(Point point) {
        return board.shootOnCell(point);
    }
}
