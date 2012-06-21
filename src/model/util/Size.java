package model.util;

public class Size 
{
	
	
	public int w, h;
	
	public Size()
	{
		this(0, 0);
	}
	
	public Size(int w, int h)
	{
		this.w = w;
		this.h = h;
	}
	
	public int getWidth()
	{
		return w;
	}
	
	public int getHeight()
	{
		return h;
	}
	
	public String toString()
	{
		return "(" + w + "," + h + ")";
	}
}
