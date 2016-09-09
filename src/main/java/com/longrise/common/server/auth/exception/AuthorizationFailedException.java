package com.longrise.common.server.auth.exception;

public class AuthorizationFailedException extends Exception
{
    /**
     * 鉴权失败原因
     */
    public String cause;
    public AuthorizationFailedException()
    {
        
    }
    public AuthorizationFailedException(String cause)
    {
        this.cause = cause;
    }
    public String getMessage(){
        return cause;
    }
}
