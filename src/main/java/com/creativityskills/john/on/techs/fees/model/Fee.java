package com.creativityskills.john.on.techs.fees.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_fees")
@NamedQueries({
        @NamedQuery(
                name = "FEE_AMOUNT_BY_FEE_TYPE",
                query = "SELECT f.feeAmount FROM Fee  f WHERE f.feeType=:feeType"
        )
})
public class Fee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private FeeType feeType;
    private BigDecimal feeAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }


    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", feeType=" + feeType +
                ", feeAmount=" + feeAmount +
                '}';
    }
}
