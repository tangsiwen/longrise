package com.longrise.inf;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import net.sf.json.JSONObject;

import com.longrise.common.bean.PR;

public interface IDemo
{
    /**
     * 授权接口演示
     * @return
     */
    public PR Demo1(JSONObject json, HttpServletRequest request);
}
