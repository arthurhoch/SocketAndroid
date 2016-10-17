package br.unisc.arthur.sockmodule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.unisc.arthur.sockmodule.Socket.Talk2Module;

public class MainActivity extends Activity {
    private Button btnSelect;
    private Button btnInsert;
    private Button btnDelete;

    private EditText i_iniLat;
    private EditText i_iniLng;
    private EditText i_finLat;
    private EditText i_finLng;
    private EditText i_rota;
    private EditText i_distancia;
    private EditText i_distanciaReta;

    private Button btnClear;
    private TextView txtStatus;
    private TextView txtHostPort;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClear = (Button) findViewById(R.id.btnClear);

        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtHostPort = (TextView) findViewById(R.id.txtHostPort);

        i_iniLat = (EditText) findViewById(R.id.i_iniLat);
        i_iniLng = (EditText) findViewById(R.id.i_iniLng);
        i_finLat = (EditText) findViewById(R.id.i_finLat);
        i_finLng = (EditText) findViewById(R.id.i_FinLng);
        i_rota   = (EditText) findViewById(R.id.i_rota);
        i_distancia = (EditText) findViewById(R.id.i_distancia);
        i_distanciaReta = (EditText) findViewById(R.id.i_distanciaReta);

        btnSelect.setOnClickListener(btnSelectListener);
        btnInsert.setOnClickListener(btnInsertListener);
        btnDelete.setOnClickListener(btnDeleteListener);
        btnClear.setOnClickListener(btnClearListener);

        txtHostPort.setText("servidor.br:porta");

    }

    private OnClickListener btnSelectListener = new OnClickListener() {
        public void onClick(View v) {

            String hostPort = txtHostPort.getText().toString();
            int idxHost = hostPort.indexOf(":");
            String host = hostPort.substring(0, idxHost);
            int port = Integer.parseInt(hostPort.substring(idxHost + 1));

            String sql;
            sql = "select * from tabLocalizeUnisc";
            txtStatus.setText(RunSql(host, port, sql));

        }
    };

    private OnClickListener btnInsertListener = new OnClickListener() {
        public void onClick(View v) {

            String hostPort = txtHostPort.getText().toString();
            int idxHost = hostPort.indexOf(":");
            String host = hostPort.substring(0, idxHost);
            int port = Integer.parseInt(hostPort.substring(idxHost + 1));

            String sql;
            sql = "insert into tabLocalizeUnisc values( "+i_iniLat.getText()+", "+i_iniLng.getText()+
                    ", "+i_finLat.getText()+", "+i_finLng.getText()+", \""+i_rota.getText()+
                    "\", "+i_distancia.getText()+", "+i_distanciaReta.getText()+")";

            RunSqlNonReturn(host, port, sql);

        }
    };

    private OnClickListener btnDeleteListener = new OnClickListener() {
        public void onClick(View v) {

            String hostPort = txtHostPort.getText().toString();
            int idxHost = hostPort.indexOf(":");
            String host = hostPort.substring(0, idxHost);
            int port = Integer.parseInt(hostPort.substring(idxHost + 1));

            String sql;
            sql = "delete from tabLocalizeUnisc";
            RunSqlNonReturn(host, port, sql);

        }
    };

    private OnClickListener btnClearListener = new OnClickListener() {
        public void onClick(View v) {
            txtStatus.setText("");

        }
    };


    private void RunSqlNonReturn(String host, int port, String sql) {
        Talk2Module talk2Module;
        talk2Module = new Talk2Module(host, port);
        talk2Module.requestWhitoutResponse(sql, Talk2Module.TYPE_SQL);
    }


    private String RunSql(String host, int port, String sql) {
        Talk2Module talk2Module;
        talk2Module = new Talk2Module(host, port);
        return talk2Module.request(sql, Talk2Module.TYPE_SQL);
    }


}
