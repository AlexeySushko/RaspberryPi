import controller.RCServer;

public class MainServer extends Thread {
    public static void main(String[] args) {
        initCar();
    }

    private static void initCar() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                    Thread.currentThread().setName("ThreadCar");
                    new RCServer().initServer();// deploy
//                    new ServerTest().initServer();// testing
//            }
//        });
//        thread.start();
    }
}
