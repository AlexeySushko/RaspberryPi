import com.pi4j.util.Console;
import controller.RCServer;

public class MainServer extends Thread {
    public static void main(String[] args) throws InterruptedException {
        initCar();
    }

    private static void initCar() throws InterruptedException {
        Console console = new Console();
        console.promptForExit();//CTRL-C


        new RCServer().initServer();
        console.waitForExit();
    }
}
