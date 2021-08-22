package pl.kamil.springbootrabbitmqpublisher;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kamil.model.Order;

import static pl.kamil.springbootrabbitmqpublisher.RabbitMQConfiguration.ROUTING_A;

@Service
class RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;

    @Autowired
    RabbitMQPublisher(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    void publishToA(Order order) {
        rabbitTemplate.convertAndSend(exchange.getName(), ROUTING_A, order); // in case of FanoutExchange routingKey should be empty
    }
}
