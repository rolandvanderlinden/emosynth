package applicationtest;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.base.AppletFrame;

/**
 * This class is the main entry point for the standalone application. 
 * It builds a frame around the base applet so that the applet can both be used for the browser as the standalone version.
 * Any code here executed here will not be executed by the applet.
 * 
 * @author Roland van der Linden
 *
 */
public class TestApplication
{
	/**
	 * This will start the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		TestApplet Tapplet = new TestApplet();
		buildFrameAroundApplet(Tapplet);
	}
	
	/**
	 * This method is used to put the applet in a normal java window,
	 * so that it can also be used as a standalone application.
	 * @param applet
	 * @return
	 */
	protected static JFrame buildFrameAroundApplet(TestApplet applet)
	{
		if(applet == null)
			throw new UnsupportedOperationException();		
		
		//We will put the applet in a window (jframe).
		AppletFrame frame = new AppletFrame(TestConfig.appname, applet);
		frame.add(applet);
		
		//We can now init and start the app.
		applet.init();
		applet.start();

		//We can now set the size of the frame based on the sizes
		//of the components in the applet.
		int bordersize = 8;
		int titelbarsize = 30;
		frame.setMinimumSize(new Dimension(applet.getWidth() + (2*bordersize), applet.getHeight() + bordersize + titelbarsize));
		//We put the frame in the middle of the screen.
		frame.setLocationRelativeTo(null);
		//And we show the window.
		frame.setVisible(true);
		
		return frame;
	}
}