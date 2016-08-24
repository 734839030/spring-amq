package net.lbtech.mq;

import java.io.IOException;
import java.util.HashMap;

import net.lbtech.mq.demo.TestJMSTransaction;
import net.lbtech.mq.sender.QueueSender;
import net.lbtech.mq.sender.TopicSender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration(locations = {"classpath*:spring-context*.xml"})
//测试时候的事务
//@Transactional(transactionManager="jmsTransactionManager")
public class SpringTest {

	@Autowired
	private QueueSender queueSender;
	@Autowired
	private TopicSender topicSender;
	@Autowired
	private TestJMSTransaction testJMSTransaction;
	@Test
	public void testQueue(){
		queueSender.sendTextMessage("test.queue", "收不到消息");
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("1", "map message test");
		queueSender.sendMapMessage("test.queue", map);
		queueSender.sendObjectMessage("test.queue", map);
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testTopic(){
		topicSender.sendTextMessage("test.topic", "你好");
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("1", "map message test");
		topicSender.sendMapMessage("test.topic", map);
		topicSender.sendObjectMessage("test.topic", map);
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testTransaction(){
		testJMSTransaction.sendMore();
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catchW block
			e.printStackTrace();
		}
	}
}
