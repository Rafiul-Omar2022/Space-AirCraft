package MainGame;

import javax.swing.*;
import java.awt.*;

public class Missile extends Entity {

    public Missile(int x, int y) {
        super(x, y);
    }

    public void update() {
        y -= 3;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(getMissileImg(), x + 5, y, null);
//        g2d.draw(getBounds());
    }

    public Image getMissileImg() {
        ImageIcon ic = new ImageIcon("./src/resources/missile.png");
        return ic.getImage();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getMissileImg().getWidth(null), getMissileImg().getHeight(null));
    }
}
