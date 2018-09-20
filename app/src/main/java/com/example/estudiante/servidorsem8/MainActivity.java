package com.example.estudiante.servidorsem8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Receptor.OnMessage{

    private Button btnEnviar;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cliente = new Cliente(this);
        cliente.start();

        btnEnviar = findViewById(R.id.btn_enviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliente.enviar();
            }
        });
    }

    //Este metodo se está enjecutando en pararlelo
    @Override
    public void onReceive(final String mensaje) {

        //obliga a lo que llegue a correr en un hilo principal
        //admite un runnable
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
