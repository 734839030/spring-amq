package net.lbtech.mq.sender;

import java.io.Serializable;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 通用发送抽象类
 * 如果发送多个 请自己写一个service  这样才可以事务一致
 * @author DF
 *
 */
//@Transactional(transactionManager="jmsTransactionManager")
public abstract class AbstractSender implements Sender{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public abstract JmsTemplate getJmsTemplate();
	@Override
	public void sendTextMessage(String destinationName, final String textMessage) {
		Assert.isTrue(!StringUtils.isEmpty(textMessage), "textMessage not allow empty or null");
		getJmsTemplate().send(destinationName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				logger.debug("send text msg: {}",textMessage);
				return session.createTextMessage(textMessage);
			}
		});
	}

	@Override
	public <V> void sendMapMessage(String destinationName, final Map<String, V> map) {
		Assert.notNull(map,"map is null");
		getJmsTemplate().send(destinationName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				for (Map.Entry<String, V>  entry :map.entrySet()) {
					mapMessage.setObject(entry.getKey(), entry.getValue());
				}
				logger.debug("send map msg: {}",map);
				return mapMessage;
			}
		});
	}

	@Override
	public  void sendObjectMessage(String destinationName, final Serializable objMessage) {
		Assert.notNull(objMessage,"object is null");
		getJmsTemplate().send(destinationName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				logger.debug("send object msg: {}",objMessage);
				return session.createObjectMessage(objMessage);
			}
		});
	}

	@Override
	public void sendByteMessage(String destinationName,final byte[] bytes) {
		Assert.notNull(bytes,"bytesMessage is null");
		getJmsTemplate().send(destinationName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				logger.debug("send bytes msg: {}",bytes);
				BytesMessage bytesMessage = session.createBytesMessage();
				bytesMessage.writeBytes(bytes);
				return bytesMessage;
			}
		});
	}
}
