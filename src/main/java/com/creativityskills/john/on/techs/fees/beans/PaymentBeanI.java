package com.creativityskills.john.on.techs.fees.beans;

import com.creativityskills.john.on.techs.fees.model.Payment;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PaymentBeanI extends BeanI<Payment> {
    List<Payment> getPayments(String registrationNumber);

    List<Payment> getPaymentsList();
}

