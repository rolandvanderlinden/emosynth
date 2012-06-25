package controller.measurement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.tudelft.affectbutton.AffectButtonActionEvent;

import util.Output;
import view.measurement.MeasurementPanel;
import applicationmeasurement.MeasurementConfig;
import applicationtest.TestConfig;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import controller.tts.Speaker;

public class MeasurementController implements ActionListener
{
	private MeasurementPanel panel;
	private Speaker affectiveSpeaker;
	private Speaker neutralSpeaker;
	
	private double currentP, currentA, currentD;
	private int testsSaved;
	private int testsTotal;
	
	public MeasurementController(MeasurementPanel panel)
	{
		super();
		
		this.panel = panel;
		
		Voice affectiveVoice = VoiceManager.getInstance().getVoice(TestConfig.freeTTSSpeakerName);
		affectiveSpeaker = new Speaker(TestConfig.freeTTSSpeakerName, affectiveVoice);
		Voice neutralVoice = VoiceManager.getInstance().getVoice(TestConfig.freeTTSSpeakerName);
		neutralSpeaker = new Speaker(TestConfig.freeTTSSpeakerName, neutralVoice);
	}
	
	private void saveConversion()
	{
		
	}
	
	private void setRandomAffectiveState()
	{
		//TODO
	}
	
	private void resetSelectedAffectiveState()
	{
		//TODO
	}
	
	private void speakTextFromInputArea(Speaker speaker)
	{
		String text = panel.getInsertedText();
		if(text.length() == 0)
			text = "No input";
		
		if(speaker != null)
			speaker.say(text);
	}

	/**
	 * This method is called when an action occurred (in this case to a button).
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//Get the PAD values from the affectbutton, and make a copy of the current state of the affectbutton.
		//Make sure the continuebutton is turned on.
		if(ae.getSource().equals(panel.getAffectButton()))
		{
			AffectButtonActionEvent abae = (AffectButtonActionEvent)ae;
			currentP = abae.getPleasure();
			currentA = abae.getArousal();
			currentD = abae.getDominance();
			//TODO
			
			//Make sure the user can now continue.
			panel.getContinueButton().setEnabled(true);
		}

		//Save the conversion and continue to the next sample.
		//Turn off the continuebutton.
		else if(ae.getSource().equals(panel.getContinueButton()))
		{
			//Save the conversion to the file.
			saveConversion();
			//Set a new random affective state.
			setRandomAffectiveState();
			//Reset affectbutton & selected affective state.
			resetSelectedAffectiveState();
			
			//Increase the number of tests selected and the total
			this.testsSaved++;
			this.testsTotal++;
			panel.setTestsDone(testsSaved, testsTotal);
			
			//Make sure the user needs to select an affective state before continuing.
			panel.getContinueButton().setEnabled(false);
			
			//Revalidate and repaint the panel.
			panel.revalidate();
			panel.repaint();
			
			try
			{
				Thread.sleep(MeasurementConfig.waitTimeForNextTest);
			}
			catch (InterruptedException e)
			{
				Output.showException(e);
			}
			
			//Speak with the new affective state.
			speakTextFromInputArea(affectiveSpeaker);
		}
		
		//Repeat the affective state of the speaker.
		else if(ae.getSource().equals(panel.getRepeatButton()))
		{
			speakTextFromInputArea(affectiveSpeaker);
		}
		
		//Use the speaker that has a neutral voice.
		else if(ae.getSource().equals(panel.getNeutralButton()))
		{
			speakTextFromInputArea(neutralSpeaker);
		}
		
		//Start the next sample without saving.
		//Turn off the continuebutton.
		else if(ae.getSource().equals(panel.getSkipButton()))
		{
			//Set a new random affective state.
			setRandomAffectiveState();
			//Reset affectbutton & selected affective state.
			resetSelectedAffectiveState();
			
			//Increase the number of tests selected and the total
			this.testsTotal++;
			panel.setTestsDone(testsSaved, testsTotal);
			
			//Make sure the user needs to select an affective state before continuing.
			panel.getContinueButton().setEnabled(false);
			
			//Revalidate and repaint the panel.
			panel.revalidate();
			panel.repaint();
			
			try
			{
				Thread.sleep(MeasurementConfig.waitTimeForNextTest);
			}
			catch (InterruptedException e)
			{
				Output.showException(e);
			}
			
			//Speak with the new affective state.
			speakTextFromInputArea(affectiveSpeaker);
		}
		
		//Button action undefined.
		else
			throw new UnsupportedOperationException();
	}
	
	public Speaker getAffectiveSpeaker()
	{
		return this.affectiveSpeaker;
	}
}