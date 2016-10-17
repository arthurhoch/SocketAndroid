package br.unisc.arthur.sockmodule.Socket;

import java.util.concurrent.ExecutionException;


public class Talk2Module {

    public static final String TYPE_SQL = "sql ";

    private final String host;
    private final int port;

    public String returnMessage;

    public Talk2Module(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String request(String message, String type) {

        SocketTask socketTask;

        socketTask = new SocketTask(host, port, 5000) {
            @Override
            protected void onProgressUpdate(String... values) {
                returnMessage = values[0];
            }
        };

        message = type + message;

        socketTask.execute(message == null ? "" : message.toString());

        try {
            returnMessage = socketTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return returnMessage;
    }

    public void requestWhitoutResponse(String message, String type) {


        class NonBlock implements Runnable {
            String message;
            String type;
            NonBlock(String message, String type) { this.message = message; this.type = type; }
            public void run() {
                request(message, type);
            }
        }
        Thread t = new Thread(new NonBlock(message, type));
        t.start();

    }

}
