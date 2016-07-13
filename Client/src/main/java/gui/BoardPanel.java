package gui;

import models.BoardElementState;
import models.BoardType;
import org.springframework.beans.factory.annotation.Autowired;
import services.ShootService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BoardPanel extends JPanel {
    private final int weight = 350;
    private final int height = 350;
    private final int size = 10;

    private Map<Point, BoardElementState> userBoardState = new HashMap<>();

    @Autowired
    ShootService shootService;

    private BoardType boardType;

    public BoardPanel(BoardType boardType) {
        this.boardType = boardType;
        setEmptyBoard();
        addListener(boardType);
    }

    public void setUserBoardState(Set<Point> coordinates, BoardElementState state) {
        coordinates.stream().forEach(point -> userBoardState.put(point, state));
        repaint();
    }

    public final void setEmptyBoard() {
        setSize(new Dimension(weight, height));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                userBoardState.put(new Point(i, j), BoardElementState.EMPTY);
            }
        }
        repaint();
    }

    private void addListener(final BoardType boardType) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (boardType.equals(BoardType.Rivals)) {
                    Point p = new Point(mouseEvent.getX() / (weight / size), mouseEvent.getY() / (height / size));
                    BoardElementState updateState = shootService.shootOn(p);
                    userBoardState.put(p, updateState);
                    repaint();
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Image img = createImage(weight, height);

        Graphics2D g2 = (Graphics2D) img.getGraphics();

        g2.setColor(Color.cyan);
        g2.fillRect(0, 0, weight, height);


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                g2.setColor(userBoardState.get(new Point(i, j)).getColor());
                g2.fillRect((weight / size) * i, (height / size) * j, (weight / size) - 2, (height / size) - 2);
            }
        }
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public BoardType getBoardType() {
        return boardType;
    }
}


