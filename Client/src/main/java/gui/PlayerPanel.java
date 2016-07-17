package gui;

import models.BoardElementState;
import models.BoardType;
import models.Player;
import models.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import services.BoardStateService;
import services.GameInitializer;
import services.PlayerIdentifierService;
import services.ShipGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlayerPanel {

    @Autowired
    ShipGenerator shipGenerator;

    @Autowired
    PlayerIdentifierService identifierService;

    @Autowired
    BoardStateService boardStateService;

    @Autowired
    GameInitializer gameInitializer;

    private JButton startButton = new JButton("Start game");
    private JButton generateShipsButton = new JButton("Generate ships");

    private Set<Ship> ships;
    private Player player;
    private BoardPanel boardPanel;

    public JPanel createPlayerPanel(BoardPanel board) {
        this.boardPanel = board;
        JPanel playerPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(board.getBoardType().toString() + " board ");
        title.setFont(new Font("Dialog", Font.BOLD, 15));

        playerPanel.add(title, BorderLayout.NORTH);

        playerPanel.add(board, BorderLayout.CENTER);

        if (board.getBoardType().equals(BoardType.Yours)) playerPanel.add(userButtonsPanel(), BorderLayout.SOUTH);
        //else playerPanel.add(rivalButtonsPanel(), BorderLayout.SOUTH);

        return playerPanel;
    }


    private JPanel userButtonsPanel() {
        JPanel buttons = new JPanel();
        startButton.setEnabled(false);

        //TODO cut out this part of game logic
        generateShipsButton.addActionListener(e -> {
            boardPanel.setEmptyBoard();
            startButton.setEnabled(true);
            try {
                ships = shipGenerator.generateShips();
                ships.stream().forEach(ship -> boardPanel.setUserBoardState(ship.getCoordinates(), BoardElementState.SHIP));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Cannot establish connection with server", "Connection error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton rulesButton = new JButton("Rules");

        rulesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, GameRules.rulesMessage, "Game rules", JOptionPane.INFORMATION_MESSAGE);
        });

        buttons.add(generateShipsButton);
        buttons.add(startButton);
        buttons.add(rulesButton);
        return buttons;
    }

    /*private JPanel rivalButtonsPanel() {
        JPanel labelPanel = new JPanel();
        JLabel turnLabel = new JLabel("Your turn");
        turnLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        labelPanel.add(turnLabel);
        return labelPanel;
    }*/

    public void addListener(BoardPanel board,JPanel rivalPanel) {
        startButton.addActionListener(e -> {
            player = identifierService.identifiesPlayer();
            boardPanel.addListener(board,rivalPanel, player);
            gameInitializer.initGame(player, ships);
            generateShipsButton.setEnabled(false);


            new SwingWorker<Void, Map<Point, BoardElementState>>() {

                @Override
                protected Void doInBackground() throws Exception {
                    while(true) {
                        publish(boardStateService.getBoardState(player));

                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

                @Override
                protected void process(List<Map<Point, BoardElementState>> chunks) {
                    boardPanel.setState(chunks.get(chunks.size() - 1));
                    boardPanel.repaint();
                }
            }.execute();
        });
    }
}
