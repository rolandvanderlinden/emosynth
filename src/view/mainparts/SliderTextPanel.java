package view.mainparts;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import model.util.VectorF2;
import util.LocationCalculator;
import util.LocationCalculator.LocationType;
import util.SizeCalculator;
import controller.slidertext.SliderTextController;

public class SliderTextPanel extends AMainPanel
{
	public final static int PITCH_MIN = 0;
	public final static int PITCH_MAX = 500;
	public final static int PITCH_INIT = 100;
	
	private SliderTextController controller;
	
	protected JButton synthesizeButton;
	protected JTextArea textarea;
	protected JScrollPane textScrollpane;
	
	protected JLabel pitchLabel;
	
	protected JSlider pitchSlider;
	
	
	
	
	public SliderTextPanel()
	{
		super();
		
		//NOTE dont do init here, will be called from the controller.
	}
	
	public void initialize(int width, int height)
	{
		this.controller = new SliderTextController(this);
		
		this.setLayout(null);
		this.setBackground(Color.yellow);
		
		//Sizes
		VectorF2 holdersize = new VectorF2(width, height);
		VectorF2 textareasize = SizeCalculator.calculateSize(new VectorF2(100, 30), new VectorF2(500, 200), holdersize, 0.8f, 0.1f);
		VectorF2 synthesizebuttonsize = SizeCalculator.calculateSize(new VectorF2(100, 30), holdersize);
		VectorF2 plabelsize = new VectorF2(100,30);
		VectorF2 pitchsize = SizeCalculator.calculateSize(new VectorF2(200, 50), new VectorF2(500, 200), holdersize, 0.7f, 0.075f);
		
		//Locations
		VectorF2 textarealocation = LocationCalculator.calculateLocation(textareasize, holdersize, LocationType.CENTER, 0.775f);
		VectorF2 synthesizebuttonlocation = LocationCalculator.calculateLocation(synthesizebuttonsize, holdersize, 0.725f, 0.9f);
		VectorF2 plabellocation = LocationCalculator.calculateLocation(plabelsize, holdersize, 0.05f, 0.05f);
		VectorF2 pitchlocation = LocationCalculator.calculateLocation(pitchsize, holdersize, LocationType.CENTER, 0.05f);
		
		//Textfield
		textarea = new JTextArea();
		textarea.setSize((int)textareasize.x, (int)textareasize.y);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textScrollpane = new JScrollPane(textarea);
		textScrollpane.setSize(textarea.getWidth(), textarea.getHeight());
		textScrollpane.setLocation((int)textarealocation.x, (int)textarealocation.y);
		textScrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(this.textScrollpane);
		
		//Synthesize button.
		synthesizeButton = new JButton("Synthesize");
		synthesizeButton.setSize((int)synthesizebuttonsize.x, (int)synthesizebuttonsize.y);
		synthesizeButton.setLocation((int)synthesizebuttonlocation.x, (int)synthesizebuttonlocation.y);
		synthesizeButton.addActionListener(this.controller);
		this.add(this.synthesizeButton);
		
		//Labels
		pitchLabel = new JLabel("Pitch");
		pitchLabel.setSize((int)plabelsize.x, (int)plabelsize.y);
		pitchLabel.setLocation((int)plabellocation.x, (int)plabellocation.y);
		this.add(this.pitchLabel);
		
		//Slider
		pitchSlider = new JSlider(JSlider.HORIZONTAL, PITCH_MIN, PITCH_MAX, PITCH_INIT);
		pitchSlider.setSize((int)pitchsize.x, (int)pitchsize.y);
		pitchSlider.setLocation((int)pitchlocation.x, (int)pitchlocation.y);
		pitchSlider.setMajorTickSpacing(100);
		pitchSlider.setMinorTickSpacing(10);
		pitchSlider.setPaintTicks(true);
		pitchSlider.setPaintLabels(true);
		pitchSlider.addChangeListener(controller);
		this.add(pitchSlider);
	}
	
	public JButton getSynthesizeButton()
	{
		return this.synthesizeButton;
	}
	
	public String getInsertedText()
	{
		return this.textarea.getText();
	}
	
	public JSlider getPitchSlider()
	{
		return this.pitchSlider;
	}
}
