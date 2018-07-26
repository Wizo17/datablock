package com.momole.block;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class TransactionBlock extends Block {
	
	
	private String senderAddr ; 
	private String receiverAddr ; 
	private double amount ; 
	private double fees;


	public TransactionBlock()
	{
		super( );
		this.senderAddr = "";
		this.receiverAddr = "";
		this.amount = 0;
		this.fees = 0.3;

	}
	
	public TransactionBlock( String sender , String receiver , double amount , double fees )
	{
		super( );
		this.senderAddr = sender;
		this.receiverAddr = receiver;
		this.amount = amount ;
		this.fees = fees;
	}
	
	public TransactionBlock( String prevHash , String miner , int height , 
			String sender , String receiver , double amount , double fees)
	{
		super( prevHash , miner, height );
		this.senderAddr = sender;
		this.receiverAddr = receiver;
		this.amount = amount ;
		this.fees = fees;
	}
	
	public void hash()
	{
		String source = this.miner+
				this.amount+
				this.fees+
				this.timestamp+
				this.height+
				this.nonce+
				this.previousHash+
				this.senderAddr+
				this.receiverAddr;
		
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedHash = digest.digest( source.getBytes("UTF-8") );
			this.hash= Block.toHexString(encodedHash);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public String getSenderAddr() {
		return senderAddr;
	}

	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}

	public String getReceiverAddr() {
		return receiverAddr;
	}

	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}
	
	public String toString()
	{
		return 
			"Index( hash ) : "+		this.hash+"\n"+
			"Timestamp : "+	this.timestamp+"\n"+
			"Hash prec : "+	this.previousHash+"\n"+
			"Mineur : " + 	this.miner+"\n"+
			"Height : " + 	this.height+"\n"+
			"Emmetteur : "+	this.senderAddr+"\n"+
			"Dest : "+		this.receiverAddr+"\n"+
			"Quantité: "+	this.amount+" tokens \n"+
			"Frais : "+		this.fees+" tokens";
	}
	
}
