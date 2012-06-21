package view.test;

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
import controller.test.SliderTextController;
import controller.tts.MainSpeaker;

public class SliderTextPanel extends AMainPanel
{
	//TODO make INIT values come from the getters of the main speaker.
	public final static int PITCH_MIN = 0;
	public final static int PITCH_MAX = 500;
	public final static int PITCHSHIFT_MIN = 0;
	public final static int PITCHSHIFT_MAX = 100;
	public final static int PITCHRANGE_MIN = 0;
	public final static int PITCHRANGE_MAX = 500;
	public final static int WORDSPM_MIN = 30;
	public final static int WORDSPM_MAX = 300;
	public final static int DURSTRETCH_MIN = 0;
	public final static int DURSTRETCH_MAX = 100;
	public final static int VOL_MIN = 0;
	public final static int VOL_MAX = 100;
	
	private SliderTextController controller;
	
	protected JButton synthesizeButton;
	protected JTextArea textarea;
	protected JScrollPane textScrollpane;
	
	protected JLabel pitchLabel, pitchshiftLabel, pitchrangeLabel, wordspmLabel, durstretchLabel, volLabel;
	protected JSlider pitchSlider, pitchshiftSlider, pitchrangeSlider, wordspmSlider, durstretchSlider, volSlider;
	
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
		VectorF2 labelsize = new VectorF2(100,30);
		VectorF2 slidersize = SizeCalculator.calculateSize(new VectorF2(200, 50), new VectorF2(500, 200), holdersize, 0.7f, 0.075f);
		
		//Locations
		VectorF2 textarealocation = LocationCalculator.calculateLocation(textareasize, holdersize, LocationType.CENTER, 0.775f);
		VectorF2 synthesizebuttonlocation = LocationCalculator.calculateLocation(synthesizebuttonsize, holdersize, 0.725f, 0.9f);
		VectorF2 plabellocation = LocationCalculator.calculateLocation(labelsize, holdersize, 0.025f, 0.05f);
		VectorF2 pslabellocation = LocationCalculator.calculateLocation(labelsize, holdersize, 0.025f, 0.15f);
		VectorF2 prlabellocation = LocationCalculator.calculateLocation(labelsize, holdersize, 0.025f, 0.25f);
		VectorF2 wlabellocation = LocationCalculator.calculateLocation(labelsize, holdersize, 0.025f, 0.35f);
		VectorF2 dlabellocation = LocationCalculator.calculateLocation(labelsize, holdersize, 0.025f, 0.45f);
		VectorF2 vlabellocation = LocationCalculator.calculateLocation(labelsize, holdersize, 0.025f, 0.55f);
		VectorF2 pitchlocation = LocationCalculator.calculateLocation(slidersize, holdersize, 0.2f, 0.05f);
		VectorF2 pitchslocation = LocationCalculator.calculateLocation(slidersize, holdersize, 0.2f, 0.15f);
		VectorF2 pitchrlocation = LocationCalculator.calculateLocation(slidersize, holdersize, 0.2f, 0.25f);
		VectorF2 wordslocation = LocationCalculator.calculateLocation(slidersize, holdersize, 0.2f, 0.35f);
		VectorF2 durslocation = LocationCalculator.calculateLocation(slidersize, holdersize, 0.2f, 0.45f);
		VectorF2 vollocation = LocationCalculator.calculateLocation(slidersize, holdersize, 0.2f, 0.55f);
		
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
		pitchLabel = new JLabel("Pitch");
		pitchLabel.setSize((int)labelsize.x, (int)labelsize.y);
		pitchLabel.setLocation((int)plabellocation.x, (int)plabellocation.y);
		this.add(this.pitchLabel);
		pitchshiftLabel = new JLabel("Pitch Shift");
		pitchshiftLabel.setSize((int)labelsize.x, (int)labelsize.y);
		pitchshiftLabel.setLocation((int)pslabellocation.x, (int)pslabellocation.y);
		this.add(this.pitchshiftLabel);
		pitchrangeLabel = new JLabel("Pitch Range");
		pitchrangeLabel.setSize((int)labelsize.x, (int)labelsize.y);
		pitchrangeLabel.setLocation((int)prlabellocation.x, (int)prlabellocation.y);
		this.add(this.pitchrangeLabel);
		wordspmLabel = new JLabel("Words / Minute");
		wordspmLabel.setSize((int)labelsize.x, (int)labelsize.y);
		wordspmLabel.setLocation((int)wlabellocation.x, (int)wlabellocation.y);
		this.add(this.wordspmLabel);
		durstretchLabel = new JLabel("Duration Stretch");
		durstretchLabel.setSize((int)labelsize.x, (int)labelsize.y);
		durstretchLabel.setLocation((int)dlabellocation.x, (int)dlabellocation.y);
		this.add(this.durstretchLabel);
		volLabel = new JLabel("Volume");
		volLabel.setSize((int)labelsize.x, (int)labelsize.y);
		volLabel.setLocation((int)vlabellocation.x, (int)vlabellocation.y);
		this.add(this.volLabel);
		
