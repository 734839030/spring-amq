package net.lbtech.mq.demo;

import java.io.IOException;
import java.util.HashMap;

import net.lbtech.mq.sender.QueueSender;
import net.lbtech.mq.sender.TopicSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 演示真实使用一次发送多个时的事务  如果和mysql 做jta的事务 则可以直接调用QueueSender  TopicSender
 * @author DF
 *
 */
@Component
@Transactional(transactionManager="jmsTransactionManager")
public class TestJMSTransaction {

	@Autowired
	private QueueSender queueSender;
	@Autowired
	private TopicSender topicSender;
	
	public void sendMore(){
		queueSender.sendTextMessage("test.queue", "收不到消息");
		System.out.println(1/0);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("1", "map message test");
		queueSender.sendMapMessage("test.queue", map);
		queueSender.sendObjectMessage("test.queue", map);
	}
}
