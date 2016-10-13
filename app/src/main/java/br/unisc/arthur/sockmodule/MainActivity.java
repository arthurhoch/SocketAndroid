package br.unisc.arthur.sockmodule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import br.unisc.arthur.sockmodule.Socket.Talk2Module;

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

            String hostPort = txtHostPort.getText().toString();
            int idxHost = hostPort.indexOf(":");
            final String host = hostPort.substring(0, idxHost);
            final int port = Integer.parseInt(hostPort.substring(idxHost + 1));

            
            /*************************************************
            //Utilize para inserir na DB; - Inicio
            *************************************************/
            
            /*
            CREATE TABLE tabLocalizeUnisc(
	            iniLat float NOT NULL,
	            iniLng float NOT NULL,
	            finLng float NOT NULL,
	            rota varchar(1000) NOT NULL,
	            distancia int NOT NULL,
	            distanciaReta int NOT NULL
            );
            */
            String sql;
            sql = "insert 0.4 0.4 0.4 aaaaa2 12 2";
            RunSqlNoReturn(host, port, sql);
            
            /*************************************************
            //Utilize para inserir na DB; - Fim
            *************************************************/
            
            
            //Os exemplos abaixo não funcionarão

            // Para testes !!!

            /*Declaração variaveis*/
            //String sql;

            /*Usando informações da interface*/

            //sql = txtValor.getText().toString();
            //txtStatus.setText(RunSql(host, port, sql));

            /*Atribuindo informações*/
            //sql = "create table x";
            //RunSqlNoReturn(host, port, sql);


            // Para testes !!!

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
