package com.momole.p2p;

import java.io.IOException;
import java.io.OutputStream;
import javafx.scene.control.*;

public class CustomOutputStream extends OutputStream {

	private TextArea field ;
	
	public CustomOutputStream( TextArea field )
	{
		this.field = field;
		this.field.setEditable(false);
	}
	
	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		String unicode=null; 
		this.field.appendText( ""+Character.valueOf((char)b));
	}
	
}
