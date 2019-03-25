package entity;

import allClasses.Constants;
import operations.Go;
import allClasses.RCClient;
import operations.Stop;

public class Car implements Go, Stop {
    public static RCClient rcClient = new RCClient();


    @Override
    public void goUp() {
        rcClient.sendMessage(Constants.GO_UP);
    }

    @Override
    public void goDown() {
        rcClient.sendMessage(Constants.GO_DOWN);
    }

    @Override
    public void goLeft() {
        rcClient.sendMessage(Constants.GO_LEFT);
    }

    @Override
    public void goRight() {
        rcClient.sendMessage(Constants.GO_RIGHT);
    }

    @Override
    public void stopUp() {
        rcClient.sendMessage(Constants.STOP_UP);
    }

    @Override
    public void stopDown() {
        rcClient.sendMessage(Constants.STOP_DOWN);
    }

    @Override
    public void stopLeft() {
        rcClient.sendMessage(Constants.STOP_LEFT);
    }

    @Override
    public void stopRight() {
        rcClient.sendMessage(Constants.STOP_RIGHT);
    }
}
