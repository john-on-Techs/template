package com.creativityskills.john.on.techs.fees.beans;


import com.creativityskills.john.on.techs.fees.interceptor.Logged;
import com.creativityskills.john.on.techs.fees.model.StudentFee;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
@Logged
public class StudentFeeBean extends Bean<StudentFee> implements StudentFeeBeanI {
    @PostConstruct
    public void init(){
        this.entityClass = StudentFee.class;
    }



}
