package controller.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import applicationtest.TestConfig;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import view.test.SliderTextPanel;
import controller.tts.MainSpeaker;
import controller.tts.Speaker;

public class SliderTextController implements ActionListener, ChangeListener
{
	private SliderTextPanel panel;
	private Speaker speaker;
	
	public SliderTextController(SliderTextPanel panel)
	{
		super();
		
		this.panel = panel;
		
		Voice voice = VoiceManager.getInstance().getVoice(TestConfig.freeTTSSpeakerName);
		speaker = new Speaker(TestConfig.freeTTSSpeakerName, voice);
	}
	
	/**
	 * This method is called whenever an action (to a button) occurred.
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//Synthesize the text from the textarea.
		if(ae.getSource().equals(panel.getSynthesizeButton()))
		{
			String text = panel.getInsertedText();
			if(text.length() == 0)
				text = "No input";
			
			speaker.say(text);
		}
	}

	/**
	 * This method is called whenever a change (to a slider) occurred.
	 */
	@Override
	public void stateChanged(ChangeEvent ce)
	{
		JSlider source = (JSlider)ce.getSource();
		if (!source.getValueIsAdjusting()) 
		{
			//Change the pitch
			if(source.equals(panel.getPitchSlider()))
			{
				//Pitch runs from 0 - 500, and the slider does also.
				int pitch = source.getValue();
				speaker.setPitch(pitch);
			}
			//Change the pitch shift
			else if(source.equals(panel.getPitchShiftSlider()))
			{
				int value = source.getValue();
				
				//Pitch shift runs from 0 - 10 (for useful shifts). 
				//If we ever change the range of 0 - 10, then we also need to change it in the init of the slider.
				float pitchshift = 10 * (value - SliderTextPanel.PITCHSHIFT_MIN) / (float)(SliderTextPanel.PITCHSHIFT_MAX - SliderTextPanel.PITCHSHIFT_MIN);
				speaker.setPitchShift(pitchshift);
			}
			//Change the pitch range
			else if(source.equals(panel.getPitchRangeSlider()))
			{
				//Pitch range runs from 0 - 500, and the slider does also.
				int pitchrange = source.getValue();
				speaker.setPitchRange(pitchrange);
			}
			//Change the words per minute
			else if(source.equals(panel.getWordsPMSlider()))
			{
				//Rate runs from 30 - 300 (for useful rates), and the slider does also.
				int wordspm = source.getValue();
				speaker.setWordsPM(wordspm);
			}
			//Change the duration stretch
			else if(source.equals(panel.getDurStretchSlider()))
			{
				int value = source.getValue();

				//Duration stretch runs from 0 - 10 (for useful stretches anyway).
				//If we ever change the range of 0 - 10, then we also need to change it in the init of the slider.
				float durstretch = 10 * (value - SliderTextPanel.DURSTRETCH_MIN) / (float)(SliderTextPanel.DURSTRETCH_MAX - SliderTextPanel.DURSTRETCH_MIN);
				speaker.setDurationStretch(durstretch);
			}
			//Change the volume
			else if(source.equals(panel.getVolSlider()))
			{
				int value = source.getValue();
				
				//Volume runs from 0.0 - 1.0, but the slider is from 0 - 100.
				float volume = (value - SliderTextPanel.VOL_MIN) / (float)(SliderTextPanel.VOL_MAX - SliderTextPanel.VOL_MIN);
				speaker.setVolume(volume);
			}
			else
				throw new UnsupportedOperationException("Source unknown");
		}
	}

}
