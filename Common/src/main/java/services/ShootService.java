package services;

import exceptions.NotActualPlayerException;
import models.BoardElementState;
import models.Player;

import java.awt.*;

public interface ShootService {

    BoardElementState shootOn(Player player, Point point) throws NotActualPlayerException;
}
