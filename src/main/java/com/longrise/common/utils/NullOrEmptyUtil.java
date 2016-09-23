package com.longrise.common.utils;

public class NullOrEmptyUtil
{
    //空类型判断工具类，期望返回值均为true，如果返回为false，建议检查相关代码
    /**
     * 对象是否为null或者空字符串
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty ( Object obj )
    {
        if ( obj == null )
            return true;
        if ( obj.toString().trim().equals("") )
            return true;
        return false;
    }

    /**
     * 对象不为null或空字符串
     * @param obj
     * @return
     */
    public static boolean isNotEmpty ( Object obj )
    {
        return !isNullOrEmpty(obj);
    }

    /**
     * 所有对象都为空或者空字符串        期望返回值为true
     * @param objs  object...
     * @return  
     */
    public static boolean isNullOrEmpty_all ( Object... objs )
    {
        //传递的值不为空
        if ( objs != null && objs.length > 0 )
        {
            int objsLen = objs.length;
            for (int i = 0; i < objsLen; i++)
            {
                Object obj = objs[i];
                if ( obj != null && !obj.toString().trim().equals("") )
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 所有对象全不为空         期望返回值为true
     * @param objs  object...
     * @return
     */
    public static boolean isNotEmpty_all ( Object... objs )
    {
        //传递的值不为空
        if ( objs != null && objs.length > 0 )
        {
            int objsLen = objs.length;
            for (int i = 0; i < objsLen; i++)
            {
                Object obj = objs[i];
                if ( null == obj || "".equals(obj.toString().trim()) )
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    /**
     * 
     * @author windyStreet
     * @function 所有对象中存在空
     * @date 2015年10月29日 下午1:44:24
     * @param objs 
     * @return
     */
    public static boolean isHasEmpty(Object... objs)
    {
    	 //传递的值不为空
        if ( objs != null && objs.length > 0 )
        {
            int objsLen = objs.length;
            int i = 0 ;
            for (; i < objsLen; i++)
            {
                Object obj = objs[i];
                if ( null == obj || "".equals(obj.toString().trim()) )
                {
                    return true;
                }
            }
            if(i >= objsLen)
            {
            	return false;
            }
        }
        //为传递值为空，默认返回true
        return true;
    	
    }


}
