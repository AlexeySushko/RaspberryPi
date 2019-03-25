import com.pi4j.io.gpio.*;

/**
 * Class for added and setting pin.
 */
class MyPin {
    private GpioController gpio;
    private GpioPinDigitalOutput pinUp, pinDown, pinLeft, pinRight;

    public MyPin() {
        initLed();
    }

    /**
     * Init and set pin numbers.
     */
    protected void initLed() {

        gpio = GpioFactory.getInstance();

        pinUp = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Up", PinState.LOW);
        pinDown = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Down", PinState.LOW);
        pinLeft = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Left", PinState.LOW);
        pinRight = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Right", PinState.LOW);

        setShutdown(pinUp);
        setShutdown(pinDown);
        setShutdown(pinLeft);
        setShutdown(pinRight);
    }

    /**
     * Method for setup shutdown settings for pin.
     *
     * @param led current PIN
     */
    private void setShutdown(GpioPinDigitalOutput led) {
        led.setShutdownOptions(
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

    void goUp() {
        setHeightPin(pinUp);
    }

    void goDown() {
        setHeightPin(pinDown);
    }

    public void goLeft() {
        setHeightPin(pinLeft);
    }

    public void rightLed() {
        setHeightPin(pinRight);
    }



    public void stop() {
//        if (gpio != null) {
        System.out.println("SHUTDOWN");
//            gpio.shutdown();
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