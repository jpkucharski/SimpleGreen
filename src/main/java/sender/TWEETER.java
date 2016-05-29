package sender;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class TWEETER {
	private String sender1;
	private String messageType1;
	private String receiver1;
	private String messageTopic1;
	private String messageText1;

	public TWEETER(String sender, String messagType, String receiver,
			String messageTopic, String messageText) {
		this.sender1 = sender;
		this.messageType1 = messagType;
		this.receiver1 = receiver;
		this.messageTopic1 = messageTopic;
		this.messageText1 = messageText;
	}
	
	public void tweetService() throws FileNotFoundException {
			fileSavingOnHD2();
	}
	
	public void fileSavingOnHD2() throws FileNotFoundException {
		PrintWriter zapis;
		
			zapis = new PrintWriter(new FileOutputStream(
					"D:/userdata/jankucha/Desktop/test sms and e-mails/"
							+ messageType1 + "_" + receiver1 + "_"
							
							+ new java.util.Date().getTime() + ".txt"));
			zapis.println("Sender: " + sender1 + " Receiver :" + receiver1
					+ " Topic: " + messageTopic1 + " Message txt: "
					+ messageText1);
			zapis.close();
	
	}
}
