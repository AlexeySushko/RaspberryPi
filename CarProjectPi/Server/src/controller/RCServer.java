package controller;

import constants.Constants;
import sensors.UltrasonicServo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RCServer {
    private static final int portNumber = 4141;
    private Controller controller = new Controller();

    private UltrasonicServo ultrasonicServo = new UltrasonicServo();

    public void initServer() {

        RCServer rcServer = new RCServer();

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {

                System.out.println("Listening on portNumber " + portNumber + ", CRTL-C to quit or Click STOP ");
                Socket socket = serverSocket.accept();

                try (PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    System.out.println("Connection accepted.");
                    String in = "";

                    while ((in = input.readLine()) != null) {

                        System.out.println("Received: " + in);

                        if (in.equals("SYN")) { // Synchronize
                            System.out.println("START");
                            rcServer.write(output, "SYN-ACK"); // Synchronize-Acknowledge
                        }
                        if (in.equals(Constants.STOP)) {
                            rcServer.write(output, "FIN-ACK"); // Final-Acknowledge
                            controller.stop();
                            break;
                        }

                        if (in.equals(Constants.GO_UP)) {
                            rcServer.write(output, "< UP >");

                            //check distance
                            if (ultrasonicServo.getDistance(UltrasonicServo.frontPosition) > Constants.MIN_DISTANCE_BEFORE_CAR) {
                                controller.goUp();
                            }
                        }

                        if (in.equals(Constants.GO_DOWN)) {
                            rcServer.write(output, "< DOWN >");
                            controller.goDown();
                        }
                        if (in.equals(Constants.GO_LEFT)) {
                            controller.goLeft();
                            rcServer.write(output, "< LEFT >");
                        }
                        if (in.equals(Constants.GO_RIGHT)) {
                            controller.rightLed();
                            rcServer.write(output, "< RIGHT >");
                        }


                        if (in.equals(Constants.STOP_UP)) {
                            rcServer.write(output, "< STOP_UP >");
                            controller.stopUp();

                        }
                        if (in.equals(Constants.STOP_DOWN)) {
                            rcServer.write(output, "< STOP_DOWN >");
                            controller.stopDown();

                        }
                        if (in.equals(Constants.STOP_LEFT)) {
                            rcServer.write(output, "< STOP_LEFT >");
                            controller.stopLeft();

                        }
                        if (in.equals(Constants.STOP_RIGHT)) {
                            rcServer.write(output, "< STOP_RIGHT >");
                            controller.stopRight();

                        }
                        if (in.contains(Constants.SEND_MESSAGE)) {
                            rcServer.write(output, "< " + in + ">");
                        }
                    }
                    socket.close();
                    ultrasonicServo.stopPVM();
                    System.out.print("Closing socket.\n\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (controller != null) {
                controller.stop();
            }
        }
    }

    void write(PrintWriter output, String message) {
        System.out.println("Sending: " + message);
        output.println(message);
    }

    String turn(String input) {
        try {
            System.out.println("position " + input);
            return ("response=OK");
        } catch (NumberFormatException e) {
            return ("response=FAILED");
        }
    }
}