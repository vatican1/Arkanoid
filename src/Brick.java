import java.awt.*;

public class Brick {
    int x1,y1;
    int wight=35,height=20;
    int health=2;
    Color color;

    Brick(int x1, int y1){
        this.x1=x1*35;
        this.y1=y1*20;
    }
    void draw(Graphics g){
        switch (health){
            case 2:{g.setColor(Color.blue);
            break;
            }
            case 1:{g.setColor(Color.yellow);
            break;}

        }
        g.fillRect(x1+1,y1+1,wight-2,height-2);
    }

}
