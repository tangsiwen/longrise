package com.longrise.common.bean;

public class PR
{
    String resultdesc;
    Integer resultstate;
    Object result;
    public PR()
    {
        
    }
    public PR(Integer resultstate,String resultdesc,Object result)
    {
        this.resultstate = resultstate;
        this.resultdesc = resultdesc;
        this.result = result;
    }
    public String getResultdesc ()
    {
        return resultdesc;
    }
    public void setResultdesc ( String resultdesc )
    {
        this.resultdesc = resultdesc;
    }
    public Integer getResultstate ()
    {
        return resultstate;
    }
    public void setResultstate ( Integer resultstate )
    {
        this.resultstate = resultstate;
    }
    public Object getResult ()
    {
        return result;
    }
    public void setResult ( Object result )
    {
        this.result = result;
    }
}
