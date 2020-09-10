import java.awt.*;
import java.awt.event.KeyEvent;

public class Platform {
    int x;
    int y;
    int wight=100;
    int height=20;
    Thread thread;
    Platform(int x ,int y){
        this.x=x;
        this.y=y;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
                manager.addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            if (getX()<520-wight) {
                                redoX(10);
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            if(getX()>20){
                                redoX(-10);
                            }
                        }

                        return false;
                    }
                });

            }
        });
        thread.start();

    }


    private void redoX(int sdvig){
        x+= sdvig;
    }
    void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x,y,wight,height);
    }

    public int getX() {
        return x;
    }
    double  redoXSpeed(int x,double speedx, double speedy){
        double x1=x-this.x;
        double angle=(x1/wight*2-1)/1.01*90;
        double speed = Math.sqrt(speedx*speedx+speedy*speedy);
        return speed*Math.sin(Math.toRadians(angle));
    }

    double  redoYSpeed(int x,double speedx, double speedy){
        double x1=x-this.x;
        double angle=(x1/wight*2-1)/1.01*90;
        double speed = Math.sqrt(speedx*speedx+speedy*speedy);
        return -speed*Math.cos(Math.toRadians(angle));
    }
}
