import java.awt.*;
import java.util.ArrayList;

public class World {
    ArrayList<Brick> bricks ;
    Platform platform ;
    Ball ball ;
    int width=600;
    int height=600;



    void redo(){
        bricks = new ArrayList<Brick>();
        platform = new Platform(width/2, height-20);
        for (int i = 2; i<17;i++) {
            bricks.add(new Brick(i,3));
            bricks.add(new Brick(i,6));
            bricks.add(new Brick(i,10));
            bricks.add(new Brick(i,11));
            bricks.add(new Brick(i,12));
        }

        ball = new Ball(platform.x+50,platform.y-10,0,0);

    }


    void update(long dt) {
        ball.update(dt); // обновляем положение шарика - т.е. двигает его на вектор v*dt, где v - вектор скорости
        checkBallBricksCollisions();
        checkBallPlatformCollision();
        checkBallWallsCollisions();
        checkBallOutOfScreenCollision();
    }

    void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0,0,width+20,20);
        g.fillRect(0,0,20,height);
        g.fillRect(20+width,0,20,height);
        g.fillRect(0,height,width+40,20);

        ball.draw(g);
        for (int i = 0; i < bricks.size(); ++i) {
            bricks.get(i).draw(g);
        }
        platform.draw(g);
    }

    void checkBallBricksCollisions(){
        for (int i = 0 ; i<bricks.size();i++){
            if ((ball.xCentre+5>bricks.get(i).x1)&&(ball.xCentre-5<bricks.get(i).x1+bricks.get(i).wight)&&
                    (ball.yCentre+5>bricks.get(i).y1)&&(ball.yCentre-5<bricks.get(i).y1+bricks.get(i).height)){
                bricks.get(i).health-=1;
                if ((ball.yCentreBefor+5<bricks.get(i).y1)||(ball.yCentreBefor-5>bricks.get(i).y1+bricks.get(i).height)) {
                    ball.yCentre=ball.yCentreBefor;
                    ball.redoYSpeed();
                    if( bricks.get(i).health==0){
                        bricks.remove(i);
                    }
                    break;
                }
                if ((ball.xCentreBefor+5<bricks.get(i).x1)||(ball.xCentreBefor-5>bricks.get(i).x1+bricks.get(i).wight)) {
                    ball.xCentre=ball.xCentreBefor;
                    ball.redoXSpeed();
                    if( bricks.get(i).health==0){
                        bricks.remove(i);
                    }
                    break;
                }
                ball.yCentre=ball.yCentreBefor;
                ball.xCentre=ball.xCentreBefor;
                ball.redoXSpeed();
                if( bricks.get(i).health==0){
                    bricks.remove(i);
                }

            }
        }

    }
    void checkBallPlatformCollision() {
        if (ball.getyCentre() + 5 > platform.y) {
            if ((ball.getxCentre() > platform.x) && (ball.getxCentre() < platform.x + platform.wight)) {
                ball.yCentre = ball.yCentreBefor;
                double speedx=platform.redoXSpeed((int) (ball.xCentreBefor),ball.speedx,ball.speedy);
                double speedy=platform.redoYSpeed((int) (ball.xCentreBefor),ball.speedx,ball.speedy);
                ball.speedx=speedx;
                ball.speedy= speedy;

            }
        }
    }
    boolean checkBallPlatformCollisionBool() {
        if (ball.getyCentre() + 5 > platform.y) {
            if ((ball.getxCentre() > platform.x) && (ball.getxCentre() < platform.x + platform.wight)) {
                ball.yCentre = ball.yCentreBefor;
                ball.redoYSpeed();
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    void checkBallWallsCollisions(){
        if (ball.getxCentre()-5<20){
            ball.xCentre=ball.xCentreBefor;
            ball.redoXSpeed();
        }
        if(ball.getxCentre()+5>width+20){
            ball.xCentre=ball.xCentreBefor;
            ball.redoXSpeed();
        }
        if (ball.getyCentre()<5+20){
            ball.yCentre=ball.yCentreBefor;

            ball.redoYSpeed();
        }
        if (ball.getyCentre()+5>height){
            ball.yCentre=ball.yCentreBefor;
            ball.redoYSpeed();
        }
    }
    void checkBallOutOfScreenCollision(){

    }
}
