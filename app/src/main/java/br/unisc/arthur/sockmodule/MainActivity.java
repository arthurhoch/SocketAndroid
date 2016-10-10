package br.unisc.arthur.sockmodule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button btnSend;
    private TextView txtStatus;
    private TextView txtValor;
    private TextView txtHostPort;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend = (Button) findViewById(R.id.btnSend);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtValor = (TextView) findViewById(R.id.txtValor);
        txtHostPort = (TextView) findViewById(R.id.txtHostPort);
        btnSend.setOnClickListener(btnConnectListener);
    }

    private OnClickListener btnConnectListener = new OnClickListener() {
        public void onClick(View v) {

            // Recupera host e porta
            String hostPort = txtHostPort.getText().toString();
            int idxHost = hostPort.indexOf(":");
            final String host = hostPort.substring(0, idxHost);
            final int port = Integer.parseInt(hostPort.substring(idxHost + 1));

            Talk2Module talk2Module;
            talk2Module = new Talk2Module(host, port);

            String sql = "create table x";

            String moduleResponse = talk2Module.request(sql, Talk2Module.TYPE_SQL);



        }
    };


}
