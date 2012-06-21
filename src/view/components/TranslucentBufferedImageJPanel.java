package view.components;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.util.ResourceInfo;

/**
 * This class is an image-drawable that can have its own transparency value.
 * This will make the image partially see-through.
 * 
 * Note that it is not a good idea to change the imageAlpha value often at runtime,
 * since this requires reloading the normal image AND the translucent image,
 * making it quite time-consuming.
 *
 * @author Roland
 *
 */
public class TranslucentBufferedImageJPanel extends BufferedImageJPanel
{
	protected double imageAlpha = 1.0;
	
	// ************************
	// Constructor
	// ************************
	
	public TranslucentBufferedImageJPanel(ResourceInfo imageInfo) 
	{
		this(imageInfo, 1);
	}
	
	public TranslucentBufferedImageJPanel(ResourceInfo imageInfo, double imageAlpha) 
	{
		super(imageInfo);
		
		setImageAlpha(imageAlpha, false);
	}	
	
	// *****************************************
	// Translucency settings
	// *****************************************
	
	/**
	 * This method sets the new imageAlpha value for the transparency,
	 * and makes sure the new image with this transparency is loaded.
	 * 
	 * Note that this method also has to reload the original image
	 * and then load the translucent image, making it a very expensive 
	 * method to use at runtime. 
	 * 
	 * @param imageAlpha
	 */
	public void setImageAlpha(double alpha, boolean reload)
	{
		//Alpha must be within 0 and 1.
		if(alpha < 0) 		this.imageAlpha = 0;
		else if(alpha > 1) 	this.imageAlpha = 1;
		else				this.imageAlpha = alpha;
		
		//Load the original image.
		if(reload)
			this.setImage(this.imageInfo);
		
		//Load the translucent image based on the original image.
		if(alpha != 1.0)
			this.loadTranslucentImage();
	}
	
	/**
	 * This method loads in the translucent version of our image.
	 */
	private void loadTranslucentImage()
	{
		if(this.bufferedImage != null)
		{
	        // Create the image using the    
	        BufferedImage translucentImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TRANSLUCENT);   
	        // Get the images graphics   
	        Graphics2D g2d = translucentImage.createGraphics();   
	        // Set the Graphics composite to Alpha   
	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)imageAlpha));   
	        // Draw the LOADED img into the prepared reciver image   
	        g2d.drawImage(bufferedImage, null, 0, 0);   
	        // let go of all system resources in this Graphics   
	        g2d.dispose();   
	        
	        //Set our image to be the translucent image.
	        this.bufferedImage = translucentImage;
		}
	}

}
