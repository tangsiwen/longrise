package com.longrise.common.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;

import com.longrise.common.server.ServiceConfig;
import com.longrise.common.server.ServiceInfo;
import com.longrise.common.server.auth.BaseAuth;
import com.longrise.common.utils.FormatLog;

public class AfterAdvice implements AfterReturningAdvice
{
    final Logger Log = LoggerFactory.getLogger(AfterAdvice.class);
    public void afterReturning ( Object returnValue , Method method , Object[] args , Object target ) throws Throwable
    {
        Log.info(FormatLog.format(String.format("%s准备调用", method.getName())));
        ServiceConfig sc = ServiceInfo.getServiceInfo(method.getName());
        boolean authorizer = sc.authorizer();
        if (authorizer) {
            // 授权
            Log.info(String.format("%s--开始授权", method.getName()));
            BaseAuth grant = null;
            try {
                grant = sc.permitmethod().newInstance();
            } catch (Exception e) {
                Log.error(FormatLog.format("实例化授权方法对象异常",e));
            }
            if (grant != null) {
                if (grant.isGrant(returnValue)) {
                    grant.grant(args);
                } else {
                    Log.info(method.getName() + "--授权失败");
                }
            }
        }
        Log.info(String.format("%s结束调用",method.getName()));
    }
}
