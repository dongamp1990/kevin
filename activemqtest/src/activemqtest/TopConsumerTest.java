package activemqtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopConsumerTest {
	public static void main(String[] args) {
		//连接工厂
		String brokerURL = "tcp://localhost:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("system", "manager", brokerURL);
        Connection connection = null;
        try {
        	connection = connectionFactory.createConnection();
        	connection.start();
        	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        	Destination destination = session.createTopic("TopicTest");
//        	MessageProducer producer = session.createProducer(destination);
        	MessageConsumer consumer = session.createConsumer(destination);
//        	consumer.setMessageListener(new MyMessageListener());
        	TextMessage message = null;
        	for (;;) {
				message = (TextMessage) consumer.receive();
				if (message == null) {
					break;
				}
				System.out.println("接收到的topic消息：" + message.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			} catch (JMSException e1) {
				e1.printStackTrace();
			}
		}
	}
}
