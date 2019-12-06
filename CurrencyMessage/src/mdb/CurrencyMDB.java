package mdb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "jms/queue/Message"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "jms/queue/Message")
public class CurrencyMDB implements MessageListener {

	private static final String PATH = "C:\\Users\\usman\\Desktop\\file.csv";
    public CurrencyMDB() {
    }
	
	
    public void onMessage(Message message) {
    	ObjectMessage objectMessage = null;
		try {
			objectMessage = (ObjectMessage) message;
			ConverterModel converterModel = (ConverterModel) objectMessage.getObject();

			File file = new File(PATH);

			if (file.exists()) {
				FileWriter writer = new FileWriter(PATH, true);
				CsvHelper.WriteLine(writer,converterModel);
			}
		} catch (JMSException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
