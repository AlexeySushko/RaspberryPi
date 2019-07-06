package constants;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Constants numbering pins
 */
public class Constants_GPIO_Pin {

    /**
     * Pins names for engine management in accordance with engine driver L9110S.
     */
    public static final Pin A_1A = RaspiPin.GPIO_00;
    public static final Pin A_1B = RaspiPin.GPIO_04;
    public static final Pin B_1A = RaspiPin.GPIO_02;
    public static final Pin B_1B = RaspiPin.GPIO_03;

    public static final Pin ULTRASONIC_SENSOR_TRIGGER = RaspiPin.GPIO_27;
    public static final Pin ULTRASONIC_SENSOR_ECHO = RaspiPin.GPIO_28;

    public static final Pin SERVO_ULTRASONIC_SENSOR = RaspiPin.GPIO_01;
}
