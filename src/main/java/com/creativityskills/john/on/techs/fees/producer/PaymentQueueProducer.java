package com.creativityskills.john.on.techs.fees.producer;

import com.creativityskills.john.on.techs.fees.mdbs.PaymentCompletionListener;
import com.creativityskills.john.on.techs.fees.mdbs.PaymentQueueDefinition;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@ApplicationScoped
public class PaymentQueueProducer {
    @Inject
    private JMSContext context;
    @Inject
    private PaymentCompletionListener paymentCompletionListener;
    @Resource(mappedName = PaymentQueueDefinition.PAYMENT_RECEIVED_QUEUE)
    private Queue syncQueue;


    public void sendMessage(String txt) {
        context.createProducer()
                .setAsync(paymentCompletionListener)
                .send(syncQueue, txt);
    }
}