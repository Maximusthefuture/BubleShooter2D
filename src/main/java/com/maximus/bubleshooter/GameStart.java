package com.maximus.bubleshooter;

import javax.swing.*;

/**
 * Created by Max-R on 20.04.2016.
 */
public class GameStart {

    public static void main(String [] args) {
        GamePanel panel = new GamePanel();
        JFrame startFrame = new JFrame("BubbleGame");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.pack();
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
        panel.start();
    }
}
