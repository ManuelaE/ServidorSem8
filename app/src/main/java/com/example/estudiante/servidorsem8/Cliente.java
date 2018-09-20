package com.example.estudiante.servidorsem8;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends Thread {

    Socket s;
    Receptor rec;

    MainActivity activity;

    public Cliente( MainActivity activity){
        this.activity = activity;
    }


    //Control + o
    @Override
    public void run() {

        try {
            s = new Socket("10.0.2.2", 5000);
            //Log.e("debug", "Conexión exitosa");

            rec = new Receptor(s);

            //Paso 6
            rec.setObserver(activity);
            rec.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviar(){

        //creo un hilo "rápido"
        new Thread(new Runnable() {

            //Este codigo se va a ejecutar en paralelo
            @Override
            public void run() {

                //esto todavía no es un hilo entonces cuando lo llamo
                //en el main no es correcto
                try{

                    OutputStream os = s.getOutputStream();

                    PrintWriter out = new PrintWriter( new OutputStreamWriter(os));
                    out.println("Hola mundo, funciona");

                    //para mandar. Una vez que lo haga muere el hilo
                    out.flush();

                }catch (Exception e){
                    //me muestra en donde está el error
                    e.printStackTrace();
                }
            }

        }).start();

    }

}
