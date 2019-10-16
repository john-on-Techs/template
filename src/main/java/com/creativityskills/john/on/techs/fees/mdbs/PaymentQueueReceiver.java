package com.creativityskills.john.on.techs.fees.mdbs;


import org.jboss.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;




@MessageDriven(name = "PaymentQueueReceiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue =PaymentQueueDefinition.PAYMENT_RECEIVED_QUEUE),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),}
)

public class PaymentQueueReceiver implements MessageListener {
    @Inject
    private Logger logger;
    @Override
    public void onMessage(Message message) {
        try {
            final String text = message.getBody(String.class);
            logger.info("Received message: " + text);
        } catch (JMSException ex) {
            logger.error("Failed", ex);
        }
    }
}