package com.creativityskills.john.on.techs.fees.beans;

import com.creativityskills.john.on.techs.fees.interceptor.Logged;
import com.creativityskills.john.on.techs.fees.model.Fee;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
@Logged
public class FeeBean extends Bean<Fee>  implements FeeBeanI{
    @PostConstruct
    public void init(){
        this.entityClass = Fee.class;
    }
}
