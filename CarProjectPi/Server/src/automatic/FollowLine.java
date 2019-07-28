package automatic;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import constants.Constants_GPIO_Pin;
import controller.Controller;

import static controller.Controller.GPIO;

public class FollowLine {
    private static FollowLine followLine = null;
    static GpioPinDigitalInput leftBorder, rightBorder;
    Controller controller;

    private FollowLine(Controller controller) {
        this.controller = controller;
        System.out.println("INIT Constr");
        init();

    }

    private void init() {
        leftBorder = GPIO.provisionDigitalInputPin(Constants_GPIO_Pin.LINE_SENSOR_LEFT_OUT, "LEFT", PinPullResistance.PULL_UP);
        rightBorder = GPIO.provisionDigitalInputPin(Constants_GPIO_Pin.LINE_SENSOR_RIGHT_OUT, "LEFT", PinPullResistance.PULL_UP);

        setShutdown(leftBorder);
        setShutdown(rightBorder);
    }

    public static FollowLine getInstance(Controller controller) {

        if (followLine == null) {
            followLine = new FollowLine(controller);
            return followLine;
        }

        return followLine;
    }


    //setShutdown INPUT
    private void setShutdown(GpioPinDigitalInput pin) {
        pin.setShutdownOptions(
                true, // free pin
                PinState.LOW, // set state 0
                PinPullResistance.OFF, // disconnect pull-up resistor
                PinMode.DIGITAL_INPUT);// set the input mode
    }

    public void addLineListener() {

        try {

            leftBorder.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                    if (event.getState() == PinState.LOW) {
                        System.out.println(" --> Off line: " + event.getPin() + " = " + event.getState());
                        controller.goLeft();
                    } else {
                        System.out.println(" --> On line: " + event.getPin() + " = " + event.getState());
                        controller.goUp();
                    }
                }
            });


            rightBorder.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                    if (event.getState() == PinState.LOW) {
                        System.out.println(" --> Off line: " + event.getPin() + " = " + event.getState());
                        controller.goRight();
                    } else {
                        System.out.println(" --> On line: " + event.getPin() + " = " + event.getState());
                        controller.goUp();
                    }
                }
            });


        } catch (Exception e) {
            System.out.println("GPIO.shutdown() <<<<<");
            GPIO.shutdown();
            e.printStackTrace();
        }
    }

    public void runFollow() {
        controller.goUp();
        addLineListener();
    }

    public void stopFollow() {
        leftBorder.removeAllListeners();
        rightBorder.removeAllListeners();
        controller.resetAllWay();
    }
}
