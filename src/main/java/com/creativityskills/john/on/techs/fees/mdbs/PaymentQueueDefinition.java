package com.creativityskills.john.on.techs.fees.mdbs;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.jms.JMSConnectionFactoryDefinition;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = PaymentQueueDefinition.PAYMENT_RECEIVED_QUEUE,
                        interfaceName = "javax.jms.Queue",
                        destinationName = "payment-queue"
                ),
                @JMSDestinationDefinition(
                        name = PaymentQueueDefinition.PAYMENT_RECEIVED_TOPIC,
                        interfaceName = "javax.jms.Topic",
                        destinationName = "payment-topic"
                )
        }
)

@Stateless
@Startup
public class PaymentQueueDefinition {
    public static final String PAYMENT_RECEIVED_QUEUE = "java:/jms/queue/payment-queue";
    public static final String PAYMENT_RECEIVED_TOPIC = "java:/jms/topic/payment-topic";
}
