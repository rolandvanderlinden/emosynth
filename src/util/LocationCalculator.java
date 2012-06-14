package util;

import model.util.VectorF2;

public class LocationCalculator 
{
	public enum LocationType
	{
		BEGIN, ONE_EIGHTH, ONE_QUARTER, ONE_THIRD, CENTER, TWO_THIRDS, THREE_QUARTERS, SEVEN_EIGHTHS, END,
	}
	

	public static VectorF2 calculateLocation(VectorF2 size, VectorF2 holdersize, LocationType xtype, LocationType ytype)
	{
		return calculateLocationWithMargins(size, holdersize, xtype, ytype, new VectorF2());
	}
	
	public static VectorF2 calculateLocation(VectorF2 size, VectorF2 holdersize, LocationType xtype, float ideal_y_ratio)
	{
		return calculateLocationWithMargins(size, holdersize, xtype, ideal_y_ratio, new VectorF2());
	}
	
	public static VectorF2 calculateLocation(VectorF2 size, VectorF2 holdersize, float ideal_x_ratio, LocationType ytype)
	{
		return calculateLocationWithMargins(size, holdersize, ideal_x_ratio, ytype, new VectorF2());
	}
	
	public static VectorF2 calculateLocation(VectorF2 size, VectorF2 holdersize, float ideal_x_ratio, float ideal_y_ratio)
	{
		return calculateLocationWithMargins(size, holdersize, ideal_x_ratio, ideal_y_ratio, new VectorF2());
	}
	
	// ***************************************
	// Calculate location with margins
	// ***************************************
	
	public static VectorF2 calculateLocationWithMargins(VectorF2 size, VectorF2 holdersize, float ideal_x_ratio, LocationType ytype, VectorF2 margins)
	{
		float y_ratio = 0;
		
		y_ratio = calculateYRatio(size.y, holdersize.y, ytype, margins.y);
		
		return calculateLocationWithMargins(size, holdersize, ideal_x_ratio, y_ratio, margins);
	}
	
	public static VectorF2 calculateLocationWithMargins(VectorF2 size, VectorF2 holdersize, LocationType xtype, float ideal_y_ratio, VectorF2 margins)
	{
		float x_ratio = 0;
		
		x_ratio = calculateXRatio(size.x, holdersize.x, xtype, margins.x);
		
		return calculateLocationWithMargins(size, holdersize, x_ratio, ideal_y_ratio, margins);
	}
	
	public static VectorF2 calculateLocationWithMargins(VectorF2 size, VectorF2 holdersize, LocationType xtype, LocationType ytype, VectorF2 margins)
	{
		float x_ratio = 0, y_ratio = 0;
		
		x_ratio = calculateXRatio(size.x, holdersize.x, xtype, margins.x);
		y_ratio = calculateYRatio(size.y, holdersize.y, ytype, margins.y);
		
		return calculateLocationWithMargins(size, holdersize, x_ratio, y_ratio, margins);
	}
	
	private static float calculateXRatio(float width, float holderwidth, LocationType xtype, float xmargin)
	{
		float result = 0;
		
		float halfwidth = width / 2f;
		float ratioDiff = halfwidth / holderwidth;
		
		switch(xtype)
		{
			case BEGIN:
				result = 0; //will be corrected.
				break;
			case END: 
				result = 1; //will be corrected.
				break;
			case CENTER:
				result = 0.5f - ratioDiff;
				break;
			
			case ONE_EIGHTH:
				result = 0.125f - ratioDiff;
				break;
			case ONE_QUARTER:
				result = 0.25f - ratioDiff;
				break;
			case ONE_THIRD:
				result = (1f / 3f) - ratioDiff;
				break;
			case TWO_THIRDS:
				result = (2f / 3f) - ratioDiff;
				break;
			case THREE_QUARTERS:
				result = 0.75f - ratioDiff;
				break;
			case SEVEN_EIGHTHS:
				result = 0.875f - ratioDiff;
				break;
			default: throw new UnsupportedOperationException();
		}
		
		return result;
	}
	
	private static float calculateYRatio(float height, float holderheight, LocationType ytype, float ymargin)
	{
		float result = 0;
		
		float halfheight = height / 2f;
		float ratioDiff = halfheight / holderheight;
		
		switch(ytype)
		{
			case BEGIN:
				result = 0; //will be corrected.
				break;
			case END: 
				result = 1; //will be corrected.
				break;
			case CENTER:
				result = 0.5f - ratioDiff;
				break;
			
			case ONE_EIGHTH:
				result = 0.125f - ratioDiff;
				break;
			case ONE_QUARTER:
				result = 0.25f - ratioDiff;
				break;
			case ONE_THIRD:
				result = (1f / 3f) - ratioDiff;
				break;
			case TWO_THIRDS:
				result = (2f / 3f) - ratioDiff;
				break;
			case THREE_QUARTERS:
				result = 0.75f - ratioDiff;
				break;
			case SEVEN_EIGHTHS:
				result = 0.875f - ratioDiff;
				break;
			default: throw new UnsupportedOperationException();
		}
		
		return result;
	}
	
	public static VectorF2 calculateLocationWithMargins(VectorF2 size, VectorF2 holdersize, float ideal_x_ratio, float ideal_y_ratio, VectorF2 margins)
	{
		VectorF2 result = new VectorF2();
		
		float idealX = ideal_x_ratio * holdersize.x;
		float idealY = ideal_y_ratio * holdersize.y;
		
		//x
		
		//too big for the holder
		if(size.x > holdersize.x) 
			result.x = -1 * ((size.x - holdersize.x) / 2f);
		//with margins too big for the holder
		else if(size.x + (2 * margins.x) > holdersize.x) 
			result.x = ((size.x + (2 * margins.x) - holdersize.x) / 2f);
		//ideal location must met margin requirements.
		else if(idealX < margins.x)
			result.x = margins.x;
		//ideal location not ideal for margins and size.
		else if(idealX + size.x + margins.x > holdersize.x) 
			result.x = holdersize.x - size.x - margins.x;
		//ideal location is fine.
		else 
			result.x = idealX;
		
		//y
		
		//too big for the holder
		if(size.y > holdersize.y) 
			result.y = -1 * ((size.y - holdersize.y) / 2f);
		//with margins too big for the holder
		else if(size.y + (2 * margins.y) > holdersize.y) 
			result.y = ((size.y + (2 * margins.y) - holdersize.y) / 2f);
		//ideal location must met margin requirements.
		else if(idealY < margins.y)
			result.y = margins.y;
		//ideal location not ideal for margins and size.
		else if(idealY + size.y + margins.y > holdersize.y) 
			result.y = holdersize.y - size.y - margins.y;
		//ideal location is fine.
		else 
			result.y = idealY;
		
		return result;
	}
}
