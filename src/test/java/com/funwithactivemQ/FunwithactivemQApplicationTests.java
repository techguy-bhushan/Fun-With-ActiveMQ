package com.funwithactivemQ;

import com.funwithactivemQ.consumer.Consumer;
import com.funwithactivemQ.producer.Producer;
import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.apache.catalina.core.ApplicationContext;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunwithactivemQApplicationTests {

	@Autowired
	private static ApplicationContext applicationContext;

	@ClassRule
	public static EmbeddedActiveMQBroker broker = new EmbeddedActiveMQBroker();

	@Autowired
	private Producer sender;

	@Autowired
	private Consumer receiver;

	@Test
	public void testReceive() throws Exception {
		sender.send("Hello Spring JMS ActiveMQ!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		MatcherAssert.assertThat(receiver.getLatch().getCount(), CoreMatchers.equalTo(0l));
	}
}
