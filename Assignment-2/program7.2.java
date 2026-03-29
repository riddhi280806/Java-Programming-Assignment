import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) {
        String hostname = "127.0.0.1"; // Localhost
        int port = 5000; // Must match the server's port

        System.out.println("=== Echo Client Started ===");

        // 1. Connect to the server using a Socket
        try (
            Socket socket = new Socket(hostname, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to the Echo Server. Type a message (or 'bye' to exit):");

            String userInput;
            
            // 2. Read from the console in a loop
            while ((userInput = consoleReader.readLine()) != null) {
                // Send the message to the server
                out.println(userInput);
                
                // Read the echoed response from the server
                String serverResponse = in.readLine();
                System.out.println(serverResponse);
                
                // Exit if the user types "bye"
                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }
}
