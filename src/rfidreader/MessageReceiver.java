package RFIDReader.src.rfidreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JTextArea;

import com.alien.enterpriseRFID.notify.Message;
import com.alien.enterpriseRFID.notify.MessageListener;
import com.alien.enterpriseRFID.notify.MessageListenerService;
import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderConnectionException;
import com.alien.enterpriseRFID.reader.AlienReaderConnectionRefusedException;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.reader.AlienReaderNotValidException;
import com.alien.enterpriseRFID.reader.AlienReaderTimeoutException;
import com.alien.enterpriseRFID.tags.Tag;

import RFIDReader.src.gui.*;
import RFIDReader.src.gui.*;

/**
 * This class implements the autonomous mode of the Alien Reader.
 *
 */
public class MessageReceiver implements MessageListener {

	private MessageListenerService service;
	private AlienClass1Reader reader;
	private TagsPanel panel;
	
	private TagRecorder inv;
	private int num_reads;
	
	public MessageReceiver(TagsPanel panel, String AlienIP, int AlienPort) 
			throws IOException {
		this.service = new MessageListenerService(Parameters.NOTIFY_PORT);
		this.service.setMessageListener(this);
		this.service.startService();
		
		this.reader = new AlienClass1Reader(AlienIP, AlienPort);
		// Default username and password.
		this.reader.setUsername(Parameters.ALIEN1_USERNAME);
		this.reader.setPassword(Parameters.ALIEN1_PASSWORD);
		
		this.panel = panel;
		this.inv= new TagRecorder();
	}
	
	/**
	 * Starts the receiving service.
	 * @param notifyTime - time interval to receive messages.
	 * @throws AlienReaderConnectionRefusedException
	 * @throws AlienReaderNotValidException
	 * @throws AlienReaderTimeoutException
	 * @throws AlienReaderConnectionException
	 * @throws AlienReaderException
	 * @throws IOException
	 */
	public void start(int notifyTime) 
			throws AlienReaderConnectionRefusedException, 
			AlienReaderNotValidException, AlienReaderTimeoutException, 
			AlienReaderConnectionException, AlienReaderException, IOException {
		
		this.num_reads = 0;
		this.reader.open();
		if (this.reader.isOpen())
			System.out.println("Connected to Reader");
		
		// Getting the machine IP.
		URL url = new URL("http://checkip.amazonaws.com/");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		String own_ip = br.readLine();
		
		// Configuring the notification system.
		this.reader.setNotifyAddress(own_ip, 
				this.service.getListenerPort());
		this.reader.setNotifyTime(notifyTime);
		this.reader.setNotifyFormat(AlienClass1Reader.XML_FORMAT);
		// Required for Alien 9650 (duplicated messages problem).
		this.reader.setNotifyTrigger("OFF"); 
		
		// Starting the AutoMode.
		this.reader.setNotifyMode(AlienClass1Reader.ON);
		this.reader.setAutoMode(AlienClass1Reader.ON);
				
		this.reader.close();
	}
	
	/**
	 * Finishes the execution of the receiver.
	 * Disables the autonomous and the notify mode.
	 * @throws Exception
	 */
	public void finish() throws Exception {
		this.reader.open();
		this.reader.autoModeReset();
		this.reader.setNotifyMode(AlienClass1Reader.OFF);
		this.reader.close();
	}
	
	/**
	 * Receives the message and extract the read tags, their read rate and 
	 * their success rate.
	 */
	@Override
	public void messageReceived(Message msg) {
		this.num_reads++;
		int numTags, rounded_srate, i;
		double rrate, srate, time;
		String id, read_rate, succ_rate;
		//this.tarea.setText("");
		numTags = msg.getTagCount();		
		System.out.println("\nMessage Received: " + numTags + " tags.");
		
		TagCard[] cards = new TagCard[numTags]; // To GUI.
		i = 0;
		for (Tag tag : msg.getTagList()) {
			id = tag.getTagID();
			this.inv.recordTag(tag);
			
			System.out.print("ID: " + id);
			time = (double)tag.getRenewTime() - (double)tag.getDiscoverTime();
			time /= 1000;
			if (time == 0) // Avoiding Infinity.
				time = 1;
			rrate = ((double)tag.getRenewCount()) / time;
			read_rate = String.format("%5.2f", rrate);
			System.out.print(" Read Rate: " +  read_rate + " leituras/s");	
			
			srate = (double)this.inv.getNumReadings(id) / this.num_reads;
			srate *= 100;
			succ_rate = String.format("%5.2f", srate);
			rounded_srate = (int) srate;
			System.out.println(" Success Rate: " +  succ_rate + "%");
			
			cards[i] = new TagCard(id, read_rate, succ_rate, rounded_srate);
			i++;
		}
		
		this.panel.showTags(cards);
		
	}

}
