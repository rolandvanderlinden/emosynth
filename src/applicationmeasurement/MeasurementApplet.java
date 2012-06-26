package applicationmeasurement;

import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JPanel;

import model.util.VectorF2;
import util.Output;
import view.base.BackgroundPanel;
import view.measurement.MeasurementPanel;

public class MeasurementApplet extends JApplet
{
	// **********************************************
	// Fields
	// **********************************************
	
	protected JPanel rootpanel;
	protected BackgroundPanel backgroundPanel;
	protected MeasurementPanel measurementPanel;
	
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
		this.setSize(MeasurementConfig.appsize);

		//Insert the most basic background panel into the applet.
		this.rootpanel = new JPanel(null);
		this.rootpanel.setSize(MeasurementConfig.appsize);
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
		
		//Create the prototypePanel
		VectorF2 psize = new VectorF2(0.8f * width, 0.8f * height);
		VectorF2 ppos = new VectorF2(0.1f * width, 0.1f * height);
		this.measurementPanel = new MeasurementPanel(psize);
		this.measurementPanel.setLocation((int)ppos.x, (int)ppos.y);
		this.rootpanel.add(measurementPanel);
		
		this.backgroundPanel = new BackgroundPanel(MeasurementConfig.appsize, MeasurementConfig.outerBorderSize);
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
