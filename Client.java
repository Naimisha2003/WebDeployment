import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "127.0.0.1";
        final int SERVER_PORT = 12345;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server: " + SERVER_ADDRESS + ":" + SERVER_PORT);
            System.out.println("Type a message and press Enter to send, or type 'exit' to quit");

            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                if ("exit".equalsIgnoreCase(userInputLine)) {
                    break;
                }
                out.println(userInputLine);
                System.out.println("Server: " + in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
