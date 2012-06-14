package controller.slidertext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.mainparts.SliderTextPanel;
import controller.tts.MainSpeaker;

public class SliderTextController implements ActionListener, ChangeListener
{
	private SliderTextPanel panel;
	
	public SliderTextController(SliderTextPanel panel)
	{
		super();
		
		this.panel = panel;
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
			MainSpeaker.Instance().say(text);
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
				int pitch = (int)source.getValue();
				MainSpeaker.Instance().setPitch(pitch);
			}
		}
	}

}
