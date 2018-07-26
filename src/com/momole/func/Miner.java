package com.momole.func;

import com.momole.block.Block;

public class Miner {

	private Block block ;
	
	public Miner( Block blk )
	{
		this.block = blk ;
	}
	
	public void doMyJob()
	{
		/*effectuer le minage*/
		
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	
}