		//Slider
		pitchSlider = new JSlider(JSlider.HORIZONTAL, PITCH_MIN, PITCH_MAX, (int)MainSpeaker.Instance().getPitch());
		pitchSlider.setSize((int)slidersize.x, (int)slidersize.y);
		pitchSlider.setLocation((int)pitchlocation.x, (int)pitchlocation.y);
		pitchSlider.setMajorTickSpacing(100);
		pitchSlider.setMinorTickSpacing(20);
		pitchSlider.setPaintTicks(true);
		pitchSlider.setPaintLabels(true);
		pitchSlider.addChangeListener(controller);
		this.add(pitchSlider);
		pitchshiftSlider = new JSlider(JSlider.HORIZONTAL, PITCHSHIFT_MIN, PITCHSHIFT_MAX, 
				(int)(PITCHSHIFT_MIN + (MainSpeaker.Instance().getPitchShift() / 10.0) * (PITCHSHIFT_MAX - PITCHSHIFT_MIN)));
		pitchshiftSlider.setSize((int)slidersize.x, (int)slidersize.y);
		pitchshiftSlider.setLocation((int)pitchslocation.x, (int)pitchslocation.y);
		pitchshiftSlider.setMajorTickSpacing(20);
		pitchshiftSlider.setMinorTickSpacing(5);
		pitchshiftSlider.setPaintTicks(true);
		pitchshiftSlider.setPaintLabels(true);
		pitchshiftSlider.addChangeListener(controller);
		this.add(pitchshiftSlider);
		pitchrangeSlider = new JSlider(JSlider.HORIZONTAL, PITCHRANGE_MIN, PITCHRANGE_MAX, (int)MainSpeaker.Instance().getPitchRange());
		pitchrangeSlider.setSize((int)slidersize.x, (int)slidersize.y);
		pitchrangeSlider.setLocation((int)pitchrlocation.x, (int)pitchrlocation.y);
		pitchrangeSlider.setMajorTickSpacing(100);
		pitchrangeSlider.setMinorTickSpacing(20);
		pitchrangeSlider.setPaintTicks(true);
		pitchrangeSlider.setPaintLabels(true);
		pitchrangeSlider.addChangeListener(controller);
		this.add(pitchrangeSlider);
		wordspmSlider = new JSlider(JSlider.HORIZONTAL, WORDSPM_MIN, WORDSPM_MAX, (int)MainSpeaker.Instance().getWordsPM());
		wordspmSlider.setSize((int)slidersize.x, (int)slidersize.y);
		wordspmSlider.setLocation((int)wordslocation.x, (int)wordslocation.y);
		wordspmSlider.setMajorTickSpacing(30);
		wordspmSlider.setMinorTickSpacing(5);
		wordspmSlider.setPaintTicks(true);
		wordspmSlider.setPaintLabels(true);
		wordspmSlider.addChangeListener(controller);
		this.add(this.wordspmSlider);
		durstretchSlider = new JSlider(JSlider.HORIZONTAL, DURSTRETCH_MIN, DURSTRETCH_MAX, 
				(int)(DURSTRETCH_MIN + (MainSpeaker.Instance().getDurationStretch() / 10.0) * (DURSTRETCH_MAX - DURSTRETCH_MIN)));
		durstretchSlider.setSize((int)slidersize.x, (int)slidersize.y);
		durstretchSlider.setLocation((int)durslocation.x, (int)durslocation.y);
		durstretchSlider.setMajorTickSpacing(20);
		durstretchSlider.setMinorTickSpacing(5);
		durstretchSlider.setPaintTicks(true);
		durstretchSlider.setPaintLabels(true);
		durstretchSlider.addChangeListener(controller);
		this.add(this.durstretchSlider);
		volSlider = new JSlider(JSlider.HORIZONTAL, VOL_MIN, VOL_MAX, 
				(int)(VOL_MIN + MainSpeaker.Instance().getVolume() * (VOL_MAX - VOL_MIN)));
		volSlider.setSize((int)slidersize.x, (int)slidersize.y);
		volSlider.setLocation((int)vollocation.x, (int)vollocation.y);
		volSlider.setMajorTickSpacing(20);
		volSlider.setMinorTickSpacing(5);
		volSlider.setPaintTicks(true);
		volSlider.setPaintLabels(true);
		volSlider.addChangeListener(controller);
		this.add(this.volSlider);
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
	
	public JSlider getPitchShiftSlider()
	{
		return this.pitchshiftSlider;
	}
	
	public JSlider getPitchRangeSlider()
	{
		return this.pitchrangeSlider;
	}
	
	public JSlider getWordsPMSlider()
	{
		return this.wordspmSlider;
	}
	
	public JSlider getDurStretchSlider()
	{
		return this.durstretchSlider;
	}
	
	public JSlider getVolSlider()
	{
		return this.volSlider;
	}
	
}
