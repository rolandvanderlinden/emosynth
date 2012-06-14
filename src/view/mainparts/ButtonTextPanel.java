package view.mainparts;

import java.awt.Color;

import util.Output;

public class ButtonTextPanel extends AMainPanel
{
	public ButtonTextPanel()
	{
		super();
		
		this.setBackground(Color.pink);
	}

	@Override
	public void initialize(int width, int height)
	{
		Output.show("(" + width + "," + height + ")");
	}
}
