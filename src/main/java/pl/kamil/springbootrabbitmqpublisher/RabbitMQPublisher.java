package pl.kamil.springbootrabbitmqpublisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;
    private static final String QUEUE_NAME = "orders";

    @Autowired
    RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    void publish(String order) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, order);
    }


}
