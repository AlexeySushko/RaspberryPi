package sensors.servo;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.RaspiPin;

public class PWMExample {

    public static void main(String[] args) throws Exception {

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #01 as a PWM output pin and turn on
        GpioPinPwmOutput out = gpio.provisionPwmOutputPin(RaspiPin.GPIO_01);
        for(int i=0; i < 1024; i+=10)
        {
            System.out.println("Setting pwm to: " + i);
            out.setPwm(i);
            Thread.sleep(100);
        }
        out.setPwm(0);
        Thread.sleep(1000);
        gpio.shutdown();
    }
}