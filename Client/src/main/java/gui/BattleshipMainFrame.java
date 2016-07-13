package gui;

import models.BoardType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

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

    private void setMainFrameProperties(){
        mainFrame.setSize(new Dimension(750,420));
        mainFrame.setTitle("Battleship");
        mainFrame.setLayout(new GridLayout(1,2));
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(userPanel.createPlayerPanel(userBoardPanel));
        mainFrame.add(rivalPanel.createPlayerPanel(rivalBoardPanel));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
