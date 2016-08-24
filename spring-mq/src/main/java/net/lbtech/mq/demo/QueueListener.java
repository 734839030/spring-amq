package net.lbtech.mq.demo;

import net.lbtech.mq.listener.AbstractListener;

import org.springframework.stereotype.Component;

/**
 * 按需复写父类方法，真实业务请直接继承AbstractListener
 * que 监听器
 * @author DF
 *
 */
@Component
public class QueueListener extends AbstractListener{
	
}
