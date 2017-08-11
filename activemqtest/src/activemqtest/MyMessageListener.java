package activemqtest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMsg = (TextMessage) message;
			System.out.println("接收到的消息：" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
