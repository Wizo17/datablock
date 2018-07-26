package com.momole.p2p;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class P2P {



	public static void startServer( int port )
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					
					InetAddress host = InetAddress.getLocalHost();
					System.out.println("Openning peer server on : "+host.getHostAddress()+":"+port);
					ServerSocket server = new ServerSocket( port );
					while(true) {

						try {
							
							Socket sock = server.accept(); /*attendre une requête*/
							System.out.println("New client connected "+sock.getInetAddress().getHostAddress());
							new RequestThread( sock ).start();

						}catch(SocketException e) {
							e.printStackTrace() ;
						}
						
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					

				} catch (IOException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		
	}
	
	
	
}
