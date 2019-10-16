package com.creativityskills.john.on.techs.fees.api;

import com.google.gson.Gson;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class LoggingRestFilter implements ContainerRequestFilter,
        ContainerResponseFilter {

    @Inject
    private Logger logger;
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        logger.info(containerRequestContext.getMethod() +  " on " +containerRequestContext.getUriInfo().getPath());
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        logger.info(new Gson().toJson(containerResponseContext.getEntity()));

    }
}
