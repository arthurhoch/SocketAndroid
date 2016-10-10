package br.unisc.arthur.sockmodule;



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

/**
 * Classe para envio de dados via socket
 *
 * @author Numeroreal
 *
 */
public abstract class SocketTask extends AsyncTask<String, String, String> {

    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private String host;
    private int port;
    private int timeout;
    private BufferedReader bufferedReader;

    /**
     * Construtor com host, porta e timeout
     *
     * @param host
     *            host para conexão
     * @param port
     *            porta para conexão
     * @param timeout
     *            timeout da conexão
     */


    public SocketTask(String host, int port, int timeout) {
        super();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    /**
     * Envia dados adicionais se estiver conectado
     *
     * @param data
     *            dados adicionais
     * @throws IOException
     */
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

            } else {
                publishProgress("CONNECT ERROR");
            }
        } catch (IOException e) {
            publishProgress("ERROR");
            Log.e("SocketAndroid", "Erro de entrada e saida", e);
            result = "";
        } catch (Exception e) {
            publishProgress("ERROR");
            Log.e("SocketAndroid", "Erro generico", e);
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
                Log.e("SocketAndroid", "Erro ao fechar conexao", e);
            }
        }
        return result;
    }
}