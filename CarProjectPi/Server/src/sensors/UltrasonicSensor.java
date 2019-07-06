package sensors;

import com.pi4j.io.gpio.*;
import constants.Constants_GPIO_Pin;
import controller.Controller;

/**
 * Class for Ultrasonic Sensor for read distance.
 */
public class UltrasonicSensor {
    private static GpioPinDigitalOutput sensorTriggerPin;
    private static GpioPinDigitalInput sensorEchoPin;


    public UltrasonicSensor() {
        init();
    }

    /**
     * Initialize pins for Trigger and Echo.
     */
    public void init() {
        sensorTriggerPin = Controller.GPIO.provisionDigitalOutputPin(Constants_GPIO_Pin.ULTRASONIC_SENSOR_TRIGGER);
        sensorEchoPin = Controller.GPIO.provisionDigitalInputPin(Constants_GPIO_Pin.ULTRASONIC_SENSOR_ECHO, PinPullResistance.PULL_DOWN);
    }

    /**
     * Method read distance cron sensor.
     *
     * @return Distance in centimeters.
     */
    public int checkDistance() {
        int distance = -1;
        try {
            sensorTriggerPin.high();
            Thread.sleep((long) 0.01);
            sensorTriggerPin.low();

            while (sensorEchoPin.isLow()) {
            }

            long startTime = System.nanoTime();
            while (sensorEchoPin.isHigh()) {
            }

            long endTime = System.nanoTime();

            distance = (int) ((((endTime - startTime) / 1e3) / 2) / 29.1);
            System.out.println("Distance : " + distance + " cm.");
            Thread.sleep(20);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return distance;
    }
}