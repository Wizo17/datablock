package com.momole.p2p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class Wallet {

	public static String SALT1="MAA29081994";
	public static String SALT2="HACK2018";
	
	private String hashedPassword;
	private String privateKey ; 
	private String publicKey;
	
	/**
	 * @brief génère une clé quasi unique
	 * */
	public static String generateKey( String pad )
	{
		String strTimestamp = Long.toString( (new Date()).getTime() );
		try {
			
			MessageDigest dig = MessageDigest.getInstance("SHA-256");
			byte[] hashed = dig.digest( (strTimestamp+pad).getBytes("UTF-8") ); 
			return Base64.getEncoder().withoutPadding().encodeToString(hashed);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "<null>";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "<null>";
		}
		
	}	
	
	public Wallet( String hashedPassword )
	{
		this.hashedPassword=hashedPassword;
		this.genKeys();
	}
	
	public static boolean hasWallet()
	{
		return (new File(".peer")).exists();
	}
	
	public void write( FileWriter fw ) throws IOException
	{

		fw.write(this.hashedPassword+"\n");
		fw.write(this.privateKey+"\n"); /* Générer la clé publique */
		fw.write(this.publicKey+"\n");  /* Générer la clé privée */
	}
	
	public void read( FileReader fr ) throws IOException
	{
		BufferedReader br = new BufferedReader( fr );
		String buffer="";

		if(null!=(buffer=br.readLine())) this.hashedPassword=buffer;
		if(null!=(buffer=br.readLine())) this.privateKey=buffer;
		if(null!=(buffer=br.readLine())) this.publicKey=buffer;
	}
	
	public static Wallet retrieveWallet()
	{
		try {
			Wallet w=new Wallet("");
			FileReader fr = new FileReader( ".peer" );
			w.read(fr);
			fr.close();
			return w;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void genKeys()
	{
		this.privateKey = Wallet.generateKey( Wallet.SALT1 );
		this.publicKey = "DB"+Wallet.generateKey( Wallet.SALT2 );	
	}
	
	public boolean verifyPassword( String password )
	{
		return (password.equals(this.hashedPassword));
	}
	
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	
}
