package model;

import models.Board;
import models.BoardElementState;
import models.Ship;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class BoardTest {

    @Test
    public void TestBoardState() {

        // given
        Set<Point> coordinatesFor4length = new HashSet<>();
        coordinatesFor4length.add(new Point(0, 0));
        coordinatesFor4length.add(new Point(0, 1));
        coordinatesFor4length.add(new Point(0, 2));
        coordinatesFor4length.add(new Point(0, 3));
        Set<Point> coordinatesFor2length = new HashSet<>();
        coordinatesFor2length.add(new Point(1, 1));
        coordinatesFor2length.add(new Point(1, 2));
        Set<Ship> ships = new HashSet<>();
        ships.add(Ship.createShip(coordinatesFor4length));
        ships.add(Ship.createShip(coordinatesFor2length));

        // when
        Board board = new Board();
        board.init(ships);
        BoardElementState stateShotedShip = board.shootOnCell(new Point(0, 0));
        BoardElementState stateSinked1 = board.shootOnCell(new Point(1, 1));
        BoardElementState stateSinked2 = board.shootOnCell(new Point(1, 2));

        // then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(stateShotedShip, BoardElementState.SHOOTEDSHIP);
        //softAssert.assertEquals(stateSinked1, State.SINKED);
        softAssert.assertEquals(stateSinked2, BoardElementState.SINKED);
        softAssert.assertAll();
    }

}