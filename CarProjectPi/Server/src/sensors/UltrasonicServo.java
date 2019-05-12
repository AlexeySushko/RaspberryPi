package sensors;

import com.pi4j.wiringpi.SoftPwm;
import constants.Constants_GPIO_Pin;

import java.util.Arrays;

/**
 * Servo for ultrasonic sensor. For read all range distance.
 */
public class UltrasonicServo {
    public static int minPosition = 1;
    public static int maxPosition = 27;
    public static int frontPosition = 14;

    private static final UltrasonicSensor ULTRASONIC_SENSOR = new UltrasonicSensor();

    public UltrasonicServo() {
        init();
    }

    private void init() {
        com.pi4j.wiringpi.Gpio.wiringPiSetup();
        //(min=0 ; max=100)
        SoftPwm.softPwmCreate(Constants_GPIO_Pin.SERVO_ULTRASONIC_SENSOR.getAddress(), 0, 100);
    }

    /**
     * Method get distance from sensor.
     *
     * @return
     */
    public int getDistance() {
        return ULTRASONIC_SENSOR.checkDistance();
    }

    public int[] getAllRangeDistance() {
        System.out.println("Read distance for All range");

        int[] allRange = new int[maxPosition];

        for (int i = minPosition; i <= maxPosition; i++) {
            SoftPwm.softPwmWrite(Constants_GPIO_Pin.SERVO_ULTRASONIC_SENSOR.getAddress(), i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                allRange[i - 1] = 0;
                e.printStackTrace();
            }

            allRange[i - 1] = getDistance();
        }
        System.out.println(Arrays.toString(allRange));

        return allRange;
    }

    /**
     * Get distance for current servo position.
     * @param position current position.
     * @return current distance.
     */
    public int getDistance(int position) {
        System.out.println("Read distance for " + position + " position.");
        SoftPwm.softPwmWrite(Constants_GPIO_Pin.SERVO_ULTRASONIC_SENSOR.getAddress(), position);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }

        return getDistance();
    }

    public void stopPVM() {
        SoftPwm.softPwmStop(Constants_GPIO_Pin.SERVO_ULTRASONIC_SENSOR.getAddress());
        System.out.println("SoftPwm stopped!");
    }
}
