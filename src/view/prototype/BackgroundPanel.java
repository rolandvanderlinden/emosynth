package view.prototype;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import content.Content;

import model.util.VectorF2;
import view.components.TranslucentBufferedImageJPanel;
import applicationprototype.PrototypeConfig;

public class BackgroundPanel extends JPanel
{
	// **********************************************
	// Fields
	// **********************************************
	
	private TranslucentBufferedImageJPanel fullbackground, leftPanel, rightPanel, upPanel, lowPanel, controlsPanel;
	private PrototypePanel prototypePanel;
	private JPanel leborder, uborder, rborder, loborder, lebar, ubar, rbar, lobar;
	private JLabel createdLabel;
	
	// **********************************************
	// Constructor & init
	// **********************************************
	
	public BackgroundPanel()
	{
		super();
		
		this.init();
	}
	
	private void init()
	{
		//Create this screen itself.
		this.setLayout(null);
		this.setSize(PrototypeConfig.appsize);
		this.setBackground(Color.white);
		this.setOpaque(false);
		
		//Define the sizes that we'll use for the gui.
		int leftsize = 16;
		int rightsize = 16;
		int topsize = 80;
		int bottomsize = 130;
		int bordersize = PrototypeConfig.outerBorderSize;
		int width = this.getWidth();
		int height = this.getHeight();
	
		//Create borders
		this.leborder = new JPanel();
		this.rborder = new JPanel();
		this.uborder = new JPanel();
		this.loborder = new JPanel();
		this.leborder.setSize(bordersize, height);
		this.rborder.setSize(bordersize, height);
		this.uborder.setSize(width, bordersize);
		this.loborder.setSize(width, bordersize);
		this.leborder.setLocation(0, 0);
		this.uborder.setLocation(0,0);
		this.rborder.setLocation(width - bordersize, 0);
		this.loborder.setLocation(0, height - bordersize);
		this.leborder.setBackground(Color.black);
		this.uborder.setBackground(Color.black);
		this.rborder.setBackground(Color.black);
		this.loborder.setBackground(Color.black);
		this.add(this.leborder);
		this.add(this.uborder);
		this.add(this.rborder);
		this.add(this.loborder);
		
		//Create bars
		this.lebar = new JPanel();
		this.rbar = new JPanel();
		this.ubar = new JPanel();
		this.lobar = new JPanel();
		this.lebar.setSize(bordersize, height - topsize - bottomsize);
		this.rbar.setSize(bordersize, height - topsize - bottomsize);
		this.ubar.setSize(width - leftsize - rightsize, bordersize);
		this.lobar.setSize(width - leftsize - rightsize, bordersize);
		this.lebar.setLocation(leftsize, topsize);
		this.ubar.setLocation(leftsize, topsize);
		this.rbar.setLocation(width - rightsize - bordersize, topsize);
		this.lobar.setLocation(leftsize, height - bottomsize - bordersize);
		this.lebar.setBackground(Color.black);
		this.ubar.setBackground(Color.black);
		this.rbar.setBackground(Color.black);
		this.lobar.setBackground(Color.black);
		this.add(this.lebar);
		this.add(this.ubar);
		this.add(this.rbar);
		this.add(this.lobar);

		//Create the controls panel
		this.controlsPanel = new TranslucentBufferedImageJPanel(Content.black, 0);
		int leftrightdiff = leftsize;
		int topdiff = 30;
		int bottomdiff = 10;
		this.controlsPanel.setSize(width - (2*(bordersize + leftrightdiff)), bottomsize - (topdiff + bottomdiff));
		this.controlsPanel.setLocation(bordersize + leftrightdiff, height - bordersize - bottomsize + topdiff);
		this.controlsPanel.setLayout(null);
		this.add(controlsPanel);
		
		//Create labels on the toppanel.
		this.createdLabel = new JLabel("Created by Irene Renkens, Aurimas Bavarskis, Roland van der Linden");
		this.createdLabel.setHorizontalAlignment(JLabel.CENTER);
		this.createdLabel.setVerticalAlignment(JLabel.TOP);
		this.createdLabel.setSize(250, 30);
		this.createdLabel.setLocation(900, 5);
		this.createdLabel.setFont(Content.smallFont);
		this.createdLabel.setForeground(Color.white);
		this.add(createdLabel);
		
		//Create the 4 translucent panels
		this.leftPanel = new TranslucentBufferedImageJPanel(Content.black, 0.55);
		this.leftPanel.setSize(leftsize, height - topsize - bottomsize);
		this.leftPanel.setLocation(bordersize, topsize);
		this.leftPanel.setLayout(null);
		this.rightPanel = new TranslucentBufferedImageJPanel(Content.black, 0.55);
		this.rightPanel.setSize(rightsize, height - topsize - bottomsize);
		this.rightPanel.setLocation(width - bordersize - rightsize, topsize);
		this.rightPanel.setLayout(null);
		this.upPanel = new TranslucentBufferedImageJPanel(Content.black, 0.55);
		this.upPanel.setSize(width - (2*bordersize), topsize - bordersize);
		this.upPanel.setLocation(bordersize, bordersize);
		this.upPanel.setLayout(null);
		this.lowPanel = new TranslucentBufferedImageJPanel(Content.black, 0.55);
		this.lowPanel.setSize(width - (2*bordersize), bottomsize - bordersize);
		this.lowPanel.setLocation(bordersize, height - bottomsize);
		this.lowPanel.setLayout(null);
		this.add(leftPanel);
		this.add(rightPanel);
		this.add(upPanel);
		this.add(lowPanel);
		
		//Create the prototypePanel
		VectorF2 ppsize = new VectorF2(width - leftsize - rightsize - (2*bordersize), height - topsize - bottomsize - (2*bordersize));
		this.prototypePanel = new PrototypePanel(ppsize);
		this.prototypePanel.setLocation(bordersize + leftsize, bordersize + topsize);
		this.add(this.prototypePanel);

		//Create the fullbackground
		this.fullbackground = new TranslucentBufferedImageJPanel(Content.background, 1);
		this.fullbackground.setSize(width - (2*bordersize), height - (2*bordersize));
		this.fullbackground.setLocation(bordersize, bordersize);
		this.add(this.fullbackground);
	}
}