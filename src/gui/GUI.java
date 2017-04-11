package RFIDReader.src.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	private ControlPanel controlPanel;
	private TagsPanel tagsPanel;
	
	public GUI() {		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setSize(new Dimension(1200, 1000));
		
		tagsPanel = new TagsPanel();
		JScrollPane pane = new JScrollPane(tagsPanel);
		controlPanel = new ControlPanel();
		add(pane);
		add(controlPanel, BorderLayout.LINE_END);
		setVisible(true);
	}
	
	public TagsPanel getTagsPanel() {
		return this.tagsPanel;
	}
	
	public ControlPanel getControlPanel() {
		return this.controlPanel;
	}	
}
