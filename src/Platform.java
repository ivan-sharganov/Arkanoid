import java.awt.*;

public class Platform {
    int platformX=600;
    int platformY=620;
    int platformWidth=90;
    int platformHeight=30;
    void draw(Graphics g){
        g.drawRect(platformX, platformY, platformWidth,platformHeight);
    }
}
