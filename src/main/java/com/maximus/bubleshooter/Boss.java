package com.maximus.bubleshooter;

import java.awt.*;

/**
 * Created by maximus on 28.01.17.
 */
public class Boss{
    private double x;
    private double y;
    private int r;

    private double speed;
    private double dx;
    private double dy;

    private double health;

    private int type;
    private int rank;

    private Color color;

    public Boss(int type, int rank) {
        this.type = type;
        this.rank = rank;
        switch(type){
            case 1: color = Color.BLACK;
                switch (rank){
                    case 1:
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 7;
                        speed = 5;
                        health = 10;

                        double angle = Math.toRadians(Math.random() * 360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;


                }
        }
        switch (type) {
            case 2:
                color = Color.BLACK;
                switch (rank) {
                    case 2:
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 7;
                        speed = 6;
                        health = 10;

                        double angle = Math.toRadians(Math.random() * 360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                }
        }
}

    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }


    public int getR() {
       return r;
    }


    public boolean remove() {
        if (health <= 0) {
            return true;
        }
        return false;
    }


    public void hit() {
        health--;
        System.out.println("Boss health:" + health);
    }


    public void update() {
        x += dx;
        y += dy;

        if (x < 0 && dx < 0) dx = -dx;
        if (x > GamePanel.WIDTH && dx > 0) dx = -dx;
        if (y < 0 && dy < 0) dy = -dy;
        if (y > GamePanel.HEIGHT && dy > 0) dy = -dy;
    }


    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)x - r, (int)y - r, 5 * r, 5 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int)x - r, (int)y - r, 5 * r, 5 * r);
        g.setStroke(new BasicStroke(3));
    }
    //TODO
}
