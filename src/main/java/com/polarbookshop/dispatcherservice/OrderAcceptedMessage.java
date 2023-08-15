package com.polarbookshop.dispatcherservice;

public record OrderAcceptedMessage( //DTO containing the order identifier as a Long field
        Long orderId
) {
}
