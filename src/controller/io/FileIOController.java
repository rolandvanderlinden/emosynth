package controller.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import model.speech.SpeechToPAD;
import util.Output;

public class FileIOController
{
	private final static String filepath = "output.txt";
	
	private static FileIOController instance;
	private Writer writer;
	
	private FileIOController()
	{
		try
		{
			this.writer = new BufferedWriter(new FileWriter(new File(filepath)));
		}
		catch(Exception e)
		{
			Output.showException(e);
			
			//Exit the system since printing to the file does not work anyway.
			System.exit(1);
		}
	}
	
	public static FileIOController Instance()
	{
		if(instance == null)
			instance = new FileIOController();
		
		return instance;
	}
	
	public void writeToFile(SpeechToPAD stp)
	{
		if(stp != null)
		{
			try
			{
				writer.write(stp.toString() + "\n");
				writer.flush();
			}
			catch (IOException e)
			{
				Output.showException(e);
			}
		}
	}
}
