package pl.kamil.springbootrabbitmqpublisher;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    private static final String QUEUE_A = "queue.A";
    private static final String QUEUE_B = "queue.B";
    static final String ROUTING_A = "routing.A";
    static final String ROUTING_B = "routing.B";

    @Bean
    Queue queueA() {
        return new Queue(QUEUE_A, false);
    }

    @Bean
    Queue queueB() {
        return new Queue(QUEUE_B, false);
    }

    @Bean
    DirectExchange exchange() {
        //DirectExchange can be switched to FanoutExchange, TopicExchange, HeadersExchange, DefaultExchange
        //DirectExchange - Routes messages to a queue by matching a complete routing key
        //FanoutExchange - Routes messages to queues bound to it. FanoutExchange does not need a routingKey
        //TopicExchange - Routes messages to multiple queues by matching routing key, * wildcard expression can be used in routing key
        //HeadersExchange - Routes messages based on messages headers. HeadersExchange does not need a routingKey
        //DefaultExchange - Routes messages to a queues ny matching a queue name.
        return new DirectExchange("exchange.direct");
    }

    @Bean
    Binding bindingA(Queue queueA, DirectExchange exchange) {
        return BindingBuilder.bind(queueA)
                .to(exchange)
                .with(ROUTING_A);
    }

    @Bean
    Binding bindingB(Queue queueB, DirectExchange exchange) {
        return BindingBuilder.bind(queueB)
                .to(exchange)
                .with(ROUTING_B);
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
