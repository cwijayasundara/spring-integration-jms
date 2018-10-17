package com.cham.springintegrationjms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
@ImportResource({"common.xml", "aggregation.xml"})
public class SpringIntegrationJmsApplication  implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationJmsApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		final MessageChannel stdinToJmsOutChannel = appContext.getBean("stdinToJmsOutChannel", MessageChannel.class);
		String firstMsg = "<note>\n" +
				"<to>Tom</to>\n" +
				"<from>Phil</from>\n" +
				"<heading>Reminder</heading>\n" +
				"<body>Don't forget me this weekend!</body>\n" +
				"</note>";

		String secondMsg = "<note>\n" +
				"<to>Chaminda</to>\n" +
				"<from>Boo</from>\n" +
				"<heading>Warning</heading>\n" +
				"<body>Don't mess with me!</body>\n" +
				"</note>";

		stdinToJmsOutChannel.send(MessageBuilder.withPayload(firstMsg).build());
		stdinToJmsOutChannel.send(MessageBuilder.withPayload(secondMsg).build());
	}
}
