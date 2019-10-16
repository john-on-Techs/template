package com.creativityskills.john.on.techs.fees.mdbs;


import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.CompletionListener;
import javax.jms.Message;


@ApplicationScoped
public class PaymentCompletionListener implements CompletionListener {
    @Inject
    private Logger logger;

    public PaymentCompletionListener() {
    }

    @Override
    public void onCompletion(Message message) {
        try {
            final String text = message.getBody(String.class);
            logger.info("Send was successful: "+ text);
        } catch (Throwable e) {
            logger.error("Problem with message format",e);
        }
    }
    @Override
    public void onException(Message message, Exception exception) {
        try {
            final String text = message.getBody(String.class);
            logger.info("Send failed..." + text);
        } catch (Throwable e) {
            logger.error("Problem with message format",e);
        }
    }
}
