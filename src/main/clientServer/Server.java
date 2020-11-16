package main.clientServer;

import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private int port = 8080;
    BufferedReader in;
    BufferedWriter out;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(port);
    }
    public void startServer() throws IOException {
        this.clientSocket = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        System.out.println("Server is listening");
        String[] messageIn = in.readLine().split(" ");
        ArrayList<Integer> ints = new ArrayList<>();
        try {
            for (var item : messageIn) {
                ints.add(Integer.parseInt(item));
            }
        }catch (NumberFormatException ex){
            System.out.println("Impossible to read numbers");
            out.write("Impossible to read numbers");
            out.flush();
        }
        Integer bigger, smaller;
        if(ints.get(0) > ints.get(1)){
            bigger = ints.get(0);
            smaller = ints.get(1);
        }
        else if(ints.get(0) == ints.get(1)){
            bigger = -1;
            smaller = 0;
        }
        else{
            bigger = ints.get(1);
            smaller = ints.get(0);
        }
        if(bigger == -1 || (bigger > 0 && smaller == 0))
            out.write("1");
        else
            out.write(countPairs(bigger, smaller).toString());
        out.flush();
        clientSocket.close();
        System.out.println("Connection closed");

    }
    private BigInteger countPairs(Integer bigger, Integer smaller){
        return  getFactorial(bigger).divide(getFactorial(smaller).multiply(getFactorial(bigger - smaller)));

    }
    public static BigInteger getFactorial(Integer f) {
        if (f <= 1) {
            return BigInteger.valueOf(1);
        }
        else {
            return BigInteger.valueOf(f).multiply(getFactorial(f - 1));
        }
    }


}

