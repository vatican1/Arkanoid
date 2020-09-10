import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ArkanoidPanel arkanoidPanel = new ArkanoidPanel();
        frame.add(arkanoidPanel);
        frame.setSize(arkanoidPanel.world.width+55,arkanoidPanel.world.height+58);
        frame.setLocation(200,20);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long start, end;
                start = System.currentTimeMillis();
                while (true) {
                    arkanoidPanel.world.redo();
                    while (true) {
                        if (arkanoidPanel.world.checkBallPlatformCollisionBool()) {
                            break;
                        }
                        frame.repaint();
                        end = System.currentTimeMillis();
                        arkanoidPanel.world.update(end - start);
                        start = System.currentTimeMillis();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();

    }
}
