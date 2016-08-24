package net.lbtech.mq.sender;

import java.io.Serializable;
import java.util.Map;


/**
 * 通用发送接口 JMS 五种数据类型 stream 不常用  忽略
 * @author DF
 *
 */
public interface Sender {
	/**
	 * 发送文本消息
	 * @param destinationName 目的地名
	 * @param textMessage 
	 */
	public void sendTextMessage(String destinationName, final String textMessage);
	/**
	 * 发送Map消息
	 * @param destinationName 目的地名
	 * @param textMessage 
	 */
	public <V> void sendMapMessage(String destinationName, final Map<String,V> map);
	/**
	 * 发送对象消息
	 * @param destinationName 目的地名
	 * @param textMessage 
	 */
	public void sendObjectMessage(String destinationName, final Serializable objMessage);
	/**
	 * 发送字节消息 谨慎使用大文件占用内存
	 * @param destinationName 目的地名
	 * @param textMessage 
	 */
	public void sendByteMessage(String destinationName, final byte[] bytes);
}
