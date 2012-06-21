package model.util;

/**
 * This class contains the info that is needed to load a resource.
 * @author Roland
 *
 */
public class ResourceInfo
{
	// This holds the name of the resource you wish to load.
	private String resourceName;
	// This should be the class from which the resource can be loaded.
	// This class should be in the same folder.
	private Class root;
	
	/**
	 * This creates a new resourceInfo.
	 * @param imageString
	 * @param root
	 */
	public ResourceInfo(String resourceName, Class root)
	{
		this.resourceName = resourceName;
		this.root = root;
	}
	
	/**
	 * This returns the class from which location the resource
	 * can be loaded.
	 * @return
	 */
	public Class getRoot()
	{
		return root;
	}
	
	/**
	 * This returns the name of the resource.
	 * @return
	 */
	public String getResourceName()
	{
		return resourceName;
	}
	
	/**
	 * This returns the string representation of this resourceInfo.
	 */
	@Override
	public String toString()
	{
		return "Resource: " + resourceName + ", rootClass: " + root.toString();
	}
}
