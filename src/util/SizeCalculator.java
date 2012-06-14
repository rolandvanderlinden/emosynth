package util;

import model.util.VectorF2;

public class SizeCalculator 
{
	// *************************************************
	// Helper methods
	// *************************************************
	
	private static VectorF2 checkMaxsize(VectorF2 size, VectorF2 maxsize)
	{
		VectorF2 result = size;
		
		if(result.x > maxsize.x)
			result.x = maxsize.x;
		if(result.y > maxsize.y)
			result.y = maxsize.y;
		
		return result;
	}
	
	private static VectorF2 forceWidthHeightRatio(VectorF2 size, float forced_wh_ratio)
	{
		VectorF2 result = size;
		
		if(forced_wh_ratio == 0) //No division by zero.
			return result;
		
		//We need to keep the ratio forced_wh_ratio. We may not increase the height or width, only decrease it.
		//If the current ratio is larger, than the width is too large (w/h). 
		//If the current ratio is lower, than the height is too large (w/h).
		//We use the rule '(current_w / current_h) = forced_wh_ratio' to find the used values.
		float current_wh_ratio = size.x / size.y; //TODO zero division ?
		if(current_wh_ratio > forced_wh_ratio)
			result.x = size.y * forced_wh_ratio;
		else if(current_wh_ratio < forced_wh_ratio)
			result.y = size.x / forced_wh_ratio;
		
		return result;
	}
	
	
	
	// ******************************************************
	// Accessible size calculation
	// ******************************************************
	
	/**
	 * Calculate the size based on the allowed size, and the requested size.
	 * @param requestsize
	 * @param holdersize
	 * @return
	 */
	public static VectorF2 calculateSize(VectorF2 requestsize, VectorF2 holdersize)
	{
		return calculateSizeWithMargins(requestsize, holdersize, new VectorF2());
	}
	
	/**
	 * Calculate the size based on the allowed size, the requested size, whilst making sure the wh-ratio is maintained.
	 * @param requestsize
	 * @param holdersize
	 * @param forced_wh_ratio
	 * @return
	 */
	public static VectorF2 calculateSize(VectorF2 requestsize, VectorF2 holdersize, float forced_wh_ratio)
	{
		return calculateSizeWithMargins(requestsize, holdersize, forced_wh_ratio, new VectorF2());
	}
	
	/**
	 * Calculate the size to be used based on the ideal width and height ratio, but only if the holdersize allows it.
	 * 
	 * @param minsize
	 * @param holdersize
	 * @param ideal_w_ratio
	 * @param ideal_h_ratio
	 * @return
	 */
	public static VectorF2 calculateSize(VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio)
	{
		return calculateSizeWithMargins(holdersize, ideal_w_ratio, ideal_h_ratio, new VectorF2());
	}
	
	/**
	 * Calculate the size to be sued on the ideal width and height ratios, but only if the holdersize allows it.
	 * If the holder does change the requested size, make sure the wh-ratio is maintained.
	 * @param holdersize
	 * @param ideal_w_ratio
	 * @param ideal_h_ratio
	 * @param forced_wh_ratio
	 * @return
	 */
	public static VectorF2 calculateSize(VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio, float forced_wh_ratio)
	{
		return calculateSizeWithMargins(holdersize, ideal_w_ratio, ideal_h_ratio, forced_wh_ratio, new VectorF2());
	}
	
	/**
	 * Calculate the size to be used based on the ideal width and height, but taking into account that the 
	 * minimumsize should be met, but only if the holdersize allows it.
	 * 
	 * @param minsize
	 * @param holdersize
	 * @param ideal_w_ratio
	 * @param ideal_h_ratio
	 * @return
	 */
	public static VectorF2 calculateSize(VectorF2 minsize, VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio)
	{
		return calculateSizeWithMargins(minsize, holdersize, ideal_w_ratio, ideal_h_ratio, new VectorF2());
	}
	
	/**
	 * Calculate the size to be used based on the ideal width and height ratio, but taking into account that the 
	 * minimumsize should be met, but only if the holdersize allows it. Note that if the ideal size cannot be used,
	 * we will still maintain the wh-ratio.
	 * @param minsize
	 * @param holdersize
	 * @param ideal_w_ratio
	 * @param ideal_h_ratio
	 * @param forced_wh_ratio
	 * @return
	 */
	public static VectorF2 calculateSize(VectorF2 minsize, VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio, float forced_wh_ratio)
	{
		return calculateSizeWithMargins(minsize, holdersize, ideal_w_ratio, ideal_h_ratio, forced_wh_ratio, new VectorF2());
	}
	
	/**
	 * Calculate the size to be used based on the ideal width and height ratio, but taking account that the 
	 * minimumsize and maximumsize should be met, but only if the holdersize allows it.
	 * 
	 * @param minsize
	 * @param holdersize
	 * @param ideal_w_ratio
	 * @param ideal_h_ratio
	 * @return
	 */
	public static VectorF2 calculateSize(VectorF2 minsize, VectorF2 maxsize, VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio)
	{
		return calculateSizeWithMargins(minsize, maxsize, holdersize, ideal_w_ratio, ideal_h_ratio, new VectorF2());
	}
	
