import java.awt.*;

public class Brick {
    int brickX=50;
    int brickY=50;
    int brickWidth=115;
    int brickHeight=60;
    int power;

    Color color;

    public Brick(int x, int y, int power,Color color ){
        brickX=x;
        brickY=y;
        this.power=power;
        this.color=color;
    }
    void draw(Graphics g){
        g.setColor(color);
        g.fillRect(brickX,brickY,brickWidth, brickHeight);

    }
}
