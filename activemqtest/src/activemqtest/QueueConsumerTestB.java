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

public class QueueConsumerTestB {

	public static void main(String[] args) {
		//连接工厂
//		String brokerURL = "failover:(tcp://192.168.177.199:51510,tcp://192.168.177.198:51512,tcp://192.168.177.197:51513)?randomize=false";
		String brokerURL = "failover:(tcp://127.0.0.1:6697,tcp://127.0.0.1:6698,tcp://127.0.0.1:6699)?randomize=false";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("system", "manager", brokerURL);
        Connection connection = null;
        try {
        	connection = connectionFactory.createConnection();
        	connection.start();
        	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        	Destination destination = session.createQueue("QueueTest");
//        	MessageProducer producer = session.createProducer(destination);
        	MessageConsumer consumer = session.createConsumer(destination);
//        	consumer.setMessageListener(new MyMessageListener());
        	TextMessage message = null;
        	for (;;) {
				message = (TextMessage) consumer.receive();
				if (message == null) {
					break;
				}
				System.out.println("消费者B接收到的消息：" + message.getText());
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
