package activemqtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopProducerTest {
	public static void main(String[] args) {
		String brokerURL = "tcp://localhost:61616";
		//连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("system", "manager", brokerURL);
        Connection connection = null;
        try {
        	//创建连接
        	connection = connectionFactory.createConnection();
        	connection.start();
        	//创建会话，没有事物，自动回应
        	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        	//创建queue或者是topic
        	Destination destination = session.createTopic("TopicTest");
        	//创建生产者
        	MessageProducer producer = session.createProducer(destination);
        	TextMessage message = null;
        	for (int i = 20; i < 30; i++) {
				message = session.createTextMessage("第" + (i + 1) + "topic消息");
				//发送消息
				producer.send(message);
				System.out.println("发送成功: " + message.getText());
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
