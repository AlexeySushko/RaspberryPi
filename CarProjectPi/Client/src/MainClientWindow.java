import allClasses.MainClient;

import javax.swing.*;

public class MainClientWindow extends JFrame {

    public MainClientWindow() {

        setTitle("Raspberry Pi B3+");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(702, 578);
        setResizable(false);
        setLocation(700, 200);
        add(new MainClient());
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainClientWindow();
    }
}
