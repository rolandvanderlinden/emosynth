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
import view.components.BufferedImageJPanel;
import view.components.TranslucentBufferedImageJPanel;
import applicationmeasurement.MeasurementConfig;
import content.Content;
import controller.measurement.MeasurementController;

public class MeasurementPanel extends TranslucentBufferedImageJPanel
{
	public MeasurementController controller;
	
	protected JTextArea explanationArea;
	protected JScrollPane explanationScrollpane;
	protected AffectButton afbutton;
	protected JButton startButton, continueButton, repeatButton, neutralButton, quitButton;
	protected JTextArea inputArea;
	protected JScrollPane inputScrollpane;
	protected JLabel testsLabel;
	protected BufferedImageJPanel buttonStateImage;
	
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
		VectorF2 afbuttonsize = SizeCalculator.calculateSize(new VectorF2(50, 50), new VectorF2(100, 100), holdersize, 0.33f, 0.33f, 1);
		VectorF2 buttonsize = SizeCalculator.calculateSize(new VectorF2(160, 30), holdersize);
		VectorF2 inputareasize = SizeCalculator.calculateSize(new VectorF2(100, 30), new VectorF2(500, 200), holdersize, 0.8f, 0.1f);
		VectorF2 tlabelsize = SizeCalculator.calculateSize(new VectorF2(135, 20), holdersize);
		VectorF2 bsimagesize = SizeCalculator.calculateSize(new VectorF2(50, 50), new VectorF2(100, 100), holdersize, 0.2f, 0.2f, 1);
		
		//Locations
		VectorF2 explanationpos = LocationCalculator.calculateLocation(explanationsize, holdersize, LocationType.CENTER, 0.05f);
		VectorF2 afbuttonpos = LocationCalculator.calculateLocation(afbuttonsize, holdersize, 0.32f, 0.55f);
		VectorF2 startbuttonpos = LocationCalculator.calculateLocation(buttonsize, holdersize, 0.75f, 0.675f);
		VectorF2 continuebuttonpos = LocationCalculator.calculateLocation(buttonsize, holdersize, 0.75f, 0.75f);
		VectorF2 repeatbuttonpos = LocationCalculator.calculateLocation(buttonsize, holdersize, 0.75f, 0.825f);
		VectorF2 neutralbuttonpos = LocationCalculator.calculateLocation(buttonsize, holdersize, 0.75f, 0.9f);
		VectorF2 inputareapos = LocationCalculator.calculateLocation(inputareasize, holdersize, 0.05f, 0.85f);
		VectorF2 tlabelpos = LocationCalculator.calculateLocation(tlabelsize, holdersize, LocationType.CENTER, 0.96f);
		VectorF2 bsimagepos = LocationCalculator.calculateLocation(bsimagesize, holdersize, 0.787f, 0.53f);
		VectorF2 qbuttonpos = LocationCalculator.calculateLocation(buttonsize, holdersize, LocationType.CENTER, LocationType.THREE_QUARTERS);
		
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
		afbutton.setEnabled(false);
		this.add(this.afbutton);
		
		//Control buttons.
		startButton = new JButton("Start");
		ComponentUtil.setComponentBounds(startButton, buttonsize, startbuttonpos);
		startButton.addActionListener(controller);
		startButton.setToolTipText("Start the first test.");
		this.add(this.startButton);
		continueButton = new JButton("Save & Continue");
		ComponentUtil.setComponentBounds(continueButton, buttonsize, continuebuttonpos);
		continueButton.addActionListener(this.controller);
		continueButton.setEnabled(false);
		continueButton.setToolTipText("Save your selection and continue to the next test. That is only possible if you actually made a selection on the affectbutton.");
		this.add(this.continueButton);
		repeatButton = new JButton("Repeat test");
		ComponentUtil.setComponentBounds(repeatButton, buttonsize, repeatbuttonpos);
		repeatButton.addActionListener(this.controller);
		repeatButton.setEnabled(false);
		repeatButton.setToolTipText("Repeat the test with the same affective voice.");
		this.add(this.repeatButton);
		neutralButton = new JButton("Compare with neutral");
		ComponentUtil.setComponentBounds(neutralButton, buttonsize, neutralbuttonpos);
		neutralButton.addActionListener(this.controller);
		neutralButton.setEnabled(false);
		neutralButton.setToolTipText("Repeat the test but now with a neutral affective voice.");
		this.add(this.neutralButton);
		quitButton = new JButton("Quit");
		ComponentUtil.setComponentBounds(quitButton, buttonsize, qbuttonpos);
		quitButton.addActionListener(controller);
		quitButton.setEnabled(false);
		quitButton.setVisible(false);
		quitButton.setToolTipText("Quit the experiment. Thank you for participating.");
		this.add(this.quitButton);
		
		//Insert TextArea
		inputArea = new JTextArea();
		ComponentUtil.setComponentBounds(inputArea, inputareasize, new VectorF2());
		inputArea.setLineWrap(true);
		inputArea.setWrapStyleWord(true);
		inputArea.setText(MeasurementConfig.standardExperimentText);
		inputArea.setEditable(false);
		inputArea.setToolTipText("This is the text that will be synthesized. For internal validity reasons, we do not allow you to change the text during the experiment.");
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
		
		//Insert buttonstate image panel
		buttonStateImage = new BufferedImageJPanel(null, 0);
		ComponentUtil.setComponentBounds(buttonStateImage, bsimagesize, bsimagepos);
		this.add(buttonStateImage);
		
		this.setTestNumber(0);
	}
	
	public void switchToEndVisuals()
	{
		
	}
	
	public void setTestNumber(int num)
	{
		this.testsLabel.setText("Test number: " + num + " / " + MeasurementConfig.sampleSize);
	}
	
	public void setExplanation(String text)
	{
		this.explanationArea.setText(text);
	}
	
	public AffectButton getAffectButton()
	{
		return this.afbutton;
	}
	
	public JButton getStartButton()
	{
		return this.startButton;
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

	public String getInsertedText()
	{
		return this.inputArea.getText();
	}
	
	public BufferedImageJPanel getButtonStateImagePanel()
	{
		return buttonStateImage;
	}
	
	public JScrollPane getInputScrollPane()
	{
		return this.inputScrollpane;
	}
	
	public JButton getQuitButton()
	{
		return this.quitButton;
	}
}
