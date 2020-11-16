package main;

import main.clientServer.Client;
import main.clientServer.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            Server server = new Server();
            server.startServer();

            Client client = new Client();
            System.out.println(client.sendAndGetResult("4 1"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
