package applicationprototype;

import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.Output;
import view.prototype.BackgroundPanel;
import view.prototype.BackgroundPanel;

public class PrototypeApplet extends JApplet
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
		this.setSize(PrototypeConfig.appsize);

		//Insert the most basic background panel into the applet.
		this.rootpanel = new JPanel(null);
		this.rootpanel.setSize(PrototypeConfig.appsize);
		this.rootpanel.setBackground(Color.white);
		this.rootpanel.setLocation(0, 0);
		this.add(this.rootpanel);
	}
	
	/**
	 * This will insert the panels into the applet.
	 */
	private void insertPanels()
	{
		int width = this.rootpanel.getWidth();
		int height = this.rootpanel.getHeight();

		BackgroundPanel backgroundPanel = new BackgroundPanel();
		this.rootpanel.add(backgroundPanel);
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
