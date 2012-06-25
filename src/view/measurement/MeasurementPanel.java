package view.measurement;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.util.VectorF2;

import org.tudelft.affectbutton.AffectButton;

import util.ComponentUtil;
import util.LocationCalculator;
import util.LocationCalculator.LocationType;
import util.SizeCalculator;
import view.components.TranslucentBufferedImageJPanel;
import applicationmeasurement.MeasurementConfig;
import content.Content;
import controller.measurement.MeasurementController;

public class MeasurementPanel extends TranslucentBufferedImageJPanel
{
	protected MeasurementController controller;
	
	protected JTextArea explanationArea;
	protected JScrollPane explanationScrollpane;
	protected AffectButton afbutton;
	protected JButton continueButton, skipButton, repeatButton, neutralButton;
	protected JTextArea inputArea;
	protected JScrollPane inputScrollpane;
	protected JLabel testsLabel;
	
	public MeasurementPanel(VectorF2 size)
	{
		super(Content.black, 0.45f);
		
		initialize((int)size.x, (int)size.y);
	}
	
	private void initialize(int width, int height)
	{
		this.controller = new MeasurementController(this);
		
		this.setLayout(null);
		this.setSize(width, height);

		//Sizes
		VectorF2 holdersize = new VectorF2(width, height);
		VectorF2 explanationsize = SizeCalculator.calculateSize(holdersize, 0.9f, 0.4f);
		VectorF2 afbuttonsize = SizeCalculator.calculateSize(new VectorF2(50, 50), new VectorF2(200, 200), holdersize, 0.25f, 0.25f, 1);
		VectorF2 buttonsize = SizeCalculator.calculateSize(new VectorF2(160, 30), holdersize);
		VectorF2 inputareasize = SizeCalculator.calculateSize(new VectorF2(100, 30), new VectorF2(500, 200), holdersize, 0.8f, 0.1f);
		VectorF2 tlabelsize = SizeCalculator.calculateSize(new VectorF2(200, 20), holdersize);
		
		//Locations
		VectorF2 explanationpos = LocationCalculator.calculateLocation(explanationsize, holdersize, LocationType.CENTER, 0.05f);
		VectorF2 afbuttonpos = LocationCalculator.calculateLocation(afbuttonsize, holdersize, 0.28f, 0.525f);
		VectorF2 continuebuttonpos = LocationCalculator.calculateLocation(buttonsize, holdersize, 0.75f, 0.675f);
		VectorF2 repeatbuttonpos = LocationCalculator.calculateLocation(buttonsize, holdersize, 0.75f, 0.75f);
		VectorF2 neutralbuttonpos = LocationCalculator.calculateLocation(buttonsize, holdersize, 0.75f, 0.825f);
		VectorF2 skipbuttonpos = LocationCalculator.calculateLocation(buttonsize, holdersize, 0.75f, 0.9f);
		VectorF2 inputareapos = LocationCalculator.calculateLocation(inputareasize, holdersize, 0.05f, 0.85f);
		VectorF2 tlabelpos = LocationCalculator.calculateLocation(tlabelsize, holdersize, 0.275f, 0.96f);
		
		//Explanation TextArea
		explanationArea = new JTextArea();
		ComponentUtil.setComponentBounds(explanationArea, explanationsize, new VectorF2());
		explanationArea.setForeground(Color.white);
		explanationArea.setFont(Content.mediumFont);
		explanationArea.setOpaque(false);
		explanationArea.setEditable(false);
		explanationArea.setLineWrap(true);
		explanationArea.setWrapStyleWord(true);
		explanationArea.setText(MeasurementConfig.explanatoryText);
		explanationScrollpane = new JScrollPane(explanationArea);
		explanationScrollpane.setOpaque(false);
		explanationScrollpane.getViewport().setOpaque(false);
		ComponentUtil.setComponentBounds(explanationScrollpane, explanationsize, explanationpos);
		this.add(this.explanationScrollpane);
		
		//AffectButton
		afbutton = new AffectButton();
		ComponentUtil.setComponentBounds(afbutton, afbuttonsize, afbuttonpos);
		afbutton.addActionListener(controller);
		this.add(this.afbutton);
		
		//Synthesize button.
		continueButton = new JButton("Save & Continue");
		ComponentUtil.setComponentBounds(continueButton, buttonsize, continuebuttonpos);
		continueButton.addActionListener(this.controller);
		continueButton.setEnabled(false);
		this.add(this.continueButton);
		repeatButton = new JButton("Repeat test");
		ComponentUtil.setComponentBounds(repeatButton, buttonsize, repeatbuttonpos);
		repeatButton.addActionListener(this.controller);
		this.add(this.repeatButton);
		neutralButton = new JButton("Compare with neutral");
		ComponentUtil.setComponentBounds(neutralButton, buttonsize, neutralbuttonpos);
		neutralButton.addActionListener(this.controller);
		this.add(this.neutralButton);
		skipButton = new JButton("Skip (I don't know)");
		ComponentUtil.setComponentBounds(skipButton, buttonsize, skipbuttonpos);
		skipButton.addActionListener(this.controller);
		this.add(this.skipButton);
		
		//Insert TextArea
		inputArea = new JTextArea();
		ComponentUtil.setComponentBounds(inputArea, inputareasize, new VectorF2());
		inputArea.setLineWrap(true);
		inputArea.setWrapStyleWord(true);
		inputScrollpane = new JScrollPane(inputArea);
		ComponentUtil.setComponentBounds(inputScrollpane, inputareasize, inputareapos);
		inputScrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(this.inputScrollpane);
		
		//Insert tests label
		testsLabel = new JLabel();
		ComponentUtil.setComponentBounds(testsLabel, tlabelsize, tlabelpos);
		testsLabel.setForeground(Color.white);
		testsLabel.setFont(Content.mediumFont);
		testsLabel.setOpaque(false);
		this.add(testsLabel);
		
		this.setTestsDone(0, 0);
	}
	
	public AffectButton getAffectButton()
	{
		return this.afbutton;
	}
	
	public JButton getContinueButton()
	{
		return this.continueButton;
	}
	
	public JButton getRepeatButton()
	{
		return this.repeatButton;
	}
	
	public JButton getNeutralButton()
	{
		return this.neutralButton;
	}
	
	public JButton getSkipButton()
	{
		return this.skipButton;
	}

	public void setTestsDone(int saved, int total)
	{
		this.testsLabel.setText("Tests saved & total: " + saved + " / " + total);
	}
	
	public String getInsertedText()
	{
		return this.inputArea.getText();
	}
}
