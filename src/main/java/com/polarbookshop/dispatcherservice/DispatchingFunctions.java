package com.polarbookshop.dispatcherservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration //Functions are defined in a configuration class.
public class DispatchingFunctions {

    private static final Logger log = LoggerFactory.getLogger(DispatchingFunctions.class);

    @Bean //Functions defined as beans can be discovered and managed by Spring Cloud Function.
    public Function<OrderAcceptedMessage, Long> pack() { //Function implementing the order-packing business logic
        return orderAcceptedMessage -> { //It takes an OrderAcceptedMessage object as input.
            log.info("The order with id {} is packed.", orderAcceptedMessage.orderId());
            return orderAcceptedMessage.orderId(); //Returns an order identifier (Long) as output
        };
    }

    @Bean
    public Function<Flux<Long>, Flux<OrderDispatchedMessage>> label() { //Function implementing the order-labeling business logic
        return orderFlux -> orderFlux.map(orderId -> { //It takes an order identifier (Long) as input.
            log.info("The order with id {} is labeled.", orderId);
            return new OrderDispatchedMessage(orderId); //Returns an OrderDispatchedMessage as output
        });
    }
}
