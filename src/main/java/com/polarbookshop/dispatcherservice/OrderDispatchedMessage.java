package com.polarbookshop.dispatcherservice;

public record OrderDispatchedMessage( //DTO containing the order identifier as a Long field
        Long orderId
) {
}
