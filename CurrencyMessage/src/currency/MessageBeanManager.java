package currency;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import mdb.ConverterModel;

public class MessageBeanManager {
//	
//	@Resource(mappedName="ConnectionFactory")
//	private static ConnectionFactory connectionFactory;
//
//	@Resource(mappedName="jms/queue/Message")
//	private static Queue queue; 
	
	public void sendMessage(ConverterModel converterModel) {

		try  {
			Context ic = new InitialContext();
			  
	        ConnectionFactory cf = (ConnectionFactory) ic.lookup("/ConnectionFactory");
	        Queue queue = (Queue) ic.lookup("/jms/queue/Message");
	  
	        Connection connection = cf.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = session.createProducer(queue);

			connection.start();

			ObjectMessage objectMessage = session.createObjectMessage(converterModel);

			publisher.send(objectMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
