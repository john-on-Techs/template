package com.creativityskills.john.on.techs.fees.beans;

import javax.ejb.Local;

@Local
public interface BeanI<T> {
    T create(T t);
    T read(long id);
    T update(T t);
    T delete(T t);
}
