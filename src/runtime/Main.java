package RFIDReader.src.runtime ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import java.util.logging.Level;
import java.util.logging.Logger;

import RFIDReader.src.rfidreader.MessageReceiver;
import RFIDReader.src.rfidreader.Parameters;
import RFIDReader.src.rfidreader.Reader;
import RFIDReader.src.rfidreader.RequestMode;
import RFIDReader.src.gui.ControlPanel;
import RFIDReader.src.gui.GUI;
import RFIDReader.src.gui.LoginReader;

public class Main {
	
	public static String alien;
	public static int alien_port;
	
	public static boolean auto = true;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				LoginReader login = new LoginReader();
								
				login.ok.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						
						if(login.reader.getSelectedItem().equals("alien1")) {
							alien = Parameters.ALIEN1_IP;
							alien_port = Parameters.ALIEN1_PORT;
						} else {
							alien = Parameters.ALIEN2_IP;
							alien_port = Parameters.ALIEN2_PORT;
						}
						
						
						if(login.mode.getSelectedItem().equals("automode")) {
							automodeGUI(alien, alien_port);
						} else {
							manualGUI(alien, alien_port);
						}			
						
						login.dispatchEvent(new WindowEvent(login, WindowEvent.WINDOW_CLOSING));
					}
				});
				
			}
			
		}); //end runnable
	
	}
	
	public static void automodeGUI(String ip, int port) {
            try {
                System.out.println("auto");
                GUI wd = new GUI();
                ControlPanel rf = wd.getControlPanel();
                rf.remove(rf.read);
                
                MessageReceiver msgTags = new MessageReceiver(wd.getTagsPanel()
                        , ip, port); // TODO Auto-generated catch block
                rf.start.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            msgTags.start(1);
                        } catch (AlienReaderException | IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                rf.stop.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            msgTags.finish();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        
                    }
                });
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
	public static void manualGUI(String ip, int port) {
		System.out.println("manual");
		GUI wd = new GUI();
		ControlPanel rf = wd.getControlPanel();
		rf.remove(rf.start);
		rf.remove(rf.stop);
		
                AlienClass1Reader reader = Reader.getReaderInit(ip, port); // TODO Auto-generated catch block
                RequestMode msgTags = new RequestMode(reader);
                rf.read.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //todo
                        wd.getTagsPanel().showTags(msgTags.tagsRead());
                    }
                });
		
	}

}