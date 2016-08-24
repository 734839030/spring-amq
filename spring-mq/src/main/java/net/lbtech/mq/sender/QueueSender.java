package net.lbtech.mq.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


/**
 * 队列发送
 * @author DF
 *
 */
@Component
public class QueueSender extends AbstractSender{
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;

	@Override
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
}
