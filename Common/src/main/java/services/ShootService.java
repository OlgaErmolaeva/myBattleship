package services;

import models.BoardElementState;
import models.Player;

import java.awt.*;

public interface ShootService {

    BoardElementState shootOn(Player player, Point point);
}
