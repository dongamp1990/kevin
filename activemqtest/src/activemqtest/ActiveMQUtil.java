package activemqtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQUtil {

	private static ConnectionFactory connectionFactory;
	
	static {
		String brokerURL = "failover:(tcp://127.0.0.1:6697,tcp://127.0.0.1:6698,tcp://127.0.0.1:6699)?randomize=false";
		System.out.println("初始化ConnectionFactory..., brokerURL = " + brokerURL);
		//连接工厂
        connectionFactory = new ActiveMQConnectionFactory("system", "manager", brokerURL);
	}
	
	public static ConnectionFactory connectionFactory() {
		return connectionFactory;
	}
	
	public static ConnectionFactory connectionFactory(String brokerURL) {
		return connectionFactory("system", "manager", brokerURL);
	}
	
	public static ConnectionFactory connectionFactory(String brokerURL, String userName, String password) {
		return new ActiveMQConnectionFactory(userName, password, brokerURL);
	}
	
	public static Connection openConnection() throws JMSException {
		return connectionFactory.createConnection();
	}
}
