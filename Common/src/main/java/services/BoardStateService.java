package services;

import models.BoardElementState;
import models.Player;

import java.awt.*;
import java.util.Map;

public interface BoardStateService {

    Map<Point, BoardElementState> getBoardState(Player player);
}
