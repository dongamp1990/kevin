package activemqtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueProducerTestB {
	public static void main(String[] args) {
//		String brokerURL = "tcp://localhost:61616";
//		String brokerURL = "tcp://192.168.177.197:51513";
		String brokerURL = "failover:(tcp://192.168.177.199:51510,tcp://192.168.177.198:51512,tcp://192.168.177.197:51513)?randomize=false";
//		String brokerURL = "failover:(tcp://127.0.0.1:6697,tcp://127.0.0.1:6698,tcp://127.0.0.1:6699)?randomize=false";
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
        	Destination destination = session.createQueue("QueueTest");
        	//创建生产者
        	MessageProducer producer = session.createProducer(destination);
        	TextMessage message = null;
        	for (int i = 0; i < 10; i++) {
				message = session.createTextMessage("生产者A第" + (i + 1) + "消息");
				//发送消息
				producer.send(message);
				System.out.println("发送成功: " + message.getText());
				Thread.sleep(300L);
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
