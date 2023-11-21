package MainGame;

import java.awt.*;

public class Score {
    public static int score = 0;
    public static int highScore = 0;
    public static boolean gameOver;

    public Score() {}
    public void update() {
        score += 1;
    }

    public void draw(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.GREEN);
        g2d.drawString("Score: " + score, 10, 20);
        g2d.drawString("HighScore: " + highScore, 110, 20);
        g2d.drawString("Level: " + GameFrame.level, 260, 20);
        g2d.drawString("Controls: W,A,S,D,Space ", 350, 20);
        g2d.drawString("Game pause: ESC ", 350, 40);

        if(gameOver) {
            g2d.drawString("Game Over!", 250, 300);
        }
    }

}
