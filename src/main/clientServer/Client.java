package main.clientServer;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    int port = 8080;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public Client() throws IOException {
        this.socket = new Socket("localhost", port);
        reader = new BufferedReader(new InputStreamReader(System.in));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    public String sendAndGetResult(String message) throws IOException {
        out.write(message);
        out.flush();
        return in.readLine();
    }
}
