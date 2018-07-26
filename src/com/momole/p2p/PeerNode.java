package com.momole.p2p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PeerNode 
{
	private int GUID ; 			/* GUID du noeud */
	private String publicKey; 	/* Clé publique*/
	private String ipAddress;	/* adresse IP */
	private int tcpPort ; 		/* port TCP */


	public PeerNode(  )
	{
		this.GUID = 0; 
		this.publicKey = "NoKey"; 
		this.ipAddress = "0.0.0.0"; 
		this.tcpPort = RoutingTable.TCP_PORT; 
	}
	
	public PeerNode( int GUID , String publicKey , String ipAddress , int tcpPort)
	{
		this.GUID = GUID; 
		this.publicKey = publicKey ; 
		this.ipAddress = ipAddress; 
		this.tcpPort = tcpPort ; 
	}

	public void write( FileWriter fw )
	{
		
		try {
			fw.write(Integer.toString(this.GUID)+"\n"); 
			fw.write(this.publicKey+"\n"); 
			fw.write(this.ipAddress+"\n");
			fw.write(Integer.toString(this.tcpPort)+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void read( FileReader fr ) throws Exception
	{
		BufferedReader bufReader = new BufferedReader( fr );
		String buffer="";
		ArrayList<String> lines = new ArrayList<String>();
		
		while( null!=(buffer=bufReader.readLine()) ) {
			lines.add(buffer);
		}
		
		if( lines.size()<4 ) throw new Exception("Le fichier soumis est incorrect");
		
		this.GUID = Integer.parseInt( lines.get(0) );
		this.publicKey = lines.get(1);
		this.ipAddress = lines.get(2);
		this.tcpPort = Integer.parseInt(lines.get(3));
	}
	
	/* envoyer un ping(pour tester la connexion)  */
	public boolean ping()
	{
		try {
			
			Socket sock = new Socket( this.ipAddress , this.tcpPort );
			sock.close();
			return true; 
		} catch (UnknownHostException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public int getGUID() {
		return GUID;
	}

	public void setGUID(int gUID) {
		GUID = gUID;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}
	
	
}
