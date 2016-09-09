package com.longrise.common.utils;

import net.sf.json.JSONObject;

import com.longrise.common.bean.LogMsg;

public class FormatLog
{
    public static String format(String message)
    {
         //Logger Log = LoggerFactory.getLogger(clazz);
         LogMsg lm = new LogMsg();
         lm.setMessage(message);
         JSONObject json = JSONObject.fromObject(lm);
         return json.toString();
         //Log.info(json.toString());
    }
    public static String format(String message,Exception e)
    {
         //Logger Log = LoggerFactory.getLogger(clazz);
         LogMsg lm = new LogMsg();
         lm.setMessage(message);
         if(e.getMessage()==null || "".equals(e.getMessage()))
         {
             if(e.getCause()!=null)
             {
                 lm.setException(e.getCause().getMessage());
             }
         }
         else
         {
             lm.setException(e.getMessage()); 
         }
         
         JSONObject json = JSONObject.fromObject(lm);
         return json.toString();
         //Log.info(json.toString());
    }
}
