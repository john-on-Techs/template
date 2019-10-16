package com.creativityskills.john.on.techs.fees.interceptor;

import org.jboss.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@Logged
public class LoggingInterceptor implements Serializable {
    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        final Logger logger = Logger.getLogger(context.getTarget().getClass());
        logger.infov("Executing method {0}",context.getMethod().toString());
        return context.proceed();
    }
}