package controller.tts;

import com.sun.speech.freetts.Voice;

/**
 * This class represents a TTS speaker that can speak the text that is being given out loud.
 * @author Roland van der Linden
 *
 */
public class Speaker
{	
	private String name;
	private Voice voice;

	/**
	 * Construct a speaker with a voice.
	 * @param name
	 * @param voice
	 */
	public Speaker (String name, Voice voice)
	{
		super();
		
		this.name = name;
		this.voice = voice;
		
		init();
	}
	
	/**
	 * Initialize the speaker.
	 */
	private void init()
	{
		//Create a random name if the name is not correct.
		if(this.name == null || this.name.length() == 0)
			this.name = "Speaker" + (int)(Math.random() * Integer.MAX_VALUE);
		
		//Allocate the voice of the speaker.
		if(this.voice != null)
			this.voice.allocate();
	}
	
	/**
	 * This will make the speaker say multiple sentences in a row.
	 * @param sentences
	 */
	public void say(String[] sentences)
	{
		for(String sentence : sentences)
			this.say(sentence);
	}
	
	/**
	 * This will make the speaker say the given text out loud.
	 * @param text
	 */
	public void say(String text)
	{
		if(voice != null)
			voice.speak(text);
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}

	/**
	 * This will change the pitch of the speaker.
	 * @param pitch
	 */
	public void setPitch(float pitch)
	{
		if(voice != null)
			voice.setPitch(pitch);
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This will change the pitchshift of the speaker.
	 * @param pitchshift
	 */
	public void setPitchShift(float pitchshift)
	{
		if(voice != null)
			voice.setPitchShift(pitchshift);
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This will change the pitchrange of the speaker.
	 * @param pitchrange
	 */
	public void setPitchRange(float pitchrange)
	{
		if(voice != null)
			voice.setPitchRange(pitchrange);
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This will change the words per minute of the speaker.
	 * @param wordspm
	 */
	public void setWordsPM(float wordspm)
	{
		if(voice != null)
			voice.setRate(wordspm);
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This will change the duration stretch of the speaker
	 * @param durstretch
	 */
	public void setDurationStretch(float durstretch)
	{
		if(voice != null)
			voice.setDurationStretch(durstretch);
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This will change the volume of the speaker.
	 * @param volume
	 */
	public void setVolume(float volume)
	{
		if(voice != null)
			voice.setVolume(volume);
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This returns the pitch of the speaker.
	 * @return
	 */
	public float getPitch()
	{
		if(voice != null)
			return voice.getPitch();
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This returns the pitchshift of the speaker.
	 * @return
	 */
	public float getPitchShift()
	{
		if(voice != null)
			return voice.getPitchShift();
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This returns the pitchrange of the speaker.
	 * @return
	 */
	public float getPitchRange()
	{
		if(voice != null)
			return voice.getPitchRange();
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This returns the rate (words per minute) of the speaker.
	 * @return
	 */
	public float getWordsPM()
	{
		if(voice != null)
			return voice.getRate();
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This returns the duration stretch of the speaker.
	 * @return
	 */
	public float getDurationStretch()
	{
		if(voice != null)
			return voice.getDurationStretch();
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	/**
	 * This returns the volume of the speaker.
	 * @return
	 */
	public float getVolume()
	{
		if(voice != null)
			return voice.getVolume();
		else
			throw new UnsupportedOperationException("The voice of speaker '" + name + "' was not correctly initialized.");
	}
	
	public void dispose()
	{
		if(this.voice != null)
			this.voice.deallocate();
	}
	
	/**
	 * This returns the toString representation of this speaker.
	 */
	@Override
	public String toString()
	{
		return this.name;
	}
}
