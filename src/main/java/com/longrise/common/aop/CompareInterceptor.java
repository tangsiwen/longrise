package com.longrise.common.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CompareInterceptor implements MethodInterceptor
{

    public Object invoke ( MethodInvocation invocation ) throws Throwable
    {
        Object result = null;
        /**获取http request**/
        result = invocation.proceed();

        //System.out.println("CompareInterceptor");

        return result;
    }
}
