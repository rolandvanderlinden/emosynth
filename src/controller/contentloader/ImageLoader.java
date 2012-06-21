package controller.contentloader;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.util.ResourceInfo;
import util.Output;

/**
 * This class can load images from file. Singleton pattern used.
 * 
 * @author Roland
 * 
 */
public class ImageLoader
{
	// The instance of this singleton imageloader.
	private static ImageLoader instance;

	/**
	 * This creates the singleton.
	 */
	private ImageLoader()
	{

	}

	public static ImageLoader Instance()
	{
		if (instance == null) 
			instance = new ImageLoader();

		return instance;
	}

	/**
	 * This loads an image from file if it can be found.
	 * 
	 * @param img
	 * @return
	 */
	public Image loadImage(ResourceInfo imageInfo)
	{
		Image result = null;

		try
		{
			ImageIcon imageIcon = loadImageIcon(imageInfo);
			result = imageIcon.getImage();
		}
		catch (Exception e)
		{
			String ownMessage = "Load image failure: " + imageInfo.toString() + ".\n";
			Output.showException(e, ownMessage);
		}

		return result;
	}

	/**
	 * This loads an imageicon from file if it can be found.
	 * 
	 * @param img
	 * @return
	 */
	public ImageIcon loadImageIcon(ResourceInfo imageInfo)
	{
		ImageIcon result = null;

		try
		{
			result = new ImageIcon(imageInfo.getRoot().getResource(imageInfo.getResourceName()));
		}
		catch (Exception e)
		{
			String ownMessage = "Load imageicon failure: " + imageInfo.toString() + ".\n";
			Output.showException(e, ownMessage);
		}

		return result;
	}
	
	/**
	 * This loads an imageicon from file if it can be found.
	 * 
	 * @param img
	 * @return
	 */
	public BufferedInputStream loadBufferedInputStream(ResourceInfo imageInfo)
	{
		BufferedInputStream result = null;

		try
		{
			InputStream temp = imageInfo.getRoot().getResourceAsStream(imageInfo.getResourceName());
			
			if(temp instanceof BufferedInputStream)
				result = (BufferedInputStream)temp;
			else
				result = new BufferedInputStream(temp);
		}
		catch (Exception e)
		{
			String ownMessage = "Load bufferedinputstream failure: " + imageInfo.toString() + ".\n";
			Output.showException(e, ownMessage);
		}

		return result;
	}
	
	public BufferedImage loadBufferedImage(ResourceInfo imageInfo)
	{
		BufferedImage result = null;

		try
		{
			result = ImageIO.read(loadBufferedInputStream(imageInfo));
		}
		catch (Exception e)
		{
			String ownMessage = "Load bufferedImage failure: " + imageInfo.toString() + ".\n";
			Output.showException(e, ownMessage);	
		}

		return result;
	}
}
