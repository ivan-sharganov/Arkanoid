import javax.swing.*;
import java.awt.*;

public class ArkanoidPanel extends JPanel {
    World world=new World();
    @Override
    protected void paintComponent(Graphics g) {
        world.draw(g);
        g.drawRect(50,20,world.worldWidth,world.worldHeight);

    }

}
