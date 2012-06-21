package view.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.util.ResourceInfo;
import util.Output;
import controller.contentloader.ImageLoader;

public class BufferedImageJPanel extends AImageJPanel
{
	// ********************
	// Fields
	// ********************

	// The current image to be drawn.
	protected BufferedImage bufferedImage;

	
	// ********************
	// Constructor
	// ********************

	/**
	 * This constructs the imagepanel with a custom image.
	 * 
	 * @param imageFile
	 */
	public BufferedImageJPanel(ResourceInfo imageInfo)
	{
		super(imageInfo);
	}
	
	// *************************
	// Draw
	// *************************
	
	/**
	 * This method overrides the super method, and can draw bufferedimages to the
	 * background of the panel.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (bufferedImage != null && this.isVisible())
		{
			g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	
	// ********************
	// Image
	// ********************
	
	/**
	 * This sets the imagefile to a new one. If the given imageInfo is incorrect,
	 * the default will be used.
	 */
	@Override
	public void setImage(ResourceInfo imageInfo)
	{
		if(imageInfo == null) //Perhaps intentionally put null.
		{
			loadDefaultImage();
			return;
		}
		
		try
		{
			BufferedImage bimage = ImageLoader.Instance().loadBufferedImage(imageInfo);
			
			if (bimage != null) // If loading the image worked correctly
			{
				this.imageInfo = imageInfo;
				this.bufferedImage = bimage;
			}
			else
				loadDefaultImage();
		}
		catch (Exception e)
		{
			Output.showException(e, "ImageJPanel.setImage(): ImageFile could not be loaded: " + imageInfo.toString());
					
			loadDefaultImage();
		}
	}
}
