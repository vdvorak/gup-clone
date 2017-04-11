package ua.com.itproekt.gup.jms;

import javax.jms.JMSException;
import javax.jms.Queue;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MainMessageSender {
	private JmsTemplate jmsTemplate_i;
	private Queue testQueue_i;
	private static final Logger logger_c = Logger .getLogger(MainMessageSender.class);

	public void sendMessage(String message_p) throws JMSException {
		logger_c.debug("About to put message on queue. Queue[" + testQueue_i.toString() + "] Message[" + message_p + "]");
		jmsTemplate_i.convertAndSend(testQueue_i, message_p);
	}

	public void setJmsTemplate(JmsTemplate tmpl) {
		this.jmsTemplate_i = tmpl;
	}

	public void setTestQueue(Queue queue) {
		this.testQueue_i = queue;
	}
}