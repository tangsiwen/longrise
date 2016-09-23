package com.longrise.common.jersey.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.longrise.common.utils.FormatLog;

@PreMatching
public class AuthRequestFilter implements ContainerRequestFilter
{
    final static Logger Log = LoggerFactory.getLogger(AuthRequestFilter.class);
    public void filter ( ContainerRequestContext arg0 ) throws IOException
    {
        // TODO Auto-generated method stub
       UriInfo uri= arg0.getUriInfo();
//        System.out.println("AuthRequestFilter");
//        System.out.println("getBaseUri:"+uri.getBaseUri().toString());
//        System.out.println("getPath"+uri.getPath());
//        System.out.println("getAbsolutePath"+uri.getAbsolutePath().toString());
//        System.out.println("getRequestUri"+uri.getRequestUri().toString());
        Log.info(FormatLog.format(String.format("接收到请求路径：%s", uri.getPath())));
    }

}
