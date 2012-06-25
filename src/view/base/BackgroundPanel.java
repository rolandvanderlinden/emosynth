package view.base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.util.VectorF2;
import util.ComponentUtil;
import util.LocationCalculator;
import util.SizeCalculator;
import util.LocationCalculator.LocationType;
import view.components.TranslucentBufferedImageJPanel;
import content.Content;

public class BackgroundPanel extends JPanel
{
	// **********************************************
	// Fields
	// **********************************************
	
	private TranslucentBufferedImageJPanel fullbackground, leftPanel, rightPanel, upPanel, lowPanel, controlsPanel;
	private JPanel leborder, uborder, rborder, loborder, lebar, ubar, rbar, lobar;
	private JLabel createdLabel;
	
	// **********************************************
	// Constructor & init
	// **********************************************
	
	public BackgroundPanel(Dimension size, int outerbordersize)
	{
		super();
		
		this.init(size, outerbordersize);
	}
	
	private void init(Dimension size, int outerbordersize)
	{
		FontMetrics smallFontMetrics = this.getFontMetrics(Content.smallFont);
		VectorF2 holdersize = new VectorF2(size.width, size.height);
		
		//Create this screen itself.
		this.setLayout(null);
		this.setSize(size);
		this.setBackground(Color.white);
		this.setOpaque(false);
		
		//Define the sizes that we'll use for the gui.
		int leftsize = 16;
		int rightsize = 16;
		int topsize = 16;
		int bottomsize = 16;
		int bordersize = outerbordersize;
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
		String createdText = "Created by Irene Renkens, Aurimas Bavarskis, Roland van der Linden";
		VectorF2 clabelsize = SizeCalculator.calculateSize(new VectorF2(smallFontMetrics.stringWidth(createdText), 15), holdersize);
		VectorF2 clabelpos = LocationCalculator.calculateLocationWithMargins(clabelsize, holdersize, LocationType.END, LocationType.BEGIN, new VectorF2(4,2));
		this.createdLabel = new JLabel(createdText);
		ComponentUtil.setComponentBounds(createdLabel, clabelsize, clabelpos);
		this.createdLabel.setHorizontalAlignment(JLabel.CENTER);
		this.createdLabel.setVerticalAlignment(JLabel.TOP);
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

		//Create the fullbackground
		this.fullbackground = new TranslucentBufferedImageJPanel(Content.background, 1);
		this.fullbackground.setSize(width - (2*bordersize), height - (2*bordersize));
		this.fullbackground.setLocation(bordersize, bordersize);
		this.add(this.fullbackground);
	}
}