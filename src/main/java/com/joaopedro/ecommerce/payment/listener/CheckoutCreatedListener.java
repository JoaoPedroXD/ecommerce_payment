package com.joaopedro.ecommerce.payment.listener;

import com.joaopedro.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.joaopedro.ecommerce.checkout.event.PaymentCreatedEvent;
import com.joaopedro.ecommerce.payment.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CheckoutCreatedListener {
    private final CheckoutProcessor checkoutProcessor;

    @StreamListener(CheckoutProcessor.INPUT)
    //Processa a mensagem
    public void handler(CheckoutCreatedEvent event) {
        //Processa o pagamento, pode ser feito através de um gateway
        //Salva os dados do pagamento no banco de dados
        //Enviar o evento do pagamento realizado
        final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(event.getCheckoutCode())
                .setCheckoutStatus(event.getStatus())
                .setPaymentCode(UUID.randomUUID().toString())
                .build();
        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
    }
}
