package view.test;

import javax.swing.JPanel;

public abstract class AMainPanel extends JPanel
{
	public AMainPanel()
	{
		super();
	}
	
	public abstract void initialize(int width, int height);
}
