package controller.tts;

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
	public void setPitch(int pitch)
	{
		this.speaker.setPitch(pitch);
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
