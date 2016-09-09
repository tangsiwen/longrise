package com.longrise.common.bean;

public class LogMsg
{
    public String message;
    public String exception;
    public String getMessage ()
    {
        return message;
    }
    public void setMessage ( String message )
    {
        this.message = message;
    }
    public String getException ()
    {
        return exception;
    }
    public void setException ( String exception )
    {
        this.exception = exception;
    }
}
