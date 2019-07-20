import java.awt.*;
import java.util.ArrayList;

public class World {
    int worldHeight=650;
    int worldWidth=1250;
    Ball ball = new Ball();
    ArrayList<Brick> bricks = new ArrayList<Brick>();
    Platform platform = new Platform();


    void draw(Graphics g) {
        ball.draw(g);
        for (int i = 0; i < bricks.size(); ++i) {
            bricks.get(i).draw(g);
        }
        platform.draw(g);
    }

    {
    }

    void checkBallPlatformCollision() {
        if (ball.ballY + ball.ballSize >= platform.platformY & ball.ballY + ball.ballSize <= platform.platformY + 3 & ball.ballX + ball.ballSize >= platform.platformX & ball.ballX <= platform.platformX + platform.platformWidth) {
            ball.speedY *= -1;               //
            if (ball.ballX + ball.ballSize == platform.platformX + platform.platformWidth / 2) {
                ball.speedX *= -1;
            } else if( platform.platformX + platform.platformWidth / 2 - ball.ballX - 3<=0) {
                ball.speedX=ball.speedX/Math.abs(ball.speedX);
                ball.speedX *= (ball.speedX/Math.abs(ball.speedX))*(1- (platform.platformX + platform.platformWidth / 2 - ball.ballX - 3)/5) ;
                System.out.println("right");
            }
            if (!(ball.ballX + 3 == platform.platformX + platform.platformWidth / 2) & platform.platformX + platform.platformWidth / 2 - ball.ballX - 3>0) {
                ball.speedX =  ball.speedX / Math.abs(ball.speedX);
                ball.speedX *= -(ball.speedX / Math.abs(ball.speedX)) * (1 + (platform.platformX + platform.platformWidth / 2 - ball.ballX - 3) / 5);
                System.out.println("left");
            }
        }
    }

    boolean checkFallDown() {
        if (ball.ballY+  ball.ballSize >= worldHeight+20) {
            ball.ballX = platform.platformX + 20;
            ball.ballY = platform.platformY - 5;
            ball.speedY = Math.abs(ball.speedY);
            return true;
        }
        return false;
    }

    boolean isBallInsideBrick(Ball ball, Brick brick) {
        if (ball.ballX <= brick.brickX + brick.brickWidth & ball.ballX + ball.ballSize >= brick.brickX & ball.ballY <= brick.brickY + brick.brickHeight & ball.ballY + ball.ballSize >= brick.brickY) {
            //System.out.println("inside");
            return true;
        }
        return  false;
    }

    void checkBallBricksCollisions() {
        for (int i = 0; i < bricks.size(); ++i) {
            if (isBallInsideBrick(ball, bricks.get(i))) {
                updateBallSpeed(ball, bricks.get(i));
                bricks.get(i).power-=1;
                if (bricks.get(i).power==0) {
                    bricks.remove(i);
                }
            }
        }
    }

    void checkBallOutOfScreenCollision() {
        if (ball.ballX > worldWidth - ball.ballSize) {
            ball.speedX *= -1;
        }
        if (ball.ballX < 50) { //отступ от края
            ball.speedX *= -1;
        }
        if (ball.ballY > worldHeight - ball.ballSize+20) {
            ball.speedY *= -1;
        }
        if (ball.ballY < 20) {    //отступ от края
            ball.speedY *= -1;
        }
    }

    void update() {
        checkBallBricksCollisions();
        checkBallPlatformCollision();
        checkBallOutOfScreenCollision();
        checkFallDown();
        ball.update(); // обновляем положение шарика - т.е. двигает его на вектор v*dt, где v - вектор скорости
    }

    void updateBallSpeed(Ball ball, Brick brick) {
        for (int i = 0; i < 1; i++) {
            if (ball.ballX + ball.ballSize >= brick.brickX & ball.ballX + ball.ballSize < brick.brickX + 3 & ball.ballY <= brick.brickY + brick.brickHeight & ball.ballY + ball.ballSize >= brick.brickY || ball.ballX <= brick.brickX + brick.brickWidth & ball.ballX > brick.brickX + brick.brickWidth - 3 & ball.ballY <= brick.brickY + brick.brickHeight & ball.ballY + 5 >= brick.brickY) { // Если мяч ударился о нижнюю сторону прямоугольника

                ball.speedX *= -1;
                //System.out.println("update");
                break;
            }

            if (ball.ballY <= brick.brickY + brick.brickHeight & ball.ballY >= brick.brickY + brick.brickHeight - 4 & ball.ballX + ball.ballSize >= brick.brickX & ball.ballX <= brick.brickX + brick.brickWidth || ball.ballY + ball.ballSize >= brick.brickY & ball.ballY + ball.ballSize <= brick.brickY + 4 & ball.ballX + ball.ballSize >= brick.brickX & ball.ballX <= brick.brickX + brick.brickWidth) {

                ball.speedY *= -1;
                //System.out.println("update");

            }
        }
    }
}
