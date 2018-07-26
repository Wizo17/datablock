package com.momole.block;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class TransactionBlockManager {

	public TransactionBlockManager()
	{
		
	}
	
	private void writeTransactionBlock( TransactionBlock block )
	{
		File f = new File( "transactions/"+block.getHash() );
		try {
			FileWriter fw = new FileWriter( f , true);
			
			fw.write(Long.toString(block.timestamp)+"\n");
			fw.write(block.getPreviousHash()+"\n");
			fw.write(block.getHash()+"\n");
			fw.write(block.getMiner()+"\n");
			fw.write(block.getNonce()+"\n");
			fw.write(block.getReceiverAddr()+"\n");
			fw.write(block.getSenderAddr()+"\n");
			fw.write(Double.toString(block.getAmount()) + "\n");
			fw.write(Double.toString(block.getFees()));
			fw.write(Integer.toString(block.height));
			
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public TransactionBlock readTransactionBlock( String filename ) throws Exception
	{
		ArrayList<String> list = Block.readFile(filename);
		
		if( list.size()<10 ) {
			throw new Exception("Mauvais bloc : "+filename);
		}
		
		TransactionBlock block = new TransactionBlock(  );

		block.setTimestamp( Long.parseLong(list.get(0)) );
		block.setPreviousHash(list.get(1));
		block.setHash( list.get(2) );
		block.setMiner( list.get(3) );
		block.setNonce( list.get(4) );
		block.setReceiverAddr( list.get(5) );
		block.setSenderAddr( list.get(6) );
		block.setAmount(Double.parseDouble(list.get(7)));
		block.setFees(Double.parseDouble(list.get(8)));
		block.setHeight(Integer.parseInt(list.get(9)));
		
		return block;
	}
	
	public void addTransactionBlock( TransactionBlock block )
	{
		ArrayList<TransactionBlock> blocks = this.retrieveTransactionBlocks();
		block.hash(  );
		if( blocks.isEmpty() ) {
			block.setPreviousHash("[NoHash]");
			block.setHeight(0);
		}else {
			block.setPreviousHash(blocks.get(blocks.size()-1).getHash());
			block.setHeight(blocks.get(blocks.size()-1).getHeight()+1);
		}		
		this.writeTransactionBlock( block );

	}
	
	public ArrayList<TransactionBlock> retrieveTransactionBlocks()
	{
		File directory = new File( "transactions/" );
		if( !directory.exists() || !directory.isDirectory() ) 
			directory.mkdir();
		
		ArrayList<TransactionBlock> list = new ArrayList<TransactionBlock>(); 
		try {
			File[] filesList = directory.listFiles();
			for( File f : filesList ) {
				if( f.isFile() ) {
					TransactionBlock trans = this.readTransactionBlock(f.getPath());
					list.add(trans);		
				}
			}
			/* trier la liste par timestamp */
			Collections.sort( list );
			
			return list;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ; 
	}
	
	
 	
}
