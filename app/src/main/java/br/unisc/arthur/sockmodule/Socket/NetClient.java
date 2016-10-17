package br.unisc.arthur.sockmodule.Socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetClient {

    public static final int BUFFER_SIZE = 4096;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    private String host = null;
    private int port = 443;


    public NetClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void connectWithServer() {
        try {
            if (socket == null) {
                socket = new Socket(this.host, this.port);
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disConnectWithServer() {
        if (socket != null) {
            if (socket.isConnected()) {
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendDataWithString(String message) {
        if (message != null) {
            connectWithServer();
            out.write(message);
            out.flush();
        }
    }

    public String receiveDataFromServer() {
        try {
            String message = "";

            char[] buffer = new char[BUFFER_SIZE];
            in.read(buffer);
            message = String.copyValueOf(buffer);

            disConnectWithServer();
            return message;
        } catch (IOException e) {
            return "Error receiving response:  " + e.getMessage();
        }
    }


}