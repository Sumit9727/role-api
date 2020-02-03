package com.dalrada.role.resource.requestBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dalrada.role.process.beans.ProcessRequest;
import com.dalrada.role.resource.beans.ResourceRequest;

@Component
public class ResourceRequestBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ResourceRequestBuilder.class);
	public ProcessRequest buildRequest(ResourceRequest resourceRequest) {
		logger.debug("enter into buildRequest method");
		ProcessRequest processRequest  = new ProcessRequest();
		processRequest.setRoleName(resourceRequest.getRoleName());
		processRequest.setStatus(resourceRequest.getStatus());
		processRequest.setCreatedBy(resourceRequest.getCreatedBy());
		logger.debug("exit from buildRequest method");
		return processRequest;
	}
}
