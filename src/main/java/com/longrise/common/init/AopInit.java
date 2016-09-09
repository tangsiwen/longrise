package com.longrise.common.init;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.longrise.common.utils.Global;

public class AopInit implements Servlet
{

    public void destroy ()
    {
        // TODO Auto-generated method stub
        
    }

    public ServletConfig getServletConfig ()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public String getServletInfo ()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void init ( ServletConfig arg0 ) throws ServletException
    {
        
        ApplicationContext ctx = 
                new FileSystemXmlApplicationContext(Global.getWebRootRealPath() + "WEB-INF/applicationContext.xml");
        Global.setApplicationContext(ctx);
        // TODO Auto-generated method stub
        
    }

    public void service ( ServletRequest arg0 , ServletResponse arg1 ) throws ServletException , IOException
    {
        // TODO Auto-generated method stub
        
    }

}
