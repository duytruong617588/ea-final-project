package edu.miu.notification;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}


	@RabbitListener(queues = {"propery-direct-queue-2"})
	public void bindToHiQueue1(String payload) {
		System.out.println("Notification Service Notifier:");
		System.out.println(payload);
	}

	@RabbitListener(queues = {"propery-direct-queue-1"})
	public void bindToDirectQueue1(String payload) {
		System.out.println(payload);
	}

}
