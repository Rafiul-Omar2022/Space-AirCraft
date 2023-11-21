package MainGame;

import Settings.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class GameFrame extends JPanel implements ActionListener {

    public static Timer mainTimer;
    Player player;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Missile> missiles = new ArrayList<>();

    int enemyCount;
    public static int level = 1;
    public static Score score;
    public GameFrame() {
        setFocusable(true);
        player = new Player(300,500);
        score = new Score();
        Score.gameOver = false;

        Score.highScore = getMaxScore();

        addKeyListener(new KeyAdapt(player));

        mainTimer = new Timer(10, this);
        mainTimer.start();

        startGame();

        if(Settings.musicOn) {
            Sound.playMusic();
        }
    }

    public static void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public static void removeEnemy(Enemy e) {
        enemies.remove(e);
    }

    public static ArrayList<Enemy> getEnemeyList() {
        return enemies;
    }

    public static void addMissile(Missile e) {
        missiles.add(e);
    }


    public static void removeMissile(Missile e) {
        missiles.remove(e);
    }

    public static ArrayList<Missile> getMissileList() {
        return missiles;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon ic = new ImageIcon("./src/resources/background.jpg");
        g2d.drawImage(ic.getImage(), 0,0, null);

        score.draw(g2d);
        player.draw(g2d);

        for(int i = 0; i < enemies.size(); i++) {
            Enemy tempEnemy = enemies.get(i);
            tempEnemy.draw(g2d);
        }

        for(int i = 0; i < missiles.size(); i++) {
            Missile m = missiles.get(i);
            m.draw(g2d);
        }
    }

    public void startGame() {
        enemyCount = level * 5;
        for(int i = 0; i < enemyCount; i++) {
            int min_width = 50;
            int max_width = 500;
            int min_hight = 0;
            int max_hight = 350;
            addEnemy(new Enemy(min_width + (int)(Math.random() * (max_width - min_width + 1)),  min_hight + (int)(Math.random() * (max_hight - min_hight + 1))));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        for(int i = 0; i < enemies.size(); i++) {
            Enemy tempEnemy = enemies.get(i);
            tempEnemy.update();
        }

        for(int i = 0; i < missiles.size(); i++) {
            Missile m = missiles.get(i);
            m.update();
        }

        checkEnd();
        repaint();
    }

    public void checkEnd() {
        if(enemies.isEmpty()) {
            level++;
            missiles.clear();
            JOptionPane.showMessageDialog(null,"Good work, you completed level : " + (level - 1));
            startGame();
        }
    }


    public static int getMaxScore() {
        File file = new File("./src/Settings/data.txt");

        int maxScore = Integer.MIN_VALUE;
        FileReader fileReader = null;

        try {
            BufferedReader bufferedReader;

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    int score = Integer.parseInt(parts[1]);

                    if (score > maxScore) {
                        maxScore = score;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return maxScore;
    }
}
