package controller.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import model.speech.SpeechToPAD;
import util.Output;
import applicationmeasurement.MeasurementConfig;

public class FileIOController
{
	private static FileIOController instance;
	private Writer writer;
	
	private FileIOController()
	{
		try
		{
			this.writer = new BufferedWriter(new FileWriter(new File(MeasurementConfig.outputFilepath)));
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
	
	public void writeToFile(ArrayList<SpeechToPAD> stplist)
	{		
		try
		{
			for(SpeechToPAD stp : stplist)
				writer.write(stp.toString() + "\n");
			
			writer.flush();
		}
		catch (IOException e)
		{
			Output.showException(e);
		}
	}

}
