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

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "CurrencyQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "CurrencyQueue")
public class CurrencyMDB implements MessageListener {

	private static final String PATH = "C:\\Users\\aslam\\Desktop\\file.csv";

	public CurrencyMDB() {
		// TODO Auto-generated constructor stub
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
