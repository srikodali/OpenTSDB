package com.eruvaka.opentsdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class OtsdbRead 
{

	public static void main(String[] args) throws IOException 
	{
		CloseableHttpResponse response = null;
		try
		{
		   String url = "http://ec2-54-179-168-204.ap-southeast-1.compute.amazonaws.com:4242/api/query?start=1h-ago&m=sum:temp2{deviceID=*,place=*}";
		   
		   url = url.replace("{", "%7B").replace("}", "%7D");
		   System.out.println(url);
		   
		   CloseableHttpClient httpclient = HttpClients.createDefault();
		   HttpGet httpgetreq = new HttpGet(url);
		    response = httpclient.execute(httpgetreq);
	        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
	        String line = "";
	        while ((line = rd.readLine()) != null) 
	        {
	        	System.getProperty("line.separator");
	          System.out.println(line);
	        	
	        }
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			response.close();
		}

	}

}
