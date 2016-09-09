package org.apache.oltu.oauth2.integration.endpoints;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/success")
public class SuccessRedirectURI
{
    @GET
    public Response success(@Context HttpServletRequest request)
    {
        ResponseBuilder builder = Response.status(Response.Status.OK);
        String code = request.getParameter("code");
        String AccessToken = request.getParameter("access_token");
        String TokenType = request.getParameter("token_type");
        String ExpiresIn = request.getParameter("expires_in");
       
        builder.entity(String.format("验证成功：code:%s,access_token:%s,token_type:%s,expires_in:%s", code,AccessToken,TokenType,ExpiresIn));
        return builder.build();
        
    }
    
}
