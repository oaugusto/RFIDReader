package RFIDReader.src.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginReader extends JFrame{
	
	String[] sreader = {"alien1", "alien2"};
	String[] smode = {"automode", "manual"};
	
	public JComboBox<String> reader;
	public JComboBox<String> mode;
	public JButton ok;
	
	//public String fileName = "iot.jpg";
	//private Image backgroundImage;
	/*
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
	
		// Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, this);
	}
	*/
			
	public LoginReader() {
	/*
		try {
			backgroundImage = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
		
		reader = new JComboBox<>(sreader);
		reader.setBackground(Color.BLACK);
		reader.setForeground(Color.GREEN);
		mode = new JComboBox<>(smode);
		mode.setBackground(Color.BLACK);
		mode.setForeground(Color.GREEN);
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.BLACK);
		JPanel p2 = new JPanel();
		p2.setBackground(Color.BLACK);
		
		JLabel user = new JLabel("READER: ");
		user.setForeground(Color.GREEN);
		p1.add(user);
		
		p1.add(reader);
		p1.setLayout(new FlowLayout());
		
		JLabel password = new JLabel("MODE: ");
		password.setForeground(Color.GREEN);
		p2.add(password);
		
		p2.add(mode);
		p2.setLayout(new FlowLayout());
	
		JPanel p3 = new JPanel();
		ok = new JButton("Enter");
		ok.setBackground(Color.GREEN);
		ok.setSize(50, 50);
		p3.add(ok);
		p3.setBackground(Color.BLACK);
		
		setLayout(new GridLayout(0,1));
		setBackground(Color.BLACK);
		setLocation(800, 200);
		
		add(p1);
		add(p2);
		add(p3);
		
		setSize(200, 200);
		setVisible(true);
		
	}

}
