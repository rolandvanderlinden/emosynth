package controller.buttontext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.tudelft.affectbutton.AffectButtonActionEvent;

import view.mainparts.ButtonTextPanel;
import application.Config;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import controller.tts.MainSpeaker;
import controller.tts.Speaker;

public class ButtonTextController implements ActionListener
{
	private ButtonTextPanel panel;
	private Speaker speaker;
	
	public ButtonTextController(ButtonTextPanel panel)
	{
		super();
		
		this.panel = panel;
		
		Voice voice = VoiceManager.getInstance().getVoice(Config.freeTTSSpeakerName);
		speaker = new Speaker(Config.freeTTSSpeakerName, voice);
	}

	/**
	 * This method is called when an action occurred (in this case to a button).
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//Get the PAD values from the affectbutton.
		if(ae.getSource().equals(panel.getAffectButton()))
		{
			AffectButtonActionEvent abae = (AffectButtonActionEvent)ae;
			double p = abae.getPleasure();
			double a = abae.getArousal();
			double d = abae.getDominance();
			
			panel.setPAD(p, a, d);
		}
		//Synthesize the text from the textarea.
		else if(ae.getSource().equals(panel.getSynthesizeButton()))
		{
			String text = panel.getInsertedText();
			if(text.length() == 0)
				text = "No input";
			
			speaker.say(text);
		}
	}
	
	public Speaker getSpeaker()
	{
		return this.speaker;
	}
}
