package view.components;



import javax.swing.JPanel;

import content.img.ImageRef;

import model.util.ResourceInfo;
import model.util.Size;
import util.Output;

/**
 * This is the base of all the imageJPanels that can be used in the applications.
 * @author Roland
 *
 */
public abstract class AImageJPanel extends JPanel
{
	// ********************
	// Fields
	// ********************
	
	// The default image that is displayed if the given imageInfo is not valid.
	private final ResourceInfo defaultImageInfo = new ResourceInfo("imagenotfound.jpg", ImageRef.class);
	// The current imageInfo.
	protected ResourceInfo imageInfo;
	// This boolean tells us if we have tried to load the default image.
	protected boolean triedDefault;
	
	
	// ********************
	// Constructor
	// ********************
	
	/**
	 * This constructs the imagepanel with a custom image.
	 * 
	 */
	public AImageJPanel(ResourceInfo imageInfo)
	{
		super();
		
		this.setOpaque(false);
		this.setImage(imageInfo);
		
		this.triedDefault = false;
	}
	
	
	// ********************
	// Abstract
	// ********************
	
	/**
	 * This sets the image to the background of this jpanel.
	 * @param imageInfo
	 */
	public abstract void setImage(ResourceInfo imageInfo);
	
	
	// ********************
	// Default
	// ********************
	
	/**
	 * This method loads the default image for this panel.
	 */
	protected void loadDefaultImage()
	{
		if(!triedDefault)
		{
			this.triedDefault = true;
			setImage(defaultImageInfo);
		}
		else
			Output.show("The default image could not be loaded.");
	}
	
	// ********************
	// Size
	// ********************
	
	public void setSize(Size size)
	{
		this.setSize(size.w, size.h);
	}
}
