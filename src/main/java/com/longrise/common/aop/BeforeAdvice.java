package com.longrise.common.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import com.longrise.common.server.ServiceConfig;
import com.longrise.common.server.ServiceInfo;
import com.longrise.common.server.auth.BaseAuth;
import com.longrise.common.server.auth.exception.AuthorizationFailedException;
import com.longrise.common.utils.FormatLog;

public class BeforeAdvice implements MethodBeforeAdvice
{
    final Logger Log = LoggerFactory.getLogger(BeforeAdvice.class);
    public void before ( Method method , Object[] args , Object target ) throws Throwable
    {
        // 打印调用日志
        Log.info(FormatLog.format(String.format("%s准备调用", method.getName())));
        ContainerRequestContext crc = null;
        ServiceConfig sc = null;
        boolean authorizer = false;
        boolean stateless = false;
        for(Object arg:args)
        {
            if("org.glassfish.jersey.server.ContainerRequest".equals(arg.getClass().getName()))
                crc = (ContainerRequestContext)arg;
        }
        if(crc!=null)
        {
            sc = ServiceInfo.getServiceInfo((String)crc.getProperty("serviceName"));
            authorizer = sc.authorizer();
            stateless = sc.stateless();
        }
        //boolean bool = false;
        if(authorizer)
        {
            //Log.info(String.format("%s授权接口准备开始授权", method.getName()));
        }
        else{
            if (!stateless) {
                Log.info(String.format("开始鉴权", method.getName()));
                Class<? extends BaseAuth> authMethod = sc.authmethod();
                BaseAuth auth = null;
                try {
                    auth = authMethod.newInstance();
                } catch (Exception e) {
                    Log.error(FormatLog.format("实例化鉴权方法对象异常", e));
                } 
                if (auth != null) {
                    if (auth.auth(args)) {
                        Log.info(method.getName() + "--鉴权成功");
                    } else {
                        throw new AuthorizationFailedException("鉴权失败");  
                    }
                }
            } else {
                Log.info(String.format("无需鉴权", method.getName()));
            } 
        }
        //Log.info(FormatLog.format("AOP前置拦截器描述"));
        //Log.info(String.format("方法名：%s,target:%s", method.toGenericString(),target.toString()));
        //throw new AuthorizationFailedException("鉴权失败异常测试");
}
}
