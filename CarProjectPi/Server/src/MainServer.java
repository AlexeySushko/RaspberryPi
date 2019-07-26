import controller.RCServer;

public class MainServer extends Thread {
    public static void main(String[] args) {
        initCar();
    }

    private static void initCar() {
        new RCServer().initServer();
    }
}
