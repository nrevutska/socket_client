package com.sockets;


import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int serverPort = 5555;
        String address = "localhost";
        try {
            Socket socket = new Socket(address, serverPort);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Type message");
            new Thread(() -> {
                while (true) {
                    String line = null;
                    try {
                        line = inputStream.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(line);
                    System.out.println();
                }
            }).start();
            String line;
            while (true) {
                line = keyboardReader.readLine();
                outputStream.writeUTF(line);
                outputStream.flush();
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
