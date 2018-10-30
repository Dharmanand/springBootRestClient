package com.kd.rest.restClient.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

	@JsonProperty
    private Long rxSessionIdentifier;
    private Map<String, Object> optional = new HashMap<>();
    public Data() { // empty public constructor is required
    }
    // getters/setters for all properties omitted for brevity
    @JsonAnySetter
    public void addOptional(String name, Object value) {
        optional.put(name, value);
    }
    @JsonAnyGetter
    public Object getOptional(String name) {
        return optional.get(name);
    }
    
	public Long getRxSessionIdentifier() {
		return rxSessionIdentifier;
	}
	public void setRxSessionIdentifier(Long rxSessionIdentifier) {
		this.rxSessionIdentifier = rxSessionIdentifier;
	}
	public Map<String, Object> getOptional() {
		return optional;
	}
	public void setOptional(Map<String, Object> optional) {
		this.optional = optional;
	}
	
    
}
