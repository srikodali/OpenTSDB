package com.eruvaka.opentsdb;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtsdbWriteMain 
{
	
	public static void main(String[] args)
	{
		
		OtsdbWriteMethods otsdbwm = new OtsdbWriteMethods();
		otsdbwm.buildjson();
		otsdbwm.buildHttpReq();
		otsdbwm.putMetrics();

	}

}
