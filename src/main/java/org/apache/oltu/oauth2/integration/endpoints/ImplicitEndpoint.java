package org.apache.oltu.oauth2.integration.endpoints;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * 简化模式Demo
 * @author Administrator
 *
 */
@Path("/implicit")
public class ImplicitEndpoint
{
    @POST
    public Response authorize(@Context HttpServletRequest request)
    {
        return null;
    }
    /**
     * 认证方法
     * @param request
     * @return
     */
    public String authorizer()
    {
        return null;
    }
    /**
     * 
     * @return
     */
    public Response resourceServer()
    {
        return null;
    }
}
