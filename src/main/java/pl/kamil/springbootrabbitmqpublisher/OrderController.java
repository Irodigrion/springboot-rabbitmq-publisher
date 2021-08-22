package pl.kamil.springbootrabbitmqpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kamil.model.Order;

@RestController
public class OrderController {

    private final RabbitMQPublisher rabbitMQPublisher;

    @Autowired
    OrderController(RabbitMQPublisher rabbitMQPublisher) {
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order) {
        rabbitMQPublisher.publishToA(order);
        return "Sent";
    }
}
