package allClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class for connect to server and send data to server.
 */
public class RCClient {
    private static String SERVER_IP = "192.168.0.102";
    private Socket socket = null;
    private PrintWriter output = null;
    private BufferedReader input = null;

    public RCClient() {
        try {
            socket = new Socket(InetAddress.getByName(SERVER_IP), 4141);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sendCommand(Constants.SYNCHRONIZED);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendCommand(String command) {
        System.out.println("Command: " + command);
        output.println(command);
    }

    public void sendMessage(String message) {
        System.out.println("Message: " + message);
        output.println(message);
    }


    void closeUp() {
        try {
            sendCommand("FIN");
            String in = "";
            while ((in = input.readLine()) != null) {
                if (in.equals("FIN-ACK")) {
                    break;
                }
            }
            System.out.print("Closing socket.");
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
