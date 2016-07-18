package gui;

import org.springframework.beans.factory.annotation.Autowired;
import services.PlayerIdentifierService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BattleshipMainFrame {
    private JFrame mainFrame = new JFrame();

    public void show() {
        setMainFrameProperties();
    }

    @Autowired
    PlayerPanel userPanel;

    @Autowired
    PlayerPanel rivalPanel;

    @Autowired
    BoardPanel userBoardPanel;

    @Autowired
    BoardPanel rivalBoardPanel;

    @Autowired
    PlayerIdentifierService identifierService;

    private void setMainFrameProperties() {
        mainFrame.setSize(new Dimension(750, 420));
        mainFrame.setTitle("Battleship");
        mainFrame.setLayout(new GridLayout(1, 2));
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        JPanel panel = rivalPanel.createPlayerPanel(rivalBoardPanel);
        userPanel.addListener(rivalBoardPanel, panel, mainFrame);
        mainFrame.add(userPanel.createPlayerPanel(userBoardPanel));
        mainFrame.add(panel);
        addRunAwayPlayerListener();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);


    }

    private void addRunAwayPlayerListener() {
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                identifierService.unregisterPlayer();
                event.getWindow().dispose();
            }
        });
    }
}
