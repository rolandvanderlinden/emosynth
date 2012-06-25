package controller.prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.tudelft.affectbutton.AffectButtonActionEvent;

import view.prototype.PrototypePanel;
import view.test.ButtonTextPanel;
import applicationtest.TestConfig;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import controller.tts.Speaker;

public class PrototypeController implements ActionListener
{
	private PrototypePanel panel;
	private Speaker speaker;
	
	public PrototypeController(PrototypePanel panel)
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
			
			//-1 pleasure is very low pitch
			//1 pleasure is higher pitch
			double ppercentage = (p + 1) / 2.0;
			float pitch = 80 + (float)(ppercentage * 100);

			//-1 arousal is very slow speech
			//1 arousal is faster speech
			double apercentage = (a + 1) / 2.0;
			float rate = 150 + (float)(apercentage * 100);
			
			//-1 dominance is very soft speech
			//1 dominance is louder speech
			double dpercentage = (d + 1) / 2.0;
			float volume = 0.7f + (float)(dpercentage * 0.3);
			
			panel.setPAD(p, a, d);
			speaker.setPitch(pitch);
			speaker.setWordsPM(rate);
			speaker.setVolume(volume);
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
