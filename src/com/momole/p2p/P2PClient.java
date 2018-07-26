package com.momole.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class P2PClient {
	
	private String serverIP ;
	private int port;
	
	public P2PClient( String serverIP , int port )
	{
		this.serverIP = serverIP;
		this.port = port;
	}
	
	public String request( String request )
	{
		try {
			Socket sock = new Socket(this.serverIP , this.port );
			String buffer="";
			String requestResult="";
			OutputStream os = sock.getOutputStream();
			InputStream is = sock.getInputStream();
			os.write( request.getBytes("UTF-8") );
			
			BufferedReader br = new BufferedReader( new InputStreamReader( is ) );
			while( null!=(buffer=br.readLine()) )
				requestResult+=buffer+"\n";
			
			os.write("QUIT".getBytes("UTF-8"));
			
			sock.close();
			
			return requestResult ;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "<UnknownHost>";

			
		} catch (IOException e) {
			e.printStackTrace();
			return "<null>";
			// TODO Auto-generated catch block
			
		}
		
	}
}
