package com.momole.block;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class FileBlockManager {
	
	public FileBlockManager()
	{
		
	}
	
	private void writeFileBlock( FileBlock block )
	{
		File f = new File( "files/"+block.getHash() );
		try {
			FileWriter fw = new FileWriter( f , true);
			
			fw.write(Long.toString(block.timestamp)+"\n");
			fw.write(block.getPreviousHash()+"\n");
			fw.write(block.getHash()+"\n");
			fw.write(block.getMiner()+"\n");
			fw.write(block.getNonce()+"\n");
			fw.write(block.getSenderAddr()+"\n");
			fw.write(block.getReceiverAddr()+"\n");
			fw.write(block.getEncFileHash()+"\n");
			fw.write(block.getTransactionIndex()+"\n");
			fw.write(Integer.toString(block.height));
			
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public FileBlock readFileBlock( String filename ) throws Exception
	{
		ArrayList<String> list = Block.readFile(filename);
		
		if( list.size()<10 ) {
			throw new Exception("Mauvais bloc : "+filename);
		}
		
		FileBlock block = new FileBlock(  );

		block.setTimestamp( Long.parseLong(list.get(0)) );
		block.setPreviousHash(list.get(1));
		block.setHash( list.get(2) );
		block.setMiner( list.get(3) );
		block.setNonce( list.get(4) );
		block.setReceiverAddr( list.get(5) );
		block.setSenderAddr( list.get(6) );
		block.setEncFileHash( list.get(7) );
		block.setTransactionIndex(Integer.parseInt(list.get(8)) );
		block.setHeight(Integer.parseInt(list.get(9)));
		
		return block;
	}
	
	public ArrayList<FileBlock> retrieveFileBlocks()
	{
		File directory = new File( "files/" );
		ArrayList<FileBlock> list = new ArrayList<FileBlock>(); 
		
		if( !directory.exists() || !directory.isDirectory() ) 
			directory.mkdir();
		
		try {
			File[] filesList = directory.listFiles();
			for( File f : filesList ) {
				if( f.isFile() ) {
					FileBlock trans = this.readFileBlock(f.getPath());
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
	
	public void addFileBlock( FileBlock block )
	{
		ArrayList<FileBlock> blocks = this.retrieveFileBlocks();
		block.hash(  );
		if( blocks.isEmpty() ) {
			block.setPreviousHash("[NoHash]");
			block.setHeight(0);
		}else {
			block.setPreviousHash(blocks.get(blocks.size()-1).getHash());
			block.setHeight(blocks.get(blocks.size()-1).getHeight()+1);
		}		
		this.writeFileBlock( block );
	}
	
	

	
	
	
}
