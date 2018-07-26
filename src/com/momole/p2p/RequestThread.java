package com.momole.p2p;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class RequestThread extends Thread{
	
	private Socket clientSocket ; 
	private static int nbThread=0;
	private int threadID;
	
	public RequestThread( Socket socket )
	{
		this.clientSocket = socket;
		this.threadID = nbThread;
		++RequestThread.nbThread;
		
	}
	
	public void run()
	{
		try {
			
			try {
				InputStream is = this.clientSocket.getInputStream();
				OutputStream os = this.clientSocket.getOutputStream();
				
				BufferedReader reader = new BufferedReader( new InputStreamReader(is) );
				String request="";
				
				while( (request=reader.readLine())!=null ) {
					String clientIP = this.clientSocket.getInetAddress().getHostAddress();
					System.out.println("["+this.threadID+"]["+clientIP+"]Resolving request : "+request);

					String r[] = request.split("/");
					 
					if( r.length >0 ) {
						if( r[0].equals("COPY_ROUTING_TABLE") ) { /* requête de demande de la table de routage */
							/* repondre avec la table */
							System.out.println("["+this.threadID+"]["+clientIP+"]Sending routing table");
							ArrayList<PeerNode> table = RoutingTable.retrieveRoutingTable();
							for( int i=0; i<4 ; ++i) {
								
								os.write( String.format("%d:%d:guid\n", i , table.get(i).getGUID()).getBytes("UTF-8")); 
								os.write( String.format("%d:%s:public_key\n", i , table.get(i).getPublicKey()).getBytes("UTF-8")); 
								os.write( String.format("%d:%s:ip_address\n", i , table.get(i).getIpAddress()).getBytes("UTF-8")); 
								os.write( String.format("%d:%d:tcp_port\n", i , table.get(i).getTcpPort()).getBytes("UTF-8")); 
								os.write("\n\n".getBytes("UTF-8"));
							}
						}else if( r[0].equals("QUIT") ) {
							break ; 
						}else if( r[0].equals("JOIN") ) {
							
							ArrayList<PeerNode> table = RoutingTable.retrieveRoutingTable();
							PeerNode node = null;
							for( int i=0;i<table.size();++i ) {
								node = table.get(i);
								if( node.getIpAddress().equals("0.0.0.0") ) {
									/* on enregistre son IP */
									node.setIpAddress(clientIP);
									table.set( i , node);
									System.out.println("["+this.threadID+"]["+clientIP+"] Adding "+clientIP);
									os.write(Integer.toString(node.getGUID()).getBytes("UTF-8")); 
									RoutingTable.saveRoutingTable(table);
									break;
								}
							}
						}else if( r[0].equals("PING") ) {
							if( Wallet.hasWallet() ) {
								Wallet wallet = Wallet.retrieveWallet();
								os.write( wallet.getPublicKey().getBytes("UTF-8"));	
							}
						}else if(r[0].equals("SYNC_TRANSACTION_BLOCKS")) {
							/* ecrire toutes les transactions */

							File directory = new File("transactions/");
							int i=0;

							os.write((Integer.toString(directory.listFiles().length)+"\n").getBytes("UTF-8"));
							
							for( File f : directory.listFiles() ) {
								System.out.println("Sending "+f.getName());
								BufferedReader br = new BufferedReader( new FileReader( new File( f.getPath() ) ) );
								String buffer=""; 
								while( null!=(buffer=br.readLine()) ) {
									os.write((i+":"+buffer+"\n").getBytes("UTF-8"));
								}
								++i;
							}
						}else{
							os.write(("Unknown request type '"+request+"'").getBytes("UTF-8"));
						}
					}else {
						os.write("Bad request format".getBytes("UTF-8"));
					}
					
				}
				this.clientSocket.close();

			}catch(SocketException e) {
				
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
