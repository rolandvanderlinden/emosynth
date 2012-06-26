package model.speech;

public class SpeechSettings
{
	public float pitch, pitchrange, wordspm, volume;
	
	public SpeechSettings(float pitch, float pitchrange, float wordspm, float volume)
	{
		super();
		
		this.pitch = pitch;
		this.pitchrange = pitchrange;
		this.wordspm = wordspm;
		this.volume = volume;
	}
	
	public SpeechSettings clone()
	{
		return new SpeechSettings(pitch, pitchrange, wordspm, volume);
	}
}
