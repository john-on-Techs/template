package com.creativityskills.john.on.techs.fees.beans;



import com.creativityskills.john.on.techs.fees.model.FeeType;

import javax.ejb.Local;
import java.math.BigDecimal;
@Local
public interface DueI {
    BigDecimal getDueAmount(String registrationNumber, FeeType feeType);
}
