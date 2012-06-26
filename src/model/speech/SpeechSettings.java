package model.speech;

public class SpeechSettings
{
	public float pitch, pitchrange, wordspm;
	
	public SpeechSettings(float pitch, float pitchrange, float wordspm)
	{
		super();
		
		this.pitch = pitch;
		this.pitchrange = pitchrange;
		this.wordspm = wordspm;
	}
	
	public SpeechSettings clone()
	{
		return new SpeechSettings(pitch, pitchrange, wordspm);
	}
}
