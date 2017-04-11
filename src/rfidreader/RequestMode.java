package RFIDReader.src.rfidreader;

import RFIDReader.src.gui.*;
import RFIDReader.src.gui.*;

import java.util.HashMap;
import java.util.Map;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;
import java.util.Vector;

public class RequestMode {

	private AlienClass1Reader reader;

	
	public RequestMode(AlienClass1Reader r){
		if (r == null){
			return;
		}
		reader = r;
	}
	
	public Map<String, Integer> getReadRate() throws AlienReaderException{
		
		reader.open();
		Map<String, Integer> leitura = new HashMap<String, Integer>();
		
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start <= Parameters.TIME_MS){
			Tag[] tagList = reader.getTagList();
			for (int i = 0; i < tagList.length; i++){
				Tag tag = tagList[i];
				int auxQuantidade = 0;
				if (leitura.containsKey(tag.getTagID()))
					auxQuantidade = leitura.get(tag.getTagID());
				leitura.put(tag.getTagID(), auxQuantidade + 1);
			}
		}
		reader.close();
		
		return leitura;
	}
	

	public Map<String, Integer> getSucessRate() throws AlienReaderException{
		int nReads = 20;
		//System.out.println(reader.getAddress());
		reader.open();
		
		Map<String, Integer> leitura = new HashMap<String, Integer>();
		for (int n = 0; n < nReads; n++){
			Tag[] tagList = reader.getTagList();	
			for (int i = 0; i < tagList.length; i++){
				Tag tag = tagList[i];
				int auxQuantidade = 0;
				if (leitura.containsKey(tag.getTagID()))
					auxQuantidade = leitura.get(tag.getTagID());
				leitura.put(tag.getTagID(), auxQuantidade + 1);
			}
		}
		

		reader.close();
		leitura = calcSuccessRate(leitura, nReads);
		return leitura;
	}
	
        public Map<String, Integer> calcSuccessRate(Map<String, Integer> leitura, int nReads){
		for (Map.Entry<String, Integer> entry : leitura.entrySet()){
			String tag_ID = entry.getKey();
			int readCount = entry.getValue();
			int sucessRate = readCount * 100 / nReads;
			leitura.put(tag_ID, sucessRate);
		}
                return leitura;
        }


        public TagCard[] tagsRead(){
        
     
        Map<String, Integer> successRate, readRate;
        int num_tags = 0;
        int i = 0;
        String sRead, sSuccess;
        int rate;
        TagCard[] list = null;
        try {
                successRate = this.getSucessRate();
                readRate = this.getReadRate();

                for (Map.Entry<String, Integer> entry : successRate.entrySet()){
                    //System.out.println(entry.getValue() + "% success rate ID " + entry.getKey());
                    num_tags++;
                }
                
                list = new TagCard[num_tags];
                //Vector<String> saida = new Vector<String>();
                for (Map.Entry<String, Integer> entry : successRate.entrySet()){
                    sRead = "" + readRate.get(entry.getKey())/(Parameters.TIME_MS/1000);
                    sSuccess = "" + entry.getValue();
                    rate = Integer.parseInt(sSuccess);
                    list[i] = new TagCard(entry.getKey(), sRead, sSuccess, rate );
                    i++;
                    //System.out.println(entry.getValue() + "% success rate ID " + entry.getKey());

                }      
    
        }
        catch (AlienReaderException e1) {
                e1.printStackTrace();
        }
            return list;
        }
        

	public AlienClass1Reader getReader(){
		return reader;
	}	
	
	public Tag[] getTagList() throws AlienReaderException{
		reader.open();
		Tag[] tagList = reader.getTagList();
		reader.close();
		return tagList;
	}


}