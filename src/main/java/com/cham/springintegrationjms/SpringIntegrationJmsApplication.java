package com.cham.springintegrationjms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
/*@ImportResource({"common.xml","inboundGateway.xml","outboundGateway.xml", "inboundChannelAdapter.xml",
		"outboundChannelAdapter.xml", "aggregation.xml"})*/
public class SpringIntegrationJmsApplication  implements CommandLineRunner {

	private final static String[] configFilesAggregationDemo = {
			"common.xml",
			"aggregation.xml"

	};

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationJmsApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("    Loading Aggregation Demo...");
		final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configFilesAggregationDemo, SpringIntegrationJmsApplication.class);
		final MessageChannel stdinToJmsOutChannel = applicationContext.getBean("stdinToJmsOutChannel", MessageChannel.class);
		stdinToJmsOutChannel.send(MessageBuilder.withPayload("JMS test").build());
		stdinToJmsOutChannel.send(MessageBuilder.withPayload("kafka test").build());
	}
}
