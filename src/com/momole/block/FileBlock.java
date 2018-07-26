package com.momole.block;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileBlock extends Block {

	private String encFileHash; 	/*Hash du fichier crypté*/
	private int transactionIndex ; /* index de la transaction correspondant au fichier */
	private String fileName ;	/* nom du fichier */
	private String senderAddr; /* adresse de l'emmeteur */
	private String receiverAddr; /* adresse du destinataire */
	
	public FileBlock()
	{
		super();
		this.senderAddr = this.receiverAddr="";
		this.encFileHash=""; 
		this.transactionIndex =0;
		this.fileName = "";
	}
	
	public FileBlock( String encfilehash, int transIdx , String fileName , String senderAddr , String receiverAddr) 
	{
		super();
		this.encFileHash=encfilehash ; 
		this.transactionIndex = transIdx;
		this.fileName = fileName ;
		this.senderAddr = senderAddr;
		this.receiverAddr = receiverAddr;
	}
	
	public FileBlock(String prevHash , String miner , int height,
			String encfilehash, int transIdx , String fileName, 
			String senderAddr , String receiverAddr)	
	{
		super( prevHash , miner , height );
		this.encFileHash=encfilehash ; 
		this.transactionIndex = transIdx;
		this.fileName = fileName ;
		this.senderAddr = senderAddr;
		this.receiverAddr = receiverAddr;
	}
	
	public void hash()
	{
		String source = 
				this.miner+
				this.timestamp+
				this.height+
				this.nonce+
				this.previousHash+
				this.senderAddr+
				this.receiverAddr+
				this.encFileHash;
		
		
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
	
	public int getTransactionIndex() {
		return transactionIndex;
	}

	public void setTransactionIndex(int transactionIndex) {
		this.transactionIndex = transactionIndex;
	}

	public String getFileName() {
		return fileName;
	}

	public void setName(String name) {
		this.fileName = name;
	}

	public String getEncFileHash() {
		return encFileHash;
	}

	public void setEncFileHash(String encFileHash) {
		this.encFileHash = encFileHash;
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

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
			"Hash du fichier crypté: "+	this.encFileHash;
	}
	
	
	
	
}
