package com.momole.block;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Block implements Comparable<Block> {
	
	protected long timestamp ;  /* timestamp */
	protected String previousHash ; 
	protected String hash;
	protected String miner;
	protected int height; 
	protected String nonce ;
	
	public Block( )
	{
		Date d = new Date( );

		this.timestamp = d.getTime( );
		this.previousHash = this.hash = "";
		this.miner = "DefaultMiner";
		this.height = 3 ;
		this.hash = "";
		this.nonce="TheMagicNonce";
	}
	
	public Block( String prevHash , String miner , int height ) 
	{	
		Date d = new Date( );

		this.timestamp = d.getTime( );
		this.previousHash = prevHash;
		this.miner = "DefaultMiner";
		this.height = 3;
		this.hash = "";
		this.nonce="TheMagicNonce";
	}
	
	static public String toHexString( byte[] bytes )
	{
		String hex = "";
		for( int i=0;i<bytes.length;++i ) {
			String hexString = Integer.toHexString( bytes[i] & 0xff );
			hex += hexString;
		}
		return hex;
	}
	static public ArrayList<String> readFile( String file )
	{
		ArrayList<String> list = new ArrayList<String>();
		
		File f = new File(file);
		try {
			String buffer="";
			BufferedReader reader = new BufferedReader( new FileReader(f) );
			while( (buffer=reader.readLine())!=null ) {
				list.add( buffer );
			}
			reader.close();
			return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
		
	}
	
	static public byte convertStrToByte( String str ) throws Exception
	{
		str = str.toLowerCase();
		HashMap<Character,Byte> asciiMap = new HashMap<Character,Byte>();
		
		asciiMap.put('0', (byte)0); 	asciiMap.put('a', (byte)10);
		asciiMap.put('1', (byte)1); 	asciiMap.put('b', (byte)11);
		asciiMap.put('2', (byte)2); 	asciiMap.put('c', (byte)12);
		asciiMap.put('3', (byte)3);  	asciiMap.put('d', (byte)13);
		asciiMap.put('4', (byte)4); 	asciiMap.put('e', (byte)14);
		asciiMap.put('5', (byte)5); 	asciiMap.put('f', (byte)15);
		asciiMap.put('6', (byte)6);
		asciiMap.put('7', (byte)7);
		asciiMap.put('8', (byte)8);
		asciiMap.put('9', (byte)9);
		
		if( !str.isEmpty() ) {
			
			if( str.length() < 2 ) {
				if(! asciiMap.containsKey(str.charAt(0)) ) {
					throw new Exception("La chaine '"+str+"' contient des caractères non autorisées"); 
				}else {
					return asciiMap.get( str.charAt(0) );
				}
			}else {
				if(!asciiMap.containsKey(str.charAt(0)) ||!asciiMap.containsKey(str.charAt(1))) {
					throw new Exception("La chaine '"+str+"' contient des caractères non autorisées");
				}
				
				byte higher = asciiMap.get(str.charAt(0));
				byte lower = asciiMap.get(str.charAt(1));
				byte compound = (byte)((lower | (higher<<4))&0xff);
				return compound;
			}
		}
		return 0; 
	}

	static public byte[] fromHexString( String hexString ) throws Exception
	{
		int nbBytes = hexString.length()/2;
		if( hexString.length() - 2*nbBytes > 0) { /* si (hexString.length%2) > 0*/
			nbBytes++; 
		}
		byte[] data = new byte[nbBytes];
		for( int i=0;i<nbBytes;++i ) {
			String hex = hexString.substring(i*2,Math.min((i+1)*2, hexString.length()-1)+1 );
			data[i] = Block.convertStrToByte(hex);
		}
		
		return data;
		
	}
	
	/**
	 * @brief effectue un hash (doit  être redefinie ) 
	 * 
	 * */
	public void hash( )
	{
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	
	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getMiner() {
		return miner;
	}

	public void setMiner(String miner) {
		this.miner = miner;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	@Override
	public int compareTo(Block o) {
		Block block = (Block)o;
		return (int)(this.timestamp - block.getTimestamp());
	}

	
}
