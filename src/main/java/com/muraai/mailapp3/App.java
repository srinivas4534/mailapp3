package com.muraai.mailapp3;

import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;

public class App {

	public static MailMessage mailMessage;
	public static ApplicationContext context;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		context = new ClassPathXmlApplicationContext("file:src/main/resources/context.xml");
		
		DirectChannel inputChannel = context.getBean("receiveEmailChannel", DirectChannel.class);
		DirectChannel getChannel = context.getBean("getChannel", DirectChannel.class);
        //System.out.println(inputChannel);
        
        inputChannel.subscribe(new MessageHandler() {
			
			public void handleMessage(Message<?> arg0) throws MessagingException {
				// TODO Auto-generated method stub
				
				System.out.println("-----------------------in subscribe----------------------------");
				MimeMessage payload = (MimeMessage) arg0.getPayload();
				System.out.println("-----------------------after cast----------------------------");
				try {
					mailMessage = new MailMessage(payload.getSubject(),payload.getSentDate());
					DirectChannel dbChannel = context.getBean("printSqlResult", DirectChannel.class);
					dbChannel.send(new GenericMessage<MailMessage>(mailMessage));
					System.out.println(payload.getSentDate());
				} catch (javax.mail.MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
        
        getChannel.subscribe(new MessageHandler() {
			
			public void handleMessage(Message<?> arg0) throws MessagingException {
				System.out.println("in mail subscriber");
				
				
				
			}
		});

	}

}
