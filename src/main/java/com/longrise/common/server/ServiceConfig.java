package com.longrise.common.server;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

import com.longrise.common.server.auth.BaseAuth;
import com.longrise.common.server.auth.SessionAuth;

@Target({ java.lang.annotation.ElementType.METHOD })  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface ServiceConfig{
	//public String logic();
    public String version();
    public String service();
    public boolean Monitored() default false;
    public boolean candidate() default false;
    public boolean stateless() default false;
    public Class<? extends BaseAuth> authmethod() default SessionAuth.class;
    public boolean authorizer() default false;
    public Class<? extends BaseAuth> permitmethod() default SessionAuth.class;
}
