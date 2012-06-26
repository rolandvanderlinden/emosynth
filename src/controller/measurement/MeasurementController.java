package controller.measurement;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import org.tudelft.affectbutton.AffectButton;
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
		panel.getButtonStateImagePanel().setBufferedImage(null);
	}
	
	private void extractAffectButtonImage()
	{
		AffectButton ab = panel.getAffectButton();
		Dimension oldABSize = ab.getSize();
		
		//Make sure the button is just as big as the panel in which the image needs to go (to reduce artefacts).
		ab.setSize(panel.getButtonStateImagePanel().getSize());
		//Write the current state of the ab to a new image.
		BufferedImage bimage = new BufferedImage(ab.getWidth(), ab.getHeight(), BufferedImage.TYPE_INT_RGB);
		ab.paint(bimage.getGraphics());
		
		//Put the ab back to its old size.
		ab.setSize(oldABSize);
		
		//Put the image in the panel that shows the abstate.
		panel.getButtonStateImagePanel().setBufferedImage(bimage);
	}
	
	private void speakText(Speaker speaker, String text)
	{		
		if(speaker != null)
			speaker.say(text);
	}
	
	private void speakTextFromInputArea(Speaker speaker)
	{
		String text = panel.getInsertedText();
		if(text.length() == 0)
			text = "No input";
		
		if(speaker != null)
			speaker.say(text);
	}
	
	private void waitMS(long ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			Output.showException(e);
		}
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
			
			extractAffectButtonImage();
			
			//Make sure the user can now save & continue.
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
			
			//Say that the new affective text will now be spoken.
			speakText(neutralSpeaker, "Saved. Pay attention to the new affective state.");
			waitMS(MeasurementConfig.waitTimeForNextTest);
			//Speak with the new affective state.
			speakTextFromInputArea(affectiveSpeaker);
		}
		
		//Repeat the affective state of the speaker.
		else if(ae.getSource().equals(panel.getRepeatButton()))
		{
			//Say that the affective state is repeated.
			speakText(neutralSpeaker, "Repetition.");
			speakTextFromInputArea(affectiveSpeaker);
		}
		
		//Use the speaker that has a neutral voice.
		else if(ae.getSource().equals(panel.getNeutralButton()))
		{
			//Say that the neutral voice will be used.
			speakText(neutralSpeaker, "Neutral.");
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
			
			//Increase the number of total tests
			this.testsTotal++;
			panel.setTestsDone(testsSaved, testsTotal);
			
			//Make sure the user needs to select an affective state before continuing.
			panel.getContinueButton().setEnabled(false);
			
			//Say that the new affective text will now be spoken.
			speakText(neutralSpeaker, "Skipped. Pay attention to the new affective state.");
			waitMS(MeasurementConfig.waitTimeForNextTest);
			//Speak with the new affective state.
			speakTextFromInputArea(affectiveSpeaker);
		}
		
		//Button action undefined.
		else
			throw new UnsupportedOperationException();
		
		//Revalidate and repaint the panel.
		panel.revalidate();
		panel.repaint();
	}
	
	public Speaker getAffectiveSpeaker()
	{
		return this.affectiveSpeaker;
	}
}