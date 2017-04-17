package RFIDReader.src.rfidreader;

import java.util.HashMap;
import java.util.Map;

import com.alien.enterpriseRFID.tags.Tag;

/**
 * This class is used to track the number of times that a tag is read by the
 * Reader. Necessary to calculate the success rate.
 */
public class TagRecorder {

	private Map<String, Integer> table;
	
	public TagRecorder() {
		this.table = new HashMap<String,Integer>();	
	}
	
	/**
	 * Records the reading of a tag. If it was recorded before, then its number
	 * of reading is incremented.
	 * @param tag
	 */
	public void recordTag(Tag tag) {	
		if (!this.table.containsKey(tag.getTagID())) { 
			this.table.put(tag.getTagID(), new Integer(1));
			
		} else {
			Integer old_value = this.table.get(tag.getTagID());
			old_value++;
			this.table.put(tag.getTagID(), old_value);
		}
	}
	
	/**
	 * Adds a list of tags.
	 * @param tag_list
	 */
	public void recordTags(Tag[] tag_list) {
		for (int i = 0; i < tag_list.length; i++)
			this.recordTag(tag_list[i]);
	}
	
	/**
	 * Gets the number of times that the tag was added.
	 * @param tag_id
	 */
	public int getNumReadings(String tag_id) {
		return this.table.get(tag_id);
	}
	
	/**
	 * Remove the recorded tags.
	 */
	public void clearRecords() {
		this.table = new HashMap<String,Integer>();
	}
	
}
