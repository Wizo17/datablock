package com.momole.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Drive {



	
	public static void uploadFile( String path )
	{
		try {
			String index=DriveFile.makeIndex(path);
			FileWriter fw = new FileWriter(  "drive/"+index );
			DriveFile drvFile = new DriveFile( index ,"LOC-"+((int)(1000+Math.random()*9999)),
					((int)(10+Math.random()*90)),(long)(new File(path).length()));
			drvFile.write(fw);
			fw.close( );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<DriveFile> retrieveFiles()
	{
		File dir = new File( "drive/" );
		ArrayList<DriveFile> files = new ArrayList<DriveFile>(); 
		
		for( File file : dir.listFiles() ) {
			FileReader reader;
			try {
				reader = new FileReader( file );
				DriveFile drvFile = new DriveFile( );
				drvFile.read(reader);
				files.add(drvFile);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			
		}
		return files;
	}

	
}
