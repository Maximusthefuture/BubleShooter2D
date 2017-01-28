package com.maximus.bubleshooter;

import java.awt.event.*;


/**
 * Created by MaxRemz on 07.06.2016.
 */
public class Listeners implements KeyListener, MouseListener, MouseMotionListener{



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W){
            Player.up = true;
        }
        if (key == KeyEvent.VK_S){
            Player.down = true;
        }
        if (key == KeyEvent.VK_A){
            Player.left = true;
        }
        if (key == KeyEvent.VK_D){
            Player.right = true;
        }
        if (key == KeyEvent.VK_SPACE){
            Player.isFiring = true;
        }
        if (key == KeyEvent.VK_ESCAPE) {
            GamePanel.state = GamePanel.STATES.MENU;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W){
            Player.up = false;
        }
        if (key == KeyEvent.VK_S){
            Player.down = false;
        }
        if (key == KeyEvent.VK_A){
            Player.left = false;
        }
        if (key == KeyEvent.VK_D){
            Player.right = false;
        }
        if (key == KeyEvent.VK_SPACE){
            Player.isFiring = false;
        }

    }
    public void keyTyped(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            GamePanel.player.isFiring = true;
            GamePanel.leftMouse = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            GamePanel.player.isFiring = false;
            GamePanel.leftMouse = false;
        }
    }

    public void mouseEntered(MouseEvent mouseEvent) {

    }

    public void mouseExited(MouseEvent mouseEvent) {

    }

    public void mouseDragged(MouseEvent mouseEvent) {
        GamePanel.mouseX = mouseEvent.getX();
        GamePanel.mouseY = mouseEvent.getY();
    }

    public void mouseMoved(MouseEvent mouseEvent) {
        GamePanel.mouseX = mouseEvent.getX();
        GamePanel.mouseY = mouseEvent.getY();
    }
}
