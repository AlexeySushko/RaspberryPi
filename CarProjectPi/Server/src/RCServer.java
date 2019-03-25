import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RCServer {
    private static final int portNumber = 4141;
    private MyPin myPin;

    public void initServer() throws InterruptedException {

        RCServer rcServer = new RCServer();

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {

                System.out.println("Listening on portNumber " + portNumber + ", CRTL-C to quit or Click STOP ");
                Socket socket = serverSocket.accept();

                try (PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    System.out.println("Connection accepted.");
                    String in = "";

                    myPin = new MyPin();

                    while ((in = input.readLine()) != null) {

                        System.out.println("Received: " + in);

                        if (in.equals("SYN")) { // Synchronize
                            System.out.println("START");
                            rcServer.write(output, "SYN-ACK"); // Synchronize-Acknowledge
                        }
                        if (in.equals(Constants.STOP)) {
                            rcServer.write(output, "FIN-ACK"); // Final-Acknowledge
                            myPin.stop();
                            break;
                        }
                        if (in.equals(Constants.GO_UP)) {
                            rcServer.write(output, "< UP >");
                            myPin.goUp();

                        }
                        if (in.equals(Constants.GO_DOWN)) {
                            rcServer.write(output, "< DOWN >");
                            myPin.goDown();
                        }
                        if (in.equals(Constants.GO_LEFT)) {
                            myPin.goLeft();
                            rcServer.write(output, "< LEFT >");
                        }
                        if (in.equals(Constants.GO_RIGHT)) {
                            myPin.rightLed();
                            rcServer.write(output, "< RIGHT >");
                        }


                        if (in.equals(Constants.STOP_UP)) {
                            rcServer.write(output, "< STOP_UP >");
                            myPin.stopUp();

                        }
                        if (in.equals(Constants.STOP_DOWN)) {
                            rcServer.write(output, "< STOP_DOWN >");
                            myPin.stopDown();

                        }
                        if (in.equals(Constants.STOP_LEFT)) {
                            rcServer.write(output, "< STOP_LEFT >");
                            myPin.stopLeft();

                        }
                        if (in.equals(Constants.STOP_RIGHT)) {
                            rcServer.write(output, "< STOP_RIGHT >");
                            myPin.stopRight();

                        }
                        if (in.contains(Constants.SEND_MESSAGE)) {
                            rcServer.write(output, "< " + in + ">");
                        }
                    }
                    socket.close();
                    System.out.print("Closing socket.\n\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (myPin != null) {
                myPin.stop();
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