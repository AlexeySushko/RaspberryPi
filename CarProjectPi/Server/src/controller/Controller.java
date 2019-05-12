package controller;

import com.pi4j.io.gpio.*;
import constants.Constants_GPIO_Pin;

/**
 * Class for added, setting and control pin.
 */
public class Controller {
    public static final GpioController GPIO = GpioFactory.getInstance();;
    private GpioPinDigitalOutput pinUp, pinDown, pinLeft, pinRight;

    public Controller() {
        initPin();
    }

    /**
     * Init and set pin numbers.
     */
    protected void initPin() {

        pinUp = GPIO.provisionDigitalOutputPin(Constants_GPIO_Pin.UP_PIN, "Up", PinState.LOW);
        pinDown = GPIO.provisionDigitalOutputPin(Constants_GPIO_Pin.DOWN_PIN, "Down", PinState.LOW);
        pinLeft = GPIO.provisionDigitalOutputPin(Constants_GPIO_Pin.LEFT_PIN, "Left", PinState.LOW);
        pinRight = GPIO.provisionDigitalOutputPin(Constants_GPIO_Pin.RIGHT_PIN, "Right", PinState.LOW);

        setShutdown(pinUp);
        setShutdown(pinDown);
        setShutdown(pinLeft);
        setShutdown(pinRight);
    }

    /**
     * Method for setup shutdown settings for pin.
     *
     * @param pin current PIN
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
//        if (pin.isLow()) {
//            pin.high();
//            System.out.println("Set height pin #" + pin.getPin().getAddress());
//        }
    }

    /**
     * Set state LOW.
     *
     * @param pin for change state.
     */
    private void setLowPin(GpioPinDigitalOutput pin) {
//        if(pin.isHigh()) {
//            pin.low();
//            System.out.println("Set low pin #" + pin.getPin().getAddress());
//        }
    }

    public void goUp() {
        setHeightPin(pinUp);
    }

    public void goDown() {
        setHeightPin(pinDown);
    }

    public void goLeft() {
        setHeightPin(pinLeft);
    }

    public void rightLed() {
        setHeightPin(pinRight);
    }


    public void stop() {
//        if (GPIO != null) {
        System.out.println("SHUTDOWN");
//            GPIO.shutdown();
//        }
    }

    public void stopUp() {
        setLowPin(pinUp);
    }

    public void stopDown() {
        setLowPin(pinDown);
    }

    public void stopLeft() {
        setLowPin(pinLeft);
    }


    public void stopRight() {
        setLowPin(pinRight);
    }
}