package com.momole.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import com.momole.block.Block;

public class DriveFile {
	
	private String index; 
	private String location ;
	private int peers; 
	private long timestamp;
	private long sharedSpace ; 
	
	public DriveFile( )
	{
		this.index = "";
		this.location = "";
		this.peers = 0;
		this.timestamp = (new Date()).getTime();
		this.sharedSpace = 0;
	}
	public DriveFile( String index , String location , int peers , long sharedSpace )
	{
		this.index = index;
		this.location = location;
		this.peers = peers;
		this.timestamp = (new Date()).getTime();
		this.sharedSpace = sharedSpace;
	}

	public static String makeIndex( String path )
	{
		String index=null; 
		
		MessageDigest digest;
		try {
			
			digest = MessageDigest.getInstance("MD5");
			byte[] rawIndex =  digest.digest( (path+Double.toString( Math.random()*150)).getBytes("UTF-8") );
			index=Block.toHexString(rawIndex);
			index=Base64.getEncoder().withoutPadding().encodeToString(index.getBytes("UTF-8"));
			
			return index;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void write( FileWriter writer )
	{
		try {
			writer.write( this.index+"\n");
			writer.write( this.location+"\n");
			writer.write( this.peers +"\n");
			writer.write( this.timestamp+"\n" );
			writer.write( this.sharedSpace+"\n" );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void read( FileReader reader )
	{
		String buffer=null;
		BufferedReader bufReader = new BufferedReader( reader );
		
		try {
			if( null!=(buffer=bufReader.readLine()) )this.index=buffer;
			if( null!=(buffer=bufReader.readLine()) )this.location=buffer;
			if( null!=(buffer=bufReader.readLine()) )this.peers=Integer.parseInt(buffer);
			if( null!=(buffer=bufReader.readLine()) )this.timestamp=Long.parseLong(buffer);
			if( null!=(buffer=bufReader.readLine()) )this.sharedSpace=Long.parseLong(buffer);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPeers() {
		return peers;
	}

	public void setPeers(int peers) {
		this.peers = peers;
	}
	public long getSharedSpace() {
		return sharedSpace;
	}

	public void setSharedSpace(long sharedSpace) {
		this.sharedSpace = sharedSpace;
	}
	
	
}
