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
	public void setPitch(int pitch)
	{
		if(voice != null)
			voice.setPitch(pitch);
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
