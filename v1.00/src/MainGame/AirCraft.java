package MainGame;

import javax.swing.*;

public class AirCraft extends JFrame {
    public AirCraft() {
        setTitle("Space AirCraft");
        setSize(600,600);
        setIconImage(new ImageIcon("./src/resources/spaceship.png").getImage());
        setResizable(false);
        setLocationRelativeTo(null);
        add(new GameFrame());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
