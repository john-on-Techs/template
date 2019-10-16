package com.creativityskills.john.on.techs.fees.beans;

import com.creativityskills.john.on.techs.fees.model.FeeType;

import com.google.gson.JsonParser;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Stateless
@Local
public class Due implements DueI {
    @EJB
    private FeeBeanI feeBeanI;
    @EJB
    private PaymentBeanI paymentBeanI;
    @PersistenceContext(unitName = "pu")
    protected EntityManager entityManager;

    @Override
    public BigDecimal getDueAmount(String registrationNo, final FeeType feeType) {


        String studentJsonString = new Util().getStudentByRegistrationNumber(registrationNo);
        System.out.println(studentJsonString);
        long id = new JsonParser()
                .parse(studentJsonString)
                .getAsJsonObject()
                .get("id")
                .getAsLong();
        System.out.println(id);


        BigDecimal feesDue = BigDecimal.ZERO;
        if (studentJsonString !=null) {
            BigDecimal amountPaid = (BigDecimal) entityManager
                    .createNamedQuery("STUDENT_PAYMENTS")
                    .setParameter("studentId", new JsonParser().parse(studentJsonString).getAsJsonObject().get("id").getAsLong())
                    .setParameter("feeType", feeType)
                    .getSingleResult();

            BigDecimal amountToBePaid = (BigDecimal) entityManager
                    .createNamedQuery("FIND_AMOUNT_FOR_STUDENT_FOR_FEE_TYPE")
                    .setParameter("studentId",new JsonParser().parse(studentJsonString).getAsJsonObject().get("id").getAsLong())
                    .setParameter("feeType", feeType)
                    .getSingleResult();


            if (amountPaid != null && amountToBePaid != null) {
                feesDue = amountToBePaid.subtract(amountPaid);
            }
        }

        return feesDue;


    }

}
