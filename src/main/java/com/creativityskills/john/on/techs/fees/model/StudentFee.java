package com.creativityskills.john.on.techs.fees.model;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tbl_student_fees")
@NamedQueries({
        @NamedQuery(
                name = "FIND_AMOUNT_FOR_STUDENT_FOR_FEE_TYPE",
                query = "SELECT s.fee.feeAmount FROM StudentFee s WHERE s.studentId=:studentId AND s.fee.feeType=:feeType"
        )
})
public class StudentFee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "student_id")
    private long studentId;
    @OneToOne
    private Fee fee;

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

    @Override
    public String toString() {
        return "StudentFee{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", fee=" + fee +
                '}';
    }
}
