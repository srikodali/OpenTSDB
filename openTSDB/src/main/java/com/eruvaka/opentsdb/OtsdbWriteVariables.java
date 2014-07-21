package com.eruvaka.opentsdb;

public class OtsdbWriteVariables 
{
	private String metric_name; //This value denotes what we want to record like temp,OH,PH
	private long timestamp;
	private double value;
	private String device_id;  //this tells for which device we are recording the metric_name. ex:metric_name=temp,device_id=eruvakapond1,value=30(this is temp value)
	
	//getters & setters to make the above fields public
	public String getMetric_name() {
		return metric_name;
	}
	public void setMetric_name(String metric_name) {
		this.metric_name = metric_name;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	
	//writing constructor to assign values from other class
	OtsdbWriteVariables(String metric, long time, int val, String deviceId)
	{
		this.metric_name = metric;
		this.timestamp = time;
		this.value = val;
		this.device_id = deviceId;
	}

}
