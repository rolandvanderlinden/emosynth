package application;

import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JPanel;

import util.Output;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import controller.tts.Speaker;

public class EmoSynthApplet extends JApplet
{
	// **********************************************
	// Fields
	// **********************************************
	
	public JPanel mainpanel;
	
	
	// **********************************************
	// init
	// **********************************************
	
	/**
	 * This method is called when the applet is being initialized.
	 */
	public void init()
	{
		try
		{
			Output.show();
			
			//Create the basic layout of the applet.
			this.createBasicLayout();
		}
		catch(Exception e)
		{
			Output.showException(e, "An error occured during applet initialization.");
		}
	}
	
	/**
	 * This creates the basic background layout for the applet.
	 */
	private void createBasicLayout()
	{
		//Set the correct size of the applet.
		this.setLayout(null);
		this.setSize(Config.appsize);

		//Insert the most basic background panel into the applet.
		this.mainpanel = new JPanel(null);
		this.mainpanel.setSize(Config.appsize);
		this.mainpanel.setBackground(Color.white);
		this.mainpanel.setLocation(0, 0);
		this.add(this.mainpanel);
	}
	
	
	// **********************************************
	// Start & Stop
	// **********************************************
	
	/**
	 * This method is called when the applet is started.
	 */
	public void start()
	{
		Output.show();
		
		//Test the FreeTTS text-to-speech capabilities.
		Voice voice = VoiceManager.getInstance().getVoice(Config.freeTTSSpeakerName);
		Speaker speaker = new Speaker(Config.freeTTSSpeakerName, voice);
		speaker.say("Hello! Welcome to the unfinished EmoSynth application.");
	}
	
	/**
	 * This method is called when the application is stopped.
	 */
	public void stop()
	{
		Output.show();
	}
}
