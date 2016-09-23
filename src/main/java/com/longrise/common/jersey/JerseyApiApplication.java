package com.longrise.common.jersey;

import org.glassfish.jersey.server.ResourceConfig;

import com.longrise.common.jersey.filter.AuthRequestFilter;
import com.longrise.common.jersey.filter.AuthResponseFilter;

public class JerseyApiApplication extends ResourceConfig
{
    public JerseyApiApplication()
    {
        register(AuthRequestFilter.class);
        register(AuthResponseFilter.class);
    }
}
