package RFIDReader.src.rfidreader;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;

public class Reader {
	
	private static AlienClass1Reader reader;
	
	private Reader(){
		
	}
	
	public static AlienClass1Reader getReaderInit(String host, int port){
                String username, passwd;

                username = Parameters.ALIEN1_USERNAME;
                passwd = Parameters.ALIEN1_PASSWORD;

		if (reader == null){
			reader = new AlienClass1Reader(host, port);
			reader.setUsername(username);
			reader.setPassword(passwd);
		}
		return reader;
	}

}