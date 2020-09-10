import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ArkanoidPanel extends JPanel {
    Thread thread;
    World world= new World();
    ArkanoidPanel(){
        world.redo();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
                manager.addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.getID() == KeyEvent.KEY_RELEASED) {
                            if ((e.getKeyCode() == KeyEvent.VK_ENTER) && (world.ball.speedy == 0)) {
                                world.ball.speedy = 0.8;
                            }
                        }
                        return false;
                    }
                });

            }
        });
        thread.start();
    }


    @Override
    public void paintComponent(Graphics g){
        world.draw(g);
    }
}
