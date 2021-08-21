package pl.kamil.springbootrabbitmqpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    RabbitMQPublisher rabbitMQPublisher;

    @Autowired
    OrderController(RabbitMQPublisher rabbitMQPublisher) {
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    @GetMapping("/addOrder")
    String addOrder(@RequestParam String order) {
        rabbitMQPublisher.publish(order);
        return "Sent";
    }
}
