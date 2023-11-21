package MainGame;

import Settings.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {

    public static int ESCAPE_KEY_COUNTER = 0;
    int velocityX = 0, velocityY = 0;
    int speed = 5;

    int startX = 0, startY = 0;

    public Player(int x, int y) {
        super(x, y);
    }

    public void update() {
        x += velocityX;
        y += velocityY;

        checkCollision();
        checkOffScreen();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(getPlayerImg(), x, y, null);
//        g2d.draw(getBounds()); // For view the boundaries of the players
    }

    public Image getPlayerImg() {
        ImageIcon ic = new ImageIcon("./src/resources/" + Settings.selectPlayer + ".png");
        return ic.getImage();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            velocityY = -speed;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            velocityY = speed;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            velocityX = -speed;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            velocityX = speed;
        } else if (key == KeyEvent.VK_SPACE) {
            Missile missile = new Missile(x, y);
            if(Settings.soundOn) {
                Sound.missileSound();
            }
            GameFrame.addMissile(missile);
        } else if (key == KeyEvent.VK_ESCAPE) {
            ESCAPE_KEY_COUNTER++;
            if(ESCAPE_KEY_COUNTER % 2 == 1) {
                GameFrame.mainTimer.stop();
            } else {
                GameFrame.mainTimer.start();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            velocityY = 0;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            velocityY = 0;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            velocityX = 0;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            velocityX = 0;
        }
    }


    public void checkCollision() {
        ArrayList<Enemy> enemies = GameFrame.getEnemeyList();

        for(int i = 0; i < enemies.size(); i++) {
            Enemy tempEnemy = enemies.get(i);
            if(getBounds().intersects(tempEnemy.getBounds())) {
                Score.gameOver = true;
                GameFrame.mainTimer.stop();

                if(Settings.musicOn) {
                    Sound.clipMusic.stop();
                }

                JOptionPane.showMessageDialog(null, "You died on level " + GameFrame.level + " ,try better next time");

                File file = new File("./src/Settings/data.txt");
                try (FileWriter fileWriter = new FileWriter(file, true);) {
                    fileWriter.write(Settings.name + " " + Score.score+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    public void checkOffScreen() {
        if(x < 0) {
            x = 0;
        }

        else if(x >= 580 - getPlayerImg().getWidth(null)) {
            x = 580 - getPlayerImg().getWidth(null);
        }

        else if(y < 0) {
            y = 0;
        }

        else if(y >= 580 - getPlayerImg().getHeight(null)) {
            y = 580 - getPlayerImg().getHeight(null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getPlayerImg().getWidth(null), getPlayerImg().getHeight(null));
    }
}
