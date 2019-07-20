import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Ball {
    int ballX=600;
    int ballY=600;
    int speedX=2; //2
    int speedY=3;  //3
    int ballSize =30;
    void update(){
        ballX+=speedX;
        ballY-=speedY;
    }
    void draw(Graphics g){

        g.fillRect(ballX, ballY, ballSize,ballSize);
    }

}


