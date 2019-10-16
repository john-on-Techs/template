package com.creativityskills.john.on.techs.fees.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_payments")
@NamedQueries({
        @NamedQuery(
                name = "STUDENT_PAYMENTS",
                query = "SELECT SUM (p.paymentAmount)  from Payment p where p.studentId=:studentId and p.fee.feeType=:feeType"
        ),
        @NamedQuery(
                name = "FEE_STATEMENT",
                query = "SELECT p FROM Payment p where p.studentId=:studentId"
        ),
        @NamedQuery(
                name = "ALL_PAYMENTS",
                query = "SELECT p FROM Payment p"
        )

})
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="student_id")
    private long studentId;
    @ManyToOne
    private Fee fee;
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    private BigDecimal paymentAmount;
    private String referenceNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", fee=" + fee +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                ", referenceNumber='" + referenceNumber + '\'' +
                '}';
    }

}
