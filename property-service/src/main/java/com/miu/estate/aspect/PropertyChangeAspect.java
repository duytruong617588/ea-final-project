package com.miu.estate.aspect;

import com.miu.estate.model.FavoriteProperty;
import com.miu.estate.repository.FavoritePropertyRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Aspect
@Component
public class PropertyChangeAspect {
    private final RabbitTemplate rabbitTemplate;
    private final FavoritePropertyRepository favoritePropertyRepository;

    @After("@annotation(PropertyAnnotation)")
    public void afterPropertyChange(JoinPoint joinPoint) {
        System.out.println("Property has been changed");
        Long propertyId = null;
        if (joinPoint.getArgs().length > 0) {
            propertyId = (Long) joinPoint.getArgs()[0];
        }
        List<FavoriteProperty> favoriteProperties = favoritePropertyRepository.findAllByPropertyId(propertyId);
        StringBuilder favoritePropertiesString = new StringBuilder();
        favoriteProperties.forEach(favoriteProperty -> {
            favoritePropertiesString.append("UserId:" + favoriteProperty.getUserId()).append(" => PropertyId:" + favoriteProperty.getPropertyId()).append("\n");

        });
        rabbitTemplate.convertAndSend("propery-fanout-exchange",
                "",
                favoritePropertiesString.toString());
    }
}
