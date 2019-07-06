package manipulation;

import entity.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ActionClient extends JPanel {

    Car car = new Car();

    private Image background;

    public ActionClient() {
        addKeyListener(new KeyListener());
        initImage();
        setFocusable(true);
    }

    private void initImage() {
        background = new ImageIcon(getClass().getClassLoader().getResource(Constants.BACKGROUND_PNG)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }

    /**
     * Class to handle events
     */
    class KeyListener extends KeyAdapter {
        private boolean scaning = false;

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_W:
                    car.goUp();
                    break;
                case KeyEvent.VK_S:
                    car.goDown();
                    break;
                case KeyEvent.VK_D:
                    car.goRight();
                    break;
                case KeyEvent.VK_A:
                    car.goLeft();
                    break;
                case KeyEvent.VK_O:
                    if (scaning == false){
                        scaning = true;
                        car.scan180();
                    }
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);

            super.keyPressed(e);
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_W:
                    car.stopUp();
                    break;
                case KeyEvent.VK_S:
                    car.stopDown();
                    break;
                case KeyEvent.VK_D:
                    car.stopRight();
                    break;
                case KeyEvent.VK_A:
                    car.stopLeft();
                    break;
                case KeyEvent.VK_O:
                    scaning = false;
                    break;
            }
        }
    }
}
