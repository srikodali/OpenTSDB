package com.eruvaka.opentsdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtsdbWriteMethods
{
	String dataPoint;
	String dataPoint3;
	CloseableHttpClient httpclient;
	HttpPost httppostreq;
	Long time1 = (Long)new java.util.Date().getTime();
	Logger logger = LoggerFactory.getLogger(OtsdbWriteMethods.class);
	
	//passing values to variables in class OtsdbWriteVariables through constructor
	OtsdbWriteVariables otsdbwv = new OtsdbWriteVariables("temp2",time1,40,"eruvaka001");
	//OtsdbWriteVariables otsdbwv = new OtsdbWriteVariables();
	String id = otsdbwv.getDevice_id();
	
	//method creating json object
	public String buildjson()
	{
		JSONObject jsonobj = new JSONObject();
		
			jsonobj.put("metric", otsdbwv.getMetric_name());
			jsonobj.put("timestamp", otsdbwv.getTimestamp());
			//jsonobj.put("timestamp", "1405918347128"); //hard coded value for timestamp
			jsonobj.put("value", otsdbwv.getValue());	
			
			JSONArray tag = new JSONArray();
			
			tag.put("deviceID\": \"eruvaka001");
			tag.put("place\": \"place_name2");
								     
		    jsonobj.put("tags", tag);
		      
		    /*dataPoint = jsonobj.toString().replace("[", "{").replace("]", "}").replace("\\", "");
		    
		    System.out.println(dataPoint);*/
		    
		    
		     dataPoint3 = jsonobj.toString().replace("[", "{").replace("]", "}").replace("\\", "");
		      System.out.println(dataPoint3);
		    
		    return dataPoint3;
	}
	
	//method to make connection to OTSDB and post data
	public void buildHttpReq() 
	{
		String url = "http://ec2-54-179-168-204.ap-southeast-1.compute.amazonaws.com:4242/api/put";
		httpclient = HttpClients.createDefault();
		httppostreq = new HttpPost(url);
		httppostreq.setHeader("content-type", "application/json");
		
	}
		
	public void putMetrics() 
	{
		StringEntity se;
	    try 
	    {
			//se = new StringEntity(dataPoint);
			se = new StringEntity(dataPoint3);
			se.setContentType("application/json");//;charset=UTF-8		
			httppostreq.setEntity(se);
		    HttpResponse httpresponse;
		    httpresponse = httpclient.execute(httppostreq);
		    BufferedReader rd = new BufferedReader(new InputStreamReader(httpresponse.getEntity().getContent()));
		    String line = "";
		    while ((line = rd.readLine()) != null) 
		    	{
		           
		    	System.out.println(line);
		    
		        }
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

}