	/**
	 * Calculate the size to be used based on the ideal width and height ratio, but taking account that the 
	 * minimumsize and maximumsize should be met, but only if the holdersize allows it.
	 * 
	 * Note that if the requested size is not available, we still need to force the wh-ratio.
	 * 
	 * @param minsize
	 * @param holdersize
	 * @param ideal_w_ratio
	 * @param ideal_h_ratio
	 * @return
	 */
	public static VectorF2 calculateSize(VectorF2 minsize, VectorF2 maxsize, VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio, float forced_wh_ratio)
	{
		return calculateSizeWithMargins(minsize, maxsize, holdersize, ideal_w_ratio, ideal_h_ratio, forced_wh_ratio, new VectorF2());
	}
	
	
	
	// *************************************************************
	// Calculate size with margins
	// *************************************************************
	
	/**
	 * Calculate the size based on the allowed size, and the requested size. Note that this
	 * method allows the user to also specify some margins to be applied.
	 * @param requestsize
	 * @param holdersize
	 * @return
	 */
	public static VectorF2 calculateSizeWithMargins(VectorF2 requestsize, VectorF2 holdersize, VectorF2 margins)
	{
		VectorF2 result = new VectorF2();
		
		//width
		if(requestsize.x + (2 * margins.x) > holdersize.x)
			result.x = holdersize.x - (2 * margins.x);
		else
			result.x = requestsize.x;
		
		//height
		if(requestsize.y + (2 * margins.y)> holdersize.y)
			result.y = holdersize.y - (2 * margins.y);
		else
			result.y = requestsize.y;			
		
		return result;
	}
	
	
	/**
	 * Calculate the size based on the allowed size, and the requested size. Note that this
	 * method allows the user to also specify some margins to be applied.
	 * 
	 * After restrictions, we also make sure the wh-ratio is maintained.
	 * @param requestsize
	 * @param holdersize
	 * @return
	 */
	public static VectorF2 calculateSizeWithMargins(VectorF2 requestsize, VectorF2 holdersize, float forced_wh_ratio, VectorF2 margins)
	{
		VectorF2 result = calculateSizeWithMargins(requestsize, holdersize, margins);
		result = forceWidthHeightRatio(result, forced_wh_ratio);
		
		return result;
	}
	
	public static VectorF2 calculateSizeWithMargins(VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio, VectorF2 margins)
	{
		return calculateSizeWithMargins(new VectorF2(), holdersize, ideal_w_ratio, ideal_h_ratio, margins);
	}
	
	public static VectorF2 calculateSizeWithMargins(VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio, float forced_wh_ratio, VectorF2 margins)
	{
		return calculateSizeWithMargins(new VectorF2(), holdersize, ideal_w_ratio, ideal_h_ratio, margins);
	}
	
	/**
	 * Calculate the size to be used based on the ideal width and height, but taking account that the 
	 * minimumsize should be met, but only if the holdersize allows it.
	 * 
	 * @param minsize
	 * @param holdersize
	 * @param ideal_w_ratio
	 * @param ideal_h_ratio
	 * @return
	 */
	public static VectorF2 calculateSizeWithMargins(VectorF2 minsize, VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio, VectorF2 margins)
	{
		VectorF2 result = new VectorF2();
		
		float idealWidth = ideal_w_ratio * holdersize.x;
		float idealHeight = ideal_h_ratio * holdersize.y;
		
		//width
		if(idealWidth + (2 * margins.x) > holdersize.x)
		{
			result.x = holdersize.x - (2 * margins.x);
		}
		else if(idealWidth < minsize.x)
		{
			if(minsize.x + (2 * margins.x) > holdersize.x)
				result.x = holdersize.x - (2 * margins.x);
			else
				result.x = minsize.x;
		}
		else
			result.x = idealWidth;			
		
		//height
		if(idealHeight + (2 * margins.y) > holdersize.y)
		{
			result.y = holdersize.y - (2 * margins.y);
		}
		else if(idealHeight < minsize.y)
		{
			if(minsize.y + (2 * margins.y) > holdersize.y)
				result.y = holdersize.y - (2 * margins.y);
			else
				result.y = minsize.y;
		}
		else
			result.y = idealHeight;			
		
		return result;
	}
	
	public static VectorF2 calculateSizeWithMargins(VectorF2 minsize, VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio, float forced_wh_ratio, VectorF2 margins)
	{
		VectorF2 result = calculateSizeWithMargins(minsize, holdersize, ideal_w_ratio, ideal_h_ratio, margins);
		result = forceWidthHeightRatio(result, forced_wh_ratio);
		
		return result;
	}
	
	/**
	 * Calculate the size to be used based on the ideal width and height, but taking account that the 
	 * minimumsize and maximumsize should be met, but only if the holdersize allows it.
	 * 
	 * @param minsize
	 * @param holdersize
	 * @param ideal_w_ratio
	 * @param ideal_h_ratio
	 * @return
	 */
	public static VectorF2 calculateSizeWithMargins(VectorF2 minsize, VectorF2 maxsize, VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio, VectorF2 margins)
	{
		VectorF2 result = calculateSizeWithMargins(minsize, holdersize, ideal_w_ratio, ideal_h_ratio, margins);
		result = checkMaxsize(result, maxsize);
		
		return result;
	}
	
	public static VectorF2 calculateSizeWithMargins(VectorF2 minsize, VectorF2 maxsize, VectorF2 holdersize, float ideal_w_ratio, float ideal_h_ratio, float forced_wh_ratio, VectorF2 margins)
	{
		VectorF2 result = calculateSizeWithMargins(minsize, holdersize, ideal_w_ratio, ideal_h_ratio, margins);
		result = checkMaxsize(result, maxsize);
		result = forceWidthHeightRatio(result, forced_wh_ratio);
		
		return result;
	}
}
