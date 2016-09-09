package com.longrise.logic;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import net.sf.json.JSONObject;

import com.longrise.common.bean.PR;
import com.longrise.inf.IDemo;

public class DemoLogic implements IDemo
{

    public PR Demo1 ( JSONObject json , HttpServletRequest request )
    {
        // TODO Auto-generated method stub
        return new PR(1,"aaaaaaaaaa",null);
    }

}
