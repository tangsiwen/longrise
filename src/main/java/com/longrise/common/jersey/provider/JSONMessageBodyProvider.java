package com.longrise.common.jersey.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Provider
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class JSONMessageBodyProvider implements MessageBodyWriter<Object> , MessageBodyReader<Object>
{

    public boolean isReadable ( Class<?> arg0 , Type arg1 , Annotation[] arg2 , MediaType arg3 )
    {
        return true;
    }

    public Object readFrom ( Class<Object> arg0 , Type arg1 , Annotation[] arg2 , MediaType arg3 ,
            MultivaluedMap<String, String> arg4 , InputStream arg5 ) throws IOException , WebApplicationException
    {
        //System.out.println("JSONMessageBodyProvider readFrom");
        BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(arg5, "UTF-8"));
        StringBuffer tStringBuffer = new StringBuffer();
        String sTempOneLine = new String("");
        JSONObject json = null;
        try
        {
            while ((sTempOneLine = tBufferedReader.readLine()) != null)
            {
                tStringBuffer.append(sTempOneLine);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            System.out.println(tStringBuffer.toString());
            json = JSONObject.fromObject(tStringBuffer.toString());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return json;
    }

    public long getSize ( Object arg0 , Class<?> arg1 , Type arg2 , Annotation[] arg3 , MediaType arg4 )
    {
        return -1;
    }

    public boolean isWriteable ( Class<?> arg0 , Type arg1 , Annotation[] arg2 , MediaType arg3 )
    {
        return true;
    }

    public void writeTo ( Object arg0 , Class<?> arg1 , Type arg2 , Annotation[] arg3 , MediaType arg4 ,
            MultivaluedMap<String, Object> arg5 , OutputStream arg6 ) throws IOException , WebApplicationException
    {
        OutputStreamWriter writer = new OutputStreamWriter(arg6, "UTF-8");
        try
        {
            JSONObject json = JSONObject.fromObject(arg0);
            writer.write(json.toString());
            writer.flush();
        }
        finally
        {
            writer.close();
        }
    }
}
