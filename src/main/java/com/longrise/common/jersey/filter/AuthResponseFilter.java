package com.longrise.common.jersey.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;

@PreMatching
public class AuthResponseFilter implements ContainerResponseFilter
{

    public void filter ( ContainerRequestContext arg0 , ContainerResponseContext arg1 ) throws IOException
    {
        // TODO Auto-generated method stub
        //System.out.println("AuthResponseFilter");
    }

}
