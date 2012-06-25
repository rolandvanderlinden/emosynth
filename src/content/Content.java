package content;

import java.awt.Font;

import content.img.ImageRef;

import model.util.ResourceInfo;

public class Content
{
	//Images
	public final static ResourceInfo background = new ResourceInfo("background.png", ImageRef.class);
	public final static ResourceInfo black = new ResourceInfo("black.png", ImageRef.class);
	
	
	//Fonts
	public final static Font smallFont = new Font("Arial", Font.PLAIN, 10);
	public final static Font mediumFont = new Font("Arial", Font.PLAIN, 14);
	public final static Font largeFont = new Font("Arial", Font.PLAIN, 18);
}
