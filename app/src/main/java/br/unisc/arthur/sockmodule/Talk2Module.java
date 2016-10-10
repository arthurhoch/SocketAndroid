package br.unisc.arthur.sockmodule;

import android.util.Log;


public class Talk2Module {

    public static final String TYPE_SQL = "sql";

    private final String host;
    private final int port;

    public String returnMessage;

    public Talk2Module(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String request(String message, String type) {

        SocketTask socketTask;

        Log.d("Socket", "Incializou");

        socketTask = new SocketTask(host, port, 5000) {
            @Override
            protected void onProgressUpdate(String... values) {
                returnMessage = values[0];
                Log.d("Socket", "Recebeu2 :" + returnMessage);
            }
        };

        message = type + message;

        socketTask.execute(message == null ? "" : message.toString());



        return returnMessage;
    }

}
