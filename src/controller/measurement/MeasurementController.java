package controller.measurement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import model.pad.PADSettings;
import model.speech.SpeechSettings;
import model.speech.SpeechToPAD;

import org.tudelft.affectbutton.AffectButton;
import org.tudelft.affectbutton.AffectButtonActionEvent;

import util.Output;
import view.measurement.MeasurementPanel;
import applicationmeasurement.MeasurementConfig;
import applicationtest.TestConfig;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import controller.io.FileIOController;
import controller.tts.Speaker;

public class MeasurementController implements ActionListener
{
	private MeasurementPanel panel;
	private Speaker affectiveSpeaker;
	private Speaker neutralSpeaker;

	private SpeechSettings speechSettings;
	private PADSettings padSettings;
	
	private Random random;
	private int testsSaved;
	private int testsTotal;
	
	public MeasurementController(MeasurementPanel panel)
	{
		super();
		
		this.panel = panel;
		
		this.random = new Random();
		
		//Initiate the speakers.
		Voice affectiveVoice = VoiceManager.getInstance().getVoice(TestConfig.freeTTSSpeakerName);
		affectiveSpeaker = new Speaker(TestConfig.freeTTSSpeakerName, affectiveVoice);
		Voice neutralVoice = VoiceManager.getInstance().getVoice(TestConfig.freeTTSSpeakerName);
		neutralSpeaker = new Speaker(TestConfig.freeTTSSpeakerName, neutralVoice);
		
		this.speechSettings = new SpeechSettings(0,0,0);
		this.padSettings = new PADSettings(0,0,0);
	}
	
	private void start()
	{
		setRandomAffectiveState();
		
		//Say that the new affective text will now be spoken.
		speakText(neutralSpeaker, "Started. Pay attention to the new affective state.");
		waitMS(MeasurementConfig.waitTimeForNextTest);
		//Speak with the new affective state.
		speakTextFromInputArea(affectiveSpeaker);
	}
	
	/**
	 * Save the conversion from speech to PAD to file.
	 */
	private void saveConversion()
	{
		FileIOController.Instance().writeToFile(new SpeechToPAD(this.speechSettings, this.padSettings));
	}
	
	/**
	 * Create a new random affective state for the speaker.
	 */
	private void setRandomAffectiveState()
	{
		//TODO correct random values.
		float pitch = (float)(50 + (random.nextDouble() * 250));
		float pitchrange = (float)(1 + (random.nextDouble() * 49));
		float wordspm = (float)(80 + (random.nextDouble() * 170));
		
		this.affectiveSpeaker.setPitch(pitch);
		this.affectiveSpeaker.setPitchRange(pitchrange);
		this.affectiveSpeaker.setWordsPM(wordspm);
		
		this.speechSettings = new SpeechSettings(pitch, pitchrange, wordspm);
	}
	
	/**
	 * Remove the saved selected state of the AB.
	 */
	private void resetSelectedAffectiveState()
	{
		panel.getButtonStateImagePanel().setBufferedImage(null);
	}
	
	/**
	 * Take the current state of the AB and paint it on another image.
	 */
	private void extractAffectButtonImage()
	{
		AffectButton ab = panel.getAffectButton();
		
		//Write the current state of the AB to a new image.
		BufferedImage bimage = new BufferedImage(ab.getWidth(), ab.getHeight(), BufferedImage.TYPE_INT_RGB);
		ab.paint(bimage.getGraphics());
		
		//Put the image in the panel that shows the AB-state.
		panel.getButtonStateImagePanel().setBufferedImage(bimage);
	}
	
	/**
	 * Let the given speaker say the given words.
	 * @param speaker
	 * @param text
	 */
	private void speakText(Speaker speaker, String text)
	{		
		if(speaker != null)
			speaker.say(text);
	}
	
	/**
	 * Let the given speaker say the words that are on the input area.
	 * @param speaker
	 */
	private void speakTextFromInputArea(Speaker speaker)
	{
		String text = panel.getInsertedText();
		if(text.length() == 0)
			text = "No input";
		
		if(speaker != null)
			speaker.say(text);
	}
	
	/**
	 * Make sure this thread waits the given amount of ms.
	 * @param ms
	 */
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
			//Extract the PAD values from the AB.
			AffectButtonActionEvent abae = (AffectButtonActionEvent)ae;
			this.padSettings = new PADSettings((float)abae.getPleasure(), (float)abae.getArousal(), (float)abae.getDominance());
			
			//Extract an image of the current state of the button.
			extractAffectButtonImage();
			
			//Make sure the user can now save & continue.
			panel.getContinueButton().setEnabled(true);
		}

		//Start the first test.
		else if(ae.getSource().equals(panel.getStartButton()))
		{
			panel.getStartButton().setVisible(false);
			panel.getStartButton().setEnabled(false);
			
			panel.getAffectButton().setEnabled(true);
			panel.getNeutralButton().setEnabled(true);
			panel.getRepeatButton().setEnabled(true);
			panel.getSkipButton().setEnabled(true);
			
			start();
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