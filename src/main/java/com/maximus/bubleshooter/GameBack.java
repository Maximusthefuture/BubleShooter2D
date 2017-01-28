package com.maximus.bubleshooter;

import java.awt.*;

/**
 * Created by Max-R on 21.04.2016.
 */
public class GameBack {
    //Fields
    private Color color;

    //Constructor
    public GameBack(){
        color = Color.BLUE;

    }
    //Functions
    public void update(){

    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

    }


}
