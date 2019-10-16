package com.creativityskills.john.on.techs.fees.beans;

import com.creativityskills.john.on.techs.fees.interceptor.Logged;
import com.creativityskills.john.on.techs.fees.model.Payment;
import com.creativityskills.john.on.techs.fees.producer.PaymentQueueProducer;
import com.google.gson.JsonParser;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Local
@Logged
public class PaymentBean extends Bean<Payment> implements PaymentBeanI {
    @Inject
    private PaymentQueueProducer paymentQueueProducer;
    @PostConstruct
    public void init(){
        this.entityClass = Payment.class;
    }

    @Override
    public Payment create(Payment payment) {
        Payment payment1= super.create(payment);
        paymentQueueProducer.sendMessage("[JMS Message] Payment Received: " + payment.getPaymentAmount());
        return payment1;
    }

    @Override
    public List<Payment> getPayments(String  registrationNumber) {
       String studentJsonString = new Util().getStudentByRegistrationNumber(registrationNumber);
       if(studentJsonString != null){
           return this.entityManager
                   .createNamedQuery("FEE_STATEMENT",Payment.class)
                   .setParameter("studentId",new JsonParser().parse(studentJsonString).getAsJsonObject().get("id").getAsLong())
                   .getResultList();
       }
       return null;

    }

    @Override
    public List<Payment> getPaymentsList() {
        return this.entityManager
                .createNamedQuery("ALL_PAYMENTS",Payment.class)
                .getResultList();
    }

}
