package com.dalrada.role.process.requestBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dalrada.role.integration.beans.IntgRequest;
import com.dalrada.role.process.beans.ProcessRequest;

@Component
public class ProcessRequestBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ProcessRequestBuilder.class);
	public IntgRequest buildRequest(ProcessRequest processRequest) {
		logger.debug("enter into buildRequest method");
		IntgRequest intgRequest  = new IntgRequest();
		intgRequest.setRoleName(processRequest.getRoleName());
		intgRequest.setStatus(processRequest.getStatus());
		intgRequest.setCreatedBy(processRequest.getCreatedBy());
		logger.debug("exit from buildRequest method");
		return intgRequest;
	}

}
