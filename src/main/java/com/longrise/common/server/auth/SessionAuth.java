package com.longrise.common.server.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longrise.common.bean.PR;
import com.longrise.common.utils.UUID;


public class SessionAuth extends BaseAuth {

	@Override
	public boolean auth(Object[] objects) {
		if(objects==null)
			return Boolean.FALSE;
		HttpServletRequest context = null;
		for(Object object:objects)
		{
			if("HttpServletRequest".equals(object.getClass().getName()))
			{
			   context = (HttpServletRequest) objects[objects.length-1];
			}
		}
		if (context == null)
			throw new RuntimeException("SessionAuth - HttpServletRequest为空");
		HttpSession session = context.getSession();
		if (session == null)
			return Boolean.FALSE;

		if (session.getAttribute(AUTH_KEY) != null)
			return Boolean.TRUE;
		else
			return Boolean.FALSE;

	}

	@Override
	public boolean grant(Object[] objects) {
		if(objects==null)
			return Boolean.FALSE;
		HttpServletRequest context = (HttpServletRequest) objects[objects.length-1];
		if (context == null)
			throw new RuntimeException("SessionAuth - HttpServletRequest为空");
		HttpSession session = context.getSession();
		if (session == null)
			throw new RuntimeException("SessionAuth - HttpSession为空");
		Object authKey = session.getAttribute(BaseAuth.AUTH_KEY);
		String uuid = UUID.UUID_32();
		if (authKey == null) {
			session.setAttribute(BaseAuth.AUTH_KEY, uuid);
			session.setMaxInactiveInterval(60 * 60);
			//grant.put(BaseAuth.AUTH_KEY, uuid);
		} else{}
			//grant.put(BaseAuth.AUTH_KEY, authKey);
		return true;
	}

	@Override
	public boolean isGrant(Object result) {
		try {
			PR pr = (PR) result;
			if (pr.getResultstate()==1)
				return Boolean.TRUE;
			else
				return Boolean.FALSE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

}
