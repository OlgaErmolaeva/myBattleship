package gui;

import models.BoardElementState;
import models.BoardType;
import models.Player;
import models.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import services.GameInitializer;
import services.ShipGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class PlayerPanel {

    @Autowired
    ShipGenerator shipGenerator;

    @Autowired
    GameInitializer gameInitializer;

    private Set<Ship> ships;
    private Player playerID;
    private BoardPanel board;

    public JPanel createPlayerPanel(BoardPanel board) {
        this.board = board;
        JPanel playerPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(board.getBoardType().toString() + " board");
        title.setFont(new Font("Dialog", Font.BOLD, 15));

        playerPanel.add(title, BorderLayout.NORTH);

        playerPanel.add(board, BorderLayout.CENTER);

        if (board.getBoardType().equals(BoardType.Yours)) playerPanel.add(userButtonsPanel(), BorderLayout.SOUTH);
        else playerPanel.add(rivalButtonsPanel(), BorderLayout.SOUTH);

        return playerPanel;
    }


    private JPanel userButtonsPanel() {
        JPanel buttons = new JPanel();
        JButton generateShipsButton = new JButton("Generate ships");
        JButton startButton = new JButton("Start game");
        startButton.setEnabled(false);

        //TODO cut out this part of game logic
        startButton.addActionListener(e -> {
            playerID = gameInitializer.initGame(ships);
        });
        generateShipsButton.addActionListener(e -> {
            board.setEmptyBoard();
            startButton.setEnabled(true);
            try {
                ships = shipGenerator.generateShips();
                ships.stream().forEach(ship -> board.setUserBoardState(ship.getCoordinates(), BoardElementState.SHIP));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Cannot establish connection with server", "Connection error", JOptionPane.ERROR_MESSAGE);
            }
        });

        startButton.addActionListener(actionEvent -> generateShipsButton.setEnabled(false));
        JButton rulesButton = new JButton("Rules");

        rulesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, GameRules.rulesMessage, "Game rules", JOptionPane.INFORMATION_MESSAGE);
        });

        buttons.add(generateShipsButton);
        buttons.add(startButton);
        buttons.add(rulesButton);
        return buttons;
    }

    private JPanel rivalButtonsPanel() {
        JPanel labelPanel = new JPanel();
        JLabel turnLabel = new JLabel("Your turn");
        turnLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        labelPanel.add(turnLabel);
        return labelPanel;
    }
}
