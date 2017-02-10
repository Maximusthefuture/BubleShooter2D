package com.maximus.bubleshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 * Created by Max-R on 20.04.2016.
 */
public class GamePanel extends JPanel implements Runnable {


    //Field
    public static int WIDTH = 600;
    public static int HEIGHT = 600;

    public static int mouseX;
    public static int mouseY;
    public static boolean leftMouse;
    public static int bossRank;
    public static int bossType;


    private Thread thread = new Thread(this);

    private BufferedImage image;
    private Graphics2D g;

    private int FPS = 30;
    private long timerFPS;
    private double milisToFPS;
    private int sleepTime;

    public enum STATES  {
        MENU,
        PLAY,
        NEW_GAME,
        SETTINGS,

    }

    public static STATES state = STATES.MENU;

    public static GameBack background;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
//    public static ArrayList<Boss> boss;
    public static Wave wave;
    public static Boss boss;
    public static Menu menu;


    //Constructor
    public GamePanel() {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(new Listeners());

        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());


    }


    //Functions
   public void start(){
       thread = new Thread(this);
       thread.start();

   }


    public void run() {
       FPS = 30;
       milisToFPS = 1000 / FPS;
       sleepTime = 0;
       bossRank = 1;
       bossType = 1;


        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        String name = g.getClass().getName();
//        System.out.println(name);
//        sun.java2d.SunGraphics2D
        leftMouse = false;
        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
//        boss = new ArrayList<Boss>();//TODO
        boss = new Boss(bossType, bossRank);
        wave = new Wave();
        menu = new Menu();

        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage buffered = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) buffered.getGraphics();
        g3.setColor(new Color(255, 255, 255));
        g3.drawOval(0, 0 ,4, 4);
        g3.drawLine(2, 0, 2, 4);
        g3.drawLine(0, 2, 4, 2);
        Cursor myCursor = kit.createCustomCursor(buffered, new Point(3, 3), "myCursor");
        g3.dispose();


//        //TODO delete
//        enemies.add(new Enemy(1,1));
//        enemies.add(new Enemy(1,1));

        while (true) { //TODO States
            this.setCursor(myCursor);
            timerFPS = System.nanoTime();

            if (state.equals(STATES.MENU)) {
                background.update();
                background.draw(g);
                menu.update();
                menu.draw(g);
                gameDraw();
            }
            if (state.equals(STATES.PLAY)) {
                gameUpdate();
                gameRender();
                gameDraw();
            }

            timerFPS = (System.nanoTime() - timerFPS ) / 1000000;
            if (milisToFPS > timerFPS) {
                sleepTime = (int)(milisToFPS - timerFPS);
            } else sleepTime = 1;
            try {
                thread.sleep(sleepTime);
                System.out.println("FPS: " + sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 1;
        }
    }

    public void gameUpdate() {
       //BackGround update
        background.update();
        //Player update
        player.update();
        //Boss update
        boss.update();
//        for (int i = 0; i < boss.size(); i++) {
//            boss.get(i).update();
//        }

        //Bullet update
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            boolean remove = bullets.get(i).remove();
            if (remove) {
                bullets.remove(i);
                i--;
            }
        }
        //Enemies update
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
        //Bullets - enemies collide
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();
            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                double bx = b.getX();
                double by = b.getY();
                double dx = ex - bx;
                double dy = ey - by;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if ((int) dist < e.getR() + b.getR()) {
                    e.hit();
                    bullets.remove(j);
                    j--;
                    boolean remove = e.remove();
                    if (remove) {
                        enemies.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        //Bullet - boss collide
        //Wave update
        wave.update();
        //Player- enemy collides
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();
            double dx = ex - px;
            double dy = ey - py;
            double dist = Math.sqrt(dx * dx + dy * dy);
            if ((int) dist <= e.getR() + player.getR()) {
                e.hit();
                player.hit();
                boolean remove = e.remove();
                if (remove) {
                    enemies.remove(i);
                    i--;
                }
            }
        }
    }

    public void gameRender(){
        //Background draw
        background.draw(g);
        //Player draw
        player.draw(g);
        //Boss draw
        boss.draw(g);
//        for (int i = 0; i < boss.size(); i++) {
//            boss.get(i).draw(g);
//        }

        //Bullets draw
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }
        //Enemies draw
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
//        Wave draw
        if (wave.showWave()) {
            wave.draw(g);
        }
    }

    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}



