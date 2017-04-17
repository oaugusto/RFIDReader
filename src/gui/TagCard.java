package RFIDReader.src.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class TagCard extends JPanel {
	
	JLabel id;
	JLabel readRate;
	JLabel successRate;
	JLabel icon;
	
	public TagCard(String id, String readRate, String sucessRate, int rate) {
		if (rate == 0) {
			this.icon = new JLabel(
					new ImageIcon(new ImageIcon("bin/RFIDReader/images/s1.png")
							.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		} else if(rate <= 20 && rate > 0) {
			this.icon = new JLabel(
					new ImageIcon(new ImageIcon("bin/RFIDReader/images/s2.png")
							.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		} else if(rate <= 40 && rate > 20) {
			this.icon = new JLabel(
					new ImageIcon(new ImageIcon("bin/RFIDReader/images/s3.png")
							.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		} else if(rate <= 60 && rate > 40) {
			this.icon = new JLabel(
					new ImageIcon(new ImageIcon("bin/RFIDReader/images/s4.png")
							.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		} else if(rate <= 80 && rate > 60) {
			this.icon = new JLabel(
					new ImageIcon(new ImageIcon("bin/RFIDReader/images/s5.png")
							.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		} else if(rate <= 100 && rate > 80) {
			this.icon = new JLabel(
					new ImageIcon(new ImageIcon("bin/RFIDReader/images/s6.png")
							.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		}
		add(this.icon);
		
		this.id = new JLabel("ID: " + id);
		this.id.setFont(new Font("serif", Font.PLAIN, 15));
		this.id.setForeground(Color.GREEN);
		this.id.setHorizontalAlignment(JLabel.CENTER);
		add(this.id);
		
		this.readRate = new JLabel("READ RATE: " + readRate);
		this.readRate.setFont(new Font("serif", Font.PLAIN, 20));
		this.readRate.setForeground(Color.GREEN);
		this.readRate.setHorizontalAlignment(JLabel.CENTER);
		add(this.readRate);
		
		this.successRate = new JLabel("SUCCESS " + sucessRate);
		this.successRate.setFont(new Font("serif", Font.PLAIN, 20));
		this.successRate.setForeground(Color.GREEN);
		this.successRate.setHorizontalAlignment(JLabel.CENTER);
		add(this.successRate);
		
		JProgressBar pr = new JProgressBar();
		pr.setStringPainted(true);
        pr.setValue(rate);
        pr.setSize(new Dimension(10, 23));
        pr.setForeground(Color.YELLOW);
        add(pr);
		
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GREEN));
		setSize(400, 400);
		setLayout(new GridLayout(0,1));
	}
}
