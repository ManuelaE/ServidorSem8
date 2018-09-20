package com.example.estudiante.servidorsem8;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receptor extends Thread{

	//1. Lo primero que tenemos que hacer es implementar Observer
	
	Socket socket;

	//Paso 2
	OnMessage observer;

	public Receptor( Socket socket) {
		this.socket = socket;
	}
	
	@Override
		public void run() {
		
		try {
			
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader( new InputStreamReader(is) );
			
			//siempre quiero que este en funcionamiento
			while (true) {
				String line = reader.readLine();
				Log.e("RECIBIDO", line);

				//Paso 4: solo funciona cuando observer es diferente de nulo
				observer.onReceive(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//Observable
	//Paso 1
	public interface OnMessage{

		public void onReceive(String mensaje);
	}

	//Paso 3: saca el observer del nulo
	public void setObserver( OnMessage observer ){

		this.observer = observer;
	}
	
}
