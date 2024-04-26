package com.miu.estate.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Aspect
@Component
public class PropertyChangeAspect {
    private final RabbitTemplate rabbitTemplate;
    @After("@annotation(PropertyAnnotation)")
    public void afterPropertyChange() {
        System.out.println("Property has been changed");
        rabbitTemplate.convertAndSend("direct-exchange",
                "q1-test",
                "Hi from direct 1:    " + LocalDateTime.now());
    }
}
