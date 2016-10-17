package br.unisc.arthur.sockmodule.Socket;

import android.os.AsyncTask;
import android.util.Log;


public abstract class SocketTask extends AsyncTask<String, String, String> {


    private String host;
    private int port;
    private int timeout;
    private NetClient netClient;


    public SocketTask(String host, int port, int timeout) {
        super();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }


    @Override
    protected String doInBackground(String... params) {
        String result = "";

        try {

            Log.d("Socket", "Create :"+host+":"+port);
            netClient = new NetClient(host, port);

            for (String p: params) {
                Log.d("Socket", "Send :"+p);
                netClient.sendDataWithString(p);
            }
            Log.d("Socket", "Recive: ");
            result = netClient.receiveDataFromServer();
            Log.d("Socket", "Recive:"+result);

            } catch (Exception e) {
                Log.e("Socket Android", "Erro ao fechar conexao", e);
            }

        return result;
    }
}