package com.maximus.bubleshooter;

import java.awt.*;

/**
 * Created by maximus on 28.01.17.
 */
public class Menu {
    //Fields
    private int buttonWidth;
    private int buttonHeight;
    private Color color;
    private String menuText;
    //Прозрачность
    private int transp = 0;

    //Constructor
    public Menu() {
        buttonWidth = 120;
        buttonHeight = 60;

        color = Color.WHITE;
        menuText = "PLAY";
    }

    //Function
    public void update() {
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2
                && GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2
                && GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2
                && GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
            transp = 60;
            if (GamePanel.leftMouse) {
                GamePanel.state = GamePanel.STATES.PLAY;
            }
        } else {
            transp = 0;
        }
    }

    public void draw(Graphics2D g) {
        //Play button
        g.setColor(color);
        g.setStroke(new BasicStroke(3));
        g.drawRect(GamePanel.WIDTH / 2 - buttonWidth / 2,
                GamePanel.HEIGHT / 2 - buttonHeight / 2,buttonWidth, buttonHeight);
        g.setColor(new Color(255, 255, 255, transp));
        //Чтобы начинала мигать
        g.fillRect(GamePanel.WIDTH / 2 - buttonWidth / 2,
                GamePanel.HEIGHT / 2 - buttonHeight / 2,buttonWidth, buttonHeight);
        g.setStroke(new BasicStroke(1));
        g.setColor(color);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        long length = (int) g.getFontMetrics().getStringBounds(menuText, g).getWidth();
        g.drawString(menuText, (int)GamePanel.WIDTH / 2 - length / 2,
                (int) GamePanel.HEIGHT / 2 + buttonHeight / 4);



    }
}
