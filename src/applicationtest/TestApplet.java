package applicationtest;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JPanel;

import util.Output;
import view.test.AMainPanel;
import view.test.ButtonTextPanel;
import view.test.SliderTextPanel;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import controller.tts.Speaker;

public class TestApplet extends JApplet
{
	// **********************************************
	// Fields
	// **********************************************
	
	public JPanel rootpanel;
	
	
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
			
			//Insert the panels that allow TTS interaction into the applet.
			this.insertPanels();
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
		this.setSize(TestConfig.appsize);

		//Insert the most basic background panel into the applet.
		this.rootpanel = new JPanel(null);
		this.rootpanel.setSize(TestConfig.appsize);
		this.rootpanel.setBackground(Color.white);
		this.rootpanel.setLocation(0, 0);
		this.add(this.rootpanel);
	}
	
	/**
	 * This will insert the panels into the applet.
	 */
	private void insertPanels()
	{
		//Insert panels hardcoded, and then distribute over the applet.
		ArrayList<AMainPanel> panels = new ArrayList<AMainPanel>();
		panels.add(new SliderTextPanel());
		panels.add(new ButtonTextPanel());

		//Initialize the panels with their sizes and insert them in a horizontal array.
		int combinedUsedWidth = 0;
		for(int i = 0; i < panels.size(); i++)
		{
			//Calculate the size this panel is allowed to take based on its predecessors and the total size.
			int panelsLeft = panels.size() - i;
			int width = (int)((this.rootpanel.getWidth() - combinedUsedWidth) / (double)panelsLeft);
			int height = this.rootpanel.getHeight();
						
			//Set the size and location of the panel, and allow its own intialization before insertion.
			AMainPanel amp = panels.get(i);
			amp.setSize(width, height);
			amp.setLocation(combinedUsedWidth, 0);
			amp.initialize(width, height);
			this.rootpanel.add(amp);
			
			combinedUsedWidth += width;
		}
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
	}
	
	/**
	 * This method is called when the application is stopped.
	 */
	public void stop()
	{
		Output.show();
	}
}
