package view.mainparts;

import java.awt.Color;

import util.Output;

public class SliderTextPanel extends AMainPanel
{
	public SliderTextPanel()
	{
		super();
		
		this.setBackground(Color.yellow);
		Output.show(this.getWidth() + "");
	}
	
	public void initialize(int width, int height)
	{
		Output.show("(" + width + "," + height + ")");
	}
}
