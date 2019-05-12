package constants;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Constants numbering pins
 */
public class Constants_GPIO_Pin {

    //Pin for direction of travel
    public static final Pin UP_PIN = RaspiPin.GPIO_00;
    public static final Pin DOWN_PIN = RaspiPin.GPIO_04;
    public static final Pin LEFT_PIN = RaspiPin.GPIO_02;
    public static final Pin RIGHT_PIN = RaspiPin.GPIO_03;

    public static final Pin ULTRASONIC_SENSOR_TRIGGER = RaspiPin.GPIO_27;
    public static final Pin ULTRASONIC_SENSOR_ECHO = RaspiPin.GPIO_28;

    public static final Pin SERVO_ULTRASONIC_SENSOR = RaspiPin.GPIO_01;

}
