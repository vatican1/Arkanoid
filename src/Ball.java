import java.awt.*;

public class Ball {
    double xCentreBefor=0,yCentreBefor=0;
    double xCentre,yCentre;
    double speedx,speedy;

    Ball(    double xCentre, double yCentre,    double speedx, double speedy){
        this.yCentre=yCentre;
        this.xCentre=xCentre;
        this.speedx=speedx;
        this.speedy=speedy;
    }

    void draw(Graphics g){
        g.setColor(Color.cyan);
        g.fillOval((int)(xCentre-5),(int)(yCentre-5),10,10);

    }

    void update(long dt){
        xCentreBefor=xCentre;
        yCentreBefor=yCentre;
        xCentre+=speedx*dt;
        yCentre+=speedy*dt;
    }



    public double getxCentre() {
        return xCentre;
    }

    public double getyCentre() {
        return yCentre;
    }
    public void redoXSpeed(){
        speedx=-speedx;
    }
    public void redoYSpeed(){
        speedy=-speedy;
    }

}
