package br.unisc.arthur.sockmodule.Socket;

import android.os.AsyncTask;
import android.util.Log;


public abstract class SocketTask extends AsyncTask<String, String, String> {


    private String host;
    private int port;
    private int timeout;
    private NetClient netClient;

    private TextView v;


    public SocketTask(String host, int port, int timeout) {
        super();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public SocketTask(String host, int port, int timeout, TextView v) {
        super();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.v = v;
    }


    @Override
    protected String doInBackground(String... params) {
        String result = "";

        try {

            netClient = new NetClient(host, port);

            for (String p: params) {
                netClient.sendDataWithString(p);
            }
            result = netClient.receiveDataFromServer();

            publishProgress(result);

            } catch (Exception e) {
                //Log.e("Socket Android", "Erro ao fechar conexao", e);
                publishProgress("Conection problem.");
            }

        return result;
    }
}
