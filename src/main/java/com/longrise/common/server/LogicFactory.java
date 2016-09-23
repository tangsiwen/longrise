package com.longrise.common.server;

import javax.ws.rs.container.ContainerRequestContext;

import com.longrise.common.utils.Global;

public class LogicFactory
{
    @SuppressWarnings( "unchecked" )
    public static <T> T getInstance(Class<T> clazz,String springApplicationId,String serviceName,ContainerRequestContext crc)
    {
        crc.setProperty("serviceName", serviceName);
        return (T)Global.getApplicationContext().getBean(springApplicationId);
    }
}
