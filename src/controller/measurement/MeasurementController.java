package controller.measurement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.tudelft.affectbutton.AffectButtonActionEvent;

import view.measurement.MeasurementPanel;
import applicationtest.TestConfig;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import controller.tts.Speaker;

public class MeasurementController implements ActionListener
{
	private MeasurementPanel panel;
	private Speaker speaker;
	
	public MeasurementController(MeasurementPanel panel)
	{
		super();
		
		this.panel = panel;
		
		Voice voice = VoiceManager.getInstance().getVoice(TestConfig.freeTTSSpeakerName);
		speaker = new Speaker(TestConfig.freeTTSSpeakerName, voice);
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
			//TODO
		}
		//Synthesize the text from the textarea.
		else if(ae.getSource().equals(panel.getContinueButton()))
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