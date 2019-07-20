import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int life=5;
        JLabel label=new JLabel("5");
        JFrame frame = new JFrame("Window");
        ArkanoidPanel panel = new ArkanoidPanel();
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if (50+20<=e.getX() & panel.world.worldWidth-20>=e.getX()) {
                    panel.world.platform.platformX = -20 + e.getX();
                }
            }
        });

        frame.setSize(640,480);
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        panel.add(label);
        frame.add(panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED & e.getKeyCode() == KeyEvent.VK_LEFT & panel.world.platform.platformX >=60 ) {
                    //System.out.println("left pressed");
                    panel.world.platform.platformX -= 40;
                }
                if (e.getID() == KeyEvent.KEY_PRESSED & e.getKeyCode() == KeyEvent.VK_RIGHT &  panel.world.platform.platformX +40<=panel.world.worldWidth+20) {
                    //System.out.println("right pressed");
                    panel.world.platform.platformX += 40;
                }
                return false;
            }
        });

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 7; ++j) {
                panel.world.bricks.add(new Brick(50 + 5 + i * (125), 20 +5 + j *(70) ,1, new Color((int) (Math.random()*255),(int) (Math.random()*255),(int) (Math.random()*255))));
            }
        }
//        panel.world.bricks.add(new Brick(180, 100));
        while (true) {
            frame.repaint();
            Thread.sleep(30);
            panel.world.update();
            if (panel.world.checkFallDown()) {
                life-=1;
                label.setText(String.valueOf(life));
            }
            if (life==0){
                panel.world.bricks.clear();
                //System.out.println(panel.world.bricks.size());
                for (int i = 0; i < 10; ++i) {
                    for (int j = 0; j < 7; ++j) {
                        panel.world.bricks.add(new Brick(50 + 5 + i * (125), 20 +5 + j *(70) ,1, new Color((int) (Math.random()*255),(int) (Math.random()*255),(int) (Math.random()*255))));
                    }
                }
                life=5;
                label.setText(String.valueOf(life));

            }
            //System.out.println(panel.world.ball.ballX);

        }

    }
}