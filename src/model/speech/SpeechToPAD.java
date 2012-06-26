package model.speech;

import model.pad.PADSettings;

public class SpeechToPAD
{
	protected SpeechSettings speechSettings;
	protected PADSettings padSettings;
	
	public SpeechToPAD(SpeechSettings speechSettings, PADSettings padSettings)
	{
		super();
		
		this.speechSettings = speechSettings;
		this.padSettings = padSettings;
	}
	
	@Override
	public String toString()
	{
		return speechSettings.pitch + ", " + speechSettings.pitchrange + ", " + speechSettings.wordspm + ", "
				+ padSettings.p + ", " + padSettings.a + ", " + padSettings.d + ";";
	}
}
