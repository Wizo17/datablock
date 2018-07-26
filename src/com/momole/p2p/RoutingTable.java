package com.momole.p2p;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class RoutingTable {
	
	private static final int GUID_BITS=4;
	private static final int GUID_MASK = (1<<(1+GUID_BITS))-1;
	public static final int TCP_PORT=2112;
	public static int GUID_OFFSET=0;
	
	public RoutingTable()
	{
		RoutingTable.GUID_OFFSET = 0;
	}
	
	/**
	 * @brief Crée une table de routage 
	 * 
	 * @param GUID GUID_offset de l'ordinateur actuel 
	 * 
	 * */
	public static void createEmptyRoutingTable( int GUID_offset )
	{
		File directory = new File("routing/");
		if( !directory.exists() || !directory.isDirectory())
			directory.mkdir();
		
		for( int i=0;i<4;++i ) {


			try {
				FileWriter fw = new FileWriter( new File("routing/route.00"+i) );
				
				PeerNode zeroNode = new PeerNode( GUID_MASK & (GUID_offset+(int)(Math.pow(2,i))) , 
						"NoKey" , "0.0.0.0" , 2112 );
				zeroNode.write(fw);
				fw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList<PeerNode> retrieveRoutingTable()
	{
		ArrayList<PeerNode> nodes  = new ArrayList<PeerNode>();
	
		for( int i=0;i<4;++i ) {
			try {
				PeerNode node = new PeerNode();
				FileReader fr = new FileReader( new File("routing/route.00"+i) );
				node.read(fr);
				nodes.add(node);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nodes;
	}
	
	public static void saveRoutingTable( ArrayList<PeerNode> nodeList )
	{
		for( int i=0;i<4;++i ) {
			try {

				FileWriter fw = new FileWriter( new File("routing/route.00"+i) );
				nodeList.get(i).write(fw);
				fw.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/* Rechercher une route libre dans la table*/
	
}
