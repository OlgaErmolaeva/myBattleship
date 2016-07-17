package models;


import java.awt.*;

public class Cell {

    BoardElementState state;
    private Ship myShip;

    public Cell() {
        this.state = BoardElementState.EMPTY;
    }

    public BoardElementState getState() {
        return state;
    }

    public void putShip(Ship ship) {
        state = state.SHIP;
        myShip = ship;
    }

    public BoardElementState shoot(Point coordinate) {
        if (state == BoardElementState.EMPTY) {
            state = BoardElementState.SHOOTEDEMPTY;
        }
        if (state == BoardElementState.SHIP) {
            myShip.shoot(coordinate);
            if (myShip.isAlive) {
                state = BoardElementState.SHOOTEDSHIP;
            } else {
                state = BoardElementState.SINKED;
            }
        }
        return state;
    }
}
