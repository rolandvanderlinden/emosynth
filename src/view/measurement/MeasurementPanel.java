package view.measurement;

import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.util.VectorF2;

import org.tudelft.affectbutton.AffectButton;

import util.LocationCalculator;
import util.LocationCalculator.LocationType;
import util.SizeCalculator;
import controller.measurement.MeasurementController;
import controller.prototype.PrototypeController;

public class MeasurementPanel extends JPanel
{
	protected MeasurementController controller;
	
	protected AffectButton afbutton;
	protected JButton synthesizeButton;
	protected JTextArea textarea;
	protected JScrollPane textScrollpane;
	
	protected JLabel plabel, alabel, dlabel;
	private DecimalFormat padFormat;
	
	public MeasurementPanel(VectorF2 size)
	{
		super();
		
		initialize((int)size.x, (int)size.y);
	}
	
	private void initialize(int width, int height)
	{
		this.controller = new MeasurementController(this);
		this.padFormat = new DecimalFormat("0.000");
		
		this.setLayout(null);
		this.setSize(width, height);
		this.setOpaque(false);
		
		//Sizes
		VectorF2 holdersize = new VectorF2(width, height);
		VectorF2 afbuttonsize = SizeCalculator.calculateSize(new VectorF2(50, 50), new VectorF2(200, 200), holdersize, 0.25f, 0.25f, 1);
		VectorF2 synthesizebuttonsize = SizeCalculator.calculateSize(new VectorF2(100, 30), holdersize);
		VectorF2 textareasize = SizeCalculator.calculateSize(new VectorF2(100, 30), new VectorF2(500, 200), holdersize, 0.8f, 0.1f);
		VectorF2 padlabelsize = SizeCalculator.calculateSize(new VectorF2(50, 30), holdersize);
		
		//Locations
		VectorF2 afbuttonlocation = LocationCalculator.calculateLocation(afbuttonsize, holdersize, LocationType.CENTER, 0.45f);
		VectorF2 synthesizebuttonlocation = LocationCalculator.calculateLocation(synthesizebuttonsize, holdersize, 0.725f, 0.9f);
		VectorF2 textarealocation = LocationCalculator.calculateLocation(textareasize, holdersize, LocationType.CENTER, 0.775f);
		VectorF2 plocation = LocationCalculator.calculateLocation(padlabelsize, holdersize, LocationType.ONE_QUARTER, 0.7f);
		VectorF2 alocation = LocationCalculator.calculateLocation(padlabelsize, holdersize, LocationType.CENTER, 0.7f);
		VectorF2 dlocation = LocationCalculator.calculateLocation(padlabelsize, holdersize, LocationType.THREE_QUARTERS, 0.7f);
		
		//AffectButton
		afbutton = new AffectButton();
		afbutton.setSize((int)afbuttonsize.x, (int)afbuttonsize.y);
		afbutton.setLocation((int)afbuttonlocation.x, (int)afbuttonlocation.y);
		afbutton.addActionListener(controller);
		this.add(this.afbutton);
		
		//Synthesize button.
		synthesizeButton = new JButton("Synthesize");
		synthesizeButton.setSize((int)synthesizebuttonsize.x, (int)synthesizebuttonsize.y);
		synthesizeButton.setLocation((int)synthesizebuttonlocation.x, (int)synthesizebuttonlocation.y);
		synthesizeButton.addActionListener(this.controller);
		this.add(this.synthesizeButton);
		
		//TextArea
		textarea = new JTextArea();
		textarea.setSize((int)textareasize.x, (int)textareasize.y);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textScrollpane = new JScrollPane(textarea);
		textScrollpane.setSize(textarea.getWidth(), textarea.getHeight());
		textScrollpane.setLocation((int)textarealocation.x, (int)textarealocation.y);
		textScrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(this.textScrollpane);

		//Labels
		plabel = new JLabel();
		plabel.setSize((int)padlabelsize.x, (int)padlabelsize.y);
		plabel.setLocation((int)plocation.x, (int)plocation.y);
		plabel.setForeground(Color.white);
		this.add(plabel);
		alabel = new JLabel();
		alabel.setSize((int)padlabelsize.x, (int)padlabelsize.y);
		alabel.setLocation((int)alocation.x, (int)alocation.y);
		alabel.setForeground(Color.white);
		this.add(alabel);
		dlabel = new JLabel();
		dlabel.setSize((int)padlabelsize.x, (int)padlabelsize.y);
		dlabel.setLocation((int)dlocation.x, (int)dlocation.y);
		dlabel.setForeground(Color.white);
		this.add(dlabel);
		setPAD(0, 0, 0);		
	}
	
	/**
	 * This will set the used pad values in the labels.
	 * @param p
	 * @param a
	 * @param d
	 */
	public void setPAD(double p, double a, double d)
	{
		String sp = padFormat.format(p);
		String sa = padFormat.format(a);
		String sd = padFormat.format(d);
		
		plabel.setText("p: " + sp);
		alabel.setText("a: " + sa);
		dlabel.setText("d: " + sd);
	}
	
	public AffectButton getAffectButton()
	{
		return this.afbutton;
	}
	
	public JButton getSynthesizeButton()
	{
		return this.synthesizeButton;
	}
	
	public String getInsertedText()
	{
		return this.textarea.getText();
	}
}
