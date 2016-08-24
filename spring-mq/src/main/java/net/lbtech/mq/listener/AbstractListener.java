package net.lbtech.mq.listener;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 子类需要什么方法就复写什么方法
 * @author DF
 *
 */
public abstract class AbstractListener implements MessageListener{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 文本消息处理
	 * @param text
	 */
	public void textMessageHander(String text){
		logger.debug("text: {} " ,text );
	};
	/**
	 * map消息处理
	 * @param map
	 */
	public <V>  void mapMessageHander(Map<String,V> map){
		logger.debug("map: {} " ,map );
	}
	/**
	 * 对象消息处理
	 * @param obj
	 */
	public void objectMessageHander(Serializable obj){
		logger.debug("obj: {} " ,obj );
	}
	/**
	 * 字节消息处理  这里无法做转换 只能各自去实现，bytesMessage 有可能很大需要分步读
	 * 参考方法
	 * bytesMessage.getBodyLength() 获取总长度
	 * int len = -1;
		byte[] buff = new byte[1024];
		ByteArrayOutputStream os =new ByteArrayOutputStream();
		while ((len = bytesMessage.readBytes(buff)) != -1) {
			 os.write(buff, 0, len);
		}
		如果字节数较小 可以一次读入buff
	 * @param bytesMessage
	 */
	public void bytesMessageHander(BytesMessage bytesMessage){}
	@Override
	public void onMessage(Message message) {
		logger.debug("on message for {}",message);
		try {
			if (message instanceof TextMessage){//文本消息
				TextMessage textMessage = (TextMessage)message;
				textMessageHander(textMessage.getText());
			} else if (message instanceof MapMessage ) {//map 消息
				MapMessage mapMessage = (MapMessage)message;
				Map<String,Object> map = new HashMap<String, Object>();
				//所有的key
				@SuppressWarnings("unchecked")
				Enumeration<String> names = mapMessage.getMapNames();
				while (names.hasMoreElements()) {
					String key =  names.nextElement();
					map.put(key, mapMessage.getObject(key));
				}
				mapMessageHander(map);
			} else if (message instanceof ObjectMessage ) {//对象 消息
				ObjectMessage objectMessage = (ObjectMessage)message;
				objectMessageHander(objectMessage.getObject());
			} else if (message instanceof BytesMessage) {//bytes 消息
				BytesMessage bytesMessage = (BytesMessage)message;
				bytesMessageHander(bytesMessage);
			} else {
				logger.debug("do nothing for {}",message);
			}
		} catch (Exception e) {
			logger.error("on message exception for :{},异常原因：{}",message,e.getMessage());
			//只有抛出异常才有事务回滚
			throw new RuntimeException(e);
		}
	}
}
