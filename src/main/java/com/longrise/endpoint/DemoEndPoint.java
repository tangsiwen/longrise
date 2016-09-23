package com.longrise.endpoint;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.longrise.common.server.LogicFactory;
import com.longrise.common.server.ServiceConfig;
import com.longrise.common.server.auth.SessionAuth;
import com.longrise.common.utils.FormatLog;
import com.longrise.common.utils.Global;
import com.longrise.inf.IDemo;

import net.sf.json.JSONObject;

@Path( "/Demo" )
public class DemoEndPoint
{
    final Logger Log = LoggerFactory.getLogger(DemoEndPoint.class);

    /**
     * @param json
     * @param request
     * @return
     */
    @POST
    @Path( "/Demo1/1.0.1" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    @ServiceConfig( version = "1.0.1" , service = "Demo1" , stateless = true )
    public Response Demo1 ( JSONObject json , @Context HttpServletRequest request,@Context ContainerRequestContext crc )
    {
        ResponseBuilder builder = Response.noContent();
        try
        {
            IDemo demo = LogicFactory.getInstance(IDemo.class, "demo", "Demo1", crc);;
            builder.status(200);
            builder.entity(demo.Demo1(json, request));
        }
        catch (Exception e)
        {
            builder = Response.status(500);
            Log.error(FormatLog.format("Demo1接口异常", e));
        }
        return builder.build();
    }

    /**
     * 用户登陆，将登陆信息保存在session中
     * @param json
     * @param request
     * @return
     */
    @POST
    @Path( "/Login" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    @ServiceConfig( version = "1.0.1" , service = "Login1" , stateless = true , authorizer = true ,
            permitmethod = SessionAuth.class )
    public Response Login1 ( JSONObject json , @Context HttpServletRequest request ,@Context ContainerRequestContext crc)
    {
        ResponseBuilder builder = Response.noContent();
        try
        {
            IDemo demo = LogicFactory.getInstance(IDemo.class, "demo", "Login1", crc);
            builder.status(200);
            builder.entity(demo.Login1(json, request));
        }
        catch (Exception e)
        {
            builder = Response.status(500);
            Log.error(FormatLog.format("Demo1接口异常", e));
        }
        return builder.build();
    }

    /**
     * session鉴权接口测试
     * @param json
     * @param request
     * @return
     */
    @POST
    @Path( "/Demo2" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    @ServiceConfig( version = "1.0.1" , service = "Demo2" , stateless = false , authmethod = SessionAuth.class ,
            authorizer = false )
    public Response Demo2 ( JSONObject json , @Context HttpServletRequest request,@Context ContainerRequestContext crc )
    {
        ResponseBuilder builder = Response.noContent();
        try
        {
            IDemo demo = LogicFactory.getInstance(IDemo.class, "demo", "Demo2", crc);
            builder.status(200);
            builder.entity(demo.Demo2(json, request));
        }
        catch (Exception e)
        {
            builder = Response.status(500);
            Log.error(FormatLog.format("Demo1接口异常", e));
        }
        return builder.build();
    }
}
