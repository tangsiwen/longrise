package com.longrise.common.server.auth.exception;

public class GrantFailedException
{
    /**
     * 授权失败原因
     */
    public String cause;
    public GrantFailedException()
    {
        
    }
    public GrantFailedException(String cause)
    {
        this.cause = cause;
    }
    public String getMessage(){
        return cause;
    }
}
