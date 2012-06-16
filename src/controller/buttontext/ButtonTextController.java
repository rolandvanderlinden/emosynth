package controller.buttontext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import org.tudelft.affectbutton.AffectButtonActionEvent;

import controller.tts.MainSpeaker;

import view.mainparts.ButtonTextPanel;

public class ButtonTextController implements ActionListener
{
	private ButtonTextPanel panel;
	
	public ButtonTextController(ButtonTextPanel panel)
	{
		super();
		
		this.panel = panel;
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
			MainSpeaker.Instance().say(text);
		}
	}
}
