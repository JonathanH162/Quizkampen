package se.nackademin.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket;
    private static int PORT = 1337;
    private InetAddress IP;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public Client() throws IOException {
        IP = InetAddress.getLocalHost();
        socket = new Socket(IP, PORT);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
