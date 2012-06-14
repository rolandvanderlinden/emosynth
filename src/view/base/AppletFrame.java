package view.base;

import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 * This class is a normal JFrame which holds a reference to the contained applet,
 * so it can be notified when it is being destroyed.
 * @author Roland van der Linden
 *
 */
public class AppletFrame extends JFrame
{
	//A very ugly static reference to the applet, because it can otherwise not be accessed.
	public static JApplet applet;
	
	/**
	 * This constructs the AppletFrame, adding a windowClosingListener
	 * to the frame that makes sure that the applet is properly stopped before closing.
	 * @param appname
	 * @param applet
	 */
	public AppletFrame(String appname, JApplet applet) 
	{
		super(appname);
		
		//Add the ugly static reference.
		AppletFrame.applet = applet;
		
		this.addWindowListener(
				new java.awt.event.WindowAdapter() 
				{
					public void windowClosing(WindowEvent winEvt) 
					{
						//Stop the applet.
						if(AppletFrame.applet != null)
							AppletFrame.applet.stop();
		    	
						//Exit the application.
						System.exit(0); 
					}
				});
	}
}
