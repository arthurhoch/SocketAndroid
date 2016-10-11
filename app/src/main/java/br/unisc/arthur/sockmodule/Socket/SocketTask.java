package br.unisc.arthur.sockmodule.Socket;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public abstract class SocketTask extends AsyncTask<String, String, String> {

    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private String host;
    private int port;
    private int timeout;
    private BufferedReader bufferedReader;

    public SocketTask(String host, int port, int timeout) {
        super();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }


    public void sendData(String data) throws IOException {
        if (socket != null && socket.isConnected()) {
            os.write(data.getBytes());
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        try {
            SocketAddress sockaddr = new InetSocketAddress(host, port);
            socket = new Socket();
            socket.connect(sockaddr, timeout);
            if (socket.isConnected()) {
                is = socket.getInputStream();
                os = socket.getOutputStream();
                for (String p : params) {
                    os.write(p.getBytes());
                }

                 bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                 result = bufferedReader.readLine();

            }
        } catch (IOException e) {
            result = "";
        } catch (Exception e) {
            result = "";
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                Log.e("Socket Android", "Erro ao fechar conexao", e);
            }
        }
        return result;
    }
}