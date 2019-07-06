package entity;

import manipulation.Constants;
import manipulation.RCClient;
import operations.Go;
import operations.Sensors;
import operations.Stop;
import ui.RadarWindowFX;

import java.io.IOException;

public class Car implements Go, Stop, Sensors {
    public static RCClient rcClient = new RCClient();// deploy
//    public static RCClientTest rcClient = new RCClientTest(); // test


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

    @Override
    public String scan180() {
        rcClient.sendMessage(Constants.SCAN_180);
        try {
            if (rcClient.input.readLine() != null) {
                String res = rcClient.input.readLine();

                for (String s : res.split("$")) {
                    System.out.println("На клиенте: " + s);
                }
                System.out.println(rcClient.input.readLine());

                RadarWindowFX radarWindowFX = new RadarWindowFX();
                radarWindowFX.resArrStr = res.split("\\$");
                radarWindowFX.showFigure();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
