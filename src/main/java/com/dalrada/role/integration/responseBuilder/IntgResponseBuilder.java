package com.dalrada.role.integration.responseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dalrada.role.integration.beans.IntgResponse;
import com.dalrada.role.integration.entity.RoleEntity;

@Component
public class IntgResponseBuilder {
	private static final Logger logger = LoggerFactory.getLogger(IntgResponseBuilder.class);
	public IntgResponse buildResponse(RoleEntity entity) {
		logger.debug("enter into buildResponse method");
		IntgResponse response = new IntgResponse();
		response.setResponseCode("200");
		response.setResponseMsg("successfull");
		response.setRespBody(entity);
		logger.debug("exit from buildResponse method");
		return response;		
	}

	public IntgResponse buildResponse(String responseCode, String respMsg) {
		logger.debug("enter into buildResponse method");
		IntgResponse response = new IntgResponse();
		response.setResponseCode(responseCode);
		response.setResponseMsg(respMsg);
		logger.debug("exit from buildResponse method");
		return response;	

	}

}
