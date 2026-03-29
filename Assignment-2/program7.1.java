import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        int port = 5000; // The port the server will listen on

        System.out.println("=== Echo Server Started ===");
        
        // 1. Create a ServerSocket to listen for incoming connections
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening for connections on port " + port + "...");

            // 2. Accept an incoming client connection (This blocks until a client connects)
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from: " + clientSocket.getInetAddress());

            // 3. Set up input and output streams for communication
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String inputLine;
                
                // 4. Read messages from the client in a loop
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);
                    
                    // Echo the message back to the client
                    out.println("Server Echo: " + inputLine);
                    
                    // Break the loop if the client sends "bye"
                    if ("bye".equalsIgnoreCase(inputLine)) {
                        System.out.println("Client disconnected.");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error communicating with client: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
