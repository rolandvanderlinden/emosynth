package util;

import java.awt.Component;

import model.util.VectorF2;

public class ComponentUtil
{
	public static void setComponentBounds(Component component, VectorF2 size, VectorF2 location)
	{
		component.setSize((int)size.x, (int)size.y);
		component.setLocation((int)location.x, (int)location.y);
	}
}
