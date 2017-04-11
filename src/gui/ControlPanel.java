package RFIDReader.src.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	
	public JButton start;
	public JButton stop;
	public JButton read;
	
	JLayeredPane startReader;
	JLayeredPane stopReader;
	JLayeredPane exitReader;
	JLayeredPane infoReader;
		
	public ControlPanel() {
		//setSize(new Dimension(200, 200));
        this.setLayout (new GridLayout(0,1));
		
        //ImageIcon run = new ImageIcon("start.png");
		this.start = new JButton("START");
		add(this.start);
		this.start.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.start.setSize(new Dimension(100, 100));
		this.start.setBackground(Color.GREEN);
		this.start.setForeground(Color.BLACK);
		this.start.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));
		this.start.setFocusable(false);
				
		this.stop = new JButton("STOP");
		add(this.stop);
		this.stop.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.stop.setSize(new Dimension(100, 100));
		this.stop.setBackground(Color.GREEN);
		this.stop.setForeground(Color.BLACK);
		this.stop.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));
		this.stop.setFocusable(false);
		
		this.read = new JButton("READ");
		add(this.read);
		this.read.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.read.setSize(new Dimension(100, 100));
		this.read.setBackground(Color.GREEN);
		this.read.setForeground(Color.BLACK);
		this.read.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));
		
	}	
	
}
