package com.joaopedro.ecommerce.payment.streaming;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

//Irá receber a mensagem do checkout, processar e colocar a resposta em outro tópico
public interface CheckoutProcessor {
    String INPUT = "checkout-created-output";
    String OUTPUT = "payment-paid-input";

    @Output(OUTPUT)
    MessageChannel output();

    @Input(INPUT)
    SubscribableChannel input();
}
