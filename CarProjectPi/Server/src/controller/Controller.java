package controller;

import com.pi4j.io.gpio.*;
import constants.Constants_GPIO_Pin;
import sensors.servo.UltrasonicServo;

/**
 * Class for added, setting and control pin.
 */
public class Controller {
    public static final GpioController GPIO = GpioFactory.getInstance();

    private GpioPinDigitalOutput A_1A, A_1B, B_1A, B_1B;

    public Controller() {
        initPin();
    }

    /**
     * Init and set pin numbers.
     */
    protected void initPin() {
        A_1A = GPIO.provisionDigitalOutputPin(Constants_GPIO_Pin.A_1A, "Up", PinState.LOW);
        A_1B = GPIO.provisionDigitalOutputPin(Constants_GPIO_Pin.A_1B, "Down", PinState.LOW);
        B_1A = GPIO.provisionDigitalOutputPin(Constants_GPIO_Pin.B_1A, "Left", PinState.LOW);
        B_1B = GPIO.provisionDigitalOutputPin(Constants_GPIO_Pin.B_1B, "Right", PinState.LOW);

        setShutdown(A_1A);
        setShutdown(A_1B);
        setShutdown(B_1A);
        setShutdown(B_1B);
    }

    /**
     * Method for setup shutdown settings for pin.
     *
     * @param pin current PIN.
     */
    private void setShutdown(GpioPinDigitalOutput pin) {
        pin.setShutdownOptions(
                true, // free pin
                PinState.LOW, // set state 0
                PinPullResistance.OFF, // disconnect pull-up resistor
                PinMode.DIGITAL_INPUT);// set the input mode
    }

    /**
     * Set state HEIGHT.
     *
     * @param pin for change state.
     */
    private void setHeightPin(GpioPinDigitalOutput pin) {
        if (pin.isLow()) {
            pin.high();
            System.out.println("Set height pin #" + pin.getPin().getAddress());
        }
    }

    /**
     * Set state LOW.
     *
     * @param pin for change state.
     */
    private void setLowPin(GpioPinDigitalOutput pin) {
        if (pin.isHigh()) {
            pin.low();
            System.out.println("Set low pin #" + pin.getPin().getAddress());
        }
    }

    public void goUp() {
        resetAllWay();
        setHeightPin(A_1A);
        setHeightPin(B_1B);
    }

    public void goDown() {
        resetAllWay();
        setHeightPin(A_1B);
        setHeightPin(B_1A);
    }

    public void goLeft() {
        resetAllWay();
        setHeightPin(B_1B);
        setHeightPin(A_1B);
    }

    public void goRight() {
        resetAllWay();
        setHeightPin(B_1A);
        setHeightPin(A_1A);
    }


    public void stop() {
        if (GPIO != null) {
            System.out.println("SHUTDOWN");
            GPIO.shutdown();
        }
    }

    public void stopUp() {
        setLowPin(A_1A);
        setLowPin(B_1B);
    }

    public void stopDown() {
        setLowPin(A_1B);
        setLowPin(B_1A);
    }

    public void stopLeft() {
        setLowPin(B_1B);
        setLowPin(A_1B);
    }


    public void stopRight() {

        setLowPin(B_1A);
        setLowPin(A_1A);
    }

    public String scan180() {
        UltrasonicServo servo = RCServer.ultrasonicServo;
        int[] rangeRes = servo.getAllRangeDistance();
        StringBuilder sbResult = new StringBuilder();
        for (int i : rangeRes) {
            sbResult.append(i).append("$");
        }
        sbResult.append("\n");
        servo.stopPVM();
        return sbResult.toString();
    }

    private void resetAllWay(){
        stopUp();
        stopDown();
        stopLeft();
        stopRight();
    }
}