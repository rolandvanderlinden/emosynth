package controller.tts;

import util.Output;
import application.Config;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * This is the main singleton speaker of the application.
 * @author Roland van der Linden
 *
 */
public class MainSpeaker
{
	private static MainSpeaker instance;
	private Speaker speaker;
	
	private MainSpeaker()
	{
		Voice voice = VoiceManager.getInstance().getVoice(Config.freeTTSSpeakerName);
		speaker = new Speaker(Config.freeTTSSpeakerName, voice);
	}
	
	/**
	 * This returns the singleton instance of this class.
	 * @return
	 */
	public static MainSpeaker Instance()
	{
		if(instance == null)
			instance = new MainSpeaker();
		
		return instance;
	}
	
	/**
	 * Let the synthesizer speak the given text.
	 * @param text
	 */
	public void say(String text)
	{
		this.speaker.say(text);
	}
	
	/**
	 * Set the pitch of the speaker.
	 * @param pitch
	 */
	public void setPitch(float pitch)
	{
		this.speaker.setPitch(pitch);
		Output.show("Set pitch to " + pitch + ".");
	}

	/**
	 * Set the pitchshift of the speaker.
	 * @param pitchshift
	 */
	public void setPitchShift(float pitchshift)
	{
		this.speaker.setPitchShift(pitchshift);
		Output.show("Set pitchshift to " + pitchshift + ".");
	}
	/**
	 * Set the pitchrange of the speaker.
	 * @param pitchrange
	 */
	public void setPitchRange(float pitchrange)
	{
		this.speaker.setPitchRange(pitchrange);
		Output.show("Set pitchrange to " + pitchrange + ".");
	}
	/**
	 * Set the wordspm of the speaker.
	 * @param wordspm
	 */
	public void setWordsPM(float wordspm)
	{
		this.speaker.setWordsPM(wordspm);
		Output.show("Set rate (words per minute) to " + wordspm + ".");
	}
	/**
	 * Set the duration stretch of the speaker.
	 * @param durstretch
	 */
	public void setDurationStretch(float durstretch)
	{
		this.speaker.setDurationStretch(durstretch);
		Output.show("Set duration stretch to " + durstretch + ".");
	}
	/**
	 * Set the volume of the speaker.
	 * @param vol
	 */
	public void setVolume(float volume)
	{
		this.speaker.setVolume(volume);
		Output.show("Set volume to " + volume + ".");
	}
	
	/**
	 * Get the pitch of the speaker.
	 * @return
	 */
	public float getPitch()
	{
		return this.speaker.getPitch();
	}
	
	/**
	 * Get the pitchshift of the speaker.
	 * @return
	 */
	public float getPitchShift()
	{
		return this.speaker.getPitchShift();
	}
	
	/**
	 * Get the pitchrange of the speaker.
	 * @return
	 */
	public float getPitchRange()
	{
		return this.speaker.getPitchRange();
	}
	
	/**
	 * Get the rate (words per minute) of the speaker.
	 * @return
	 */
	public float getWordsPM()
	{
		return this.speaker.getWordsPM();
	}
	
	/**
	 * Get the duration stretch of the speaker.
	 * @return
	 */
	public float getDurationStretch()
	{
		return this.speaker.getDurationStretch();
	}
	
	/**
	 * Get the volume of the speaker.
	 * @return
	 */
	public float getVolume()
	{
		return this.speaker.getVolume();
	}
	
	/**
	 * This returns the actual speaker that is controlled by the singleton class.
	 * @return
	 */
	public Speaker getSpeaker()
	{
		return this.speaker;
	}
}
