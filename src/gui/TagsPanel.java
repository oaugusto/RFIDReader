package RFIDReader.src.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class TagsPanel extends JPanel {

	JLabel label;
	
	public TagsPanel() {
		setBackground(Color.BLACK);
	    setLayout(new GridLayout(0,3));
	}

	
	public void showTags(TagCard[] tags) {
		removeAll();
	    revalidate();
	    repaint();
	    JPanel pane;
	    
	    for(TagCard tag: tags) {
	    	if(tag != null) {
	    		pane = new JPanel();
	    		pane.add(tag);
	    		pane.setBackground(Color.BLACK);
	    		add(pane);
	    	}
	    }
	}
	
	class MyAdjustmentListener implements AdjustmentListener {
	    public void adjustmentValueChanged(AdjustmentEvent e) {
	      repaint();
	    }
	  }
}