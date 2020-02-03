package com.dalrada.role.resource.responseBuilder;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dalrada.role.process.beans.ProcessResponse;
import com.dalrada.role.resource.beans.ResourceResponseBody;
import com.dalrada.role.resource.beans.ResourceResponse;

@Component
public class ResourceResponseBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ResourceResponseBuilder.class);
	
	public ResourceResponse buildResponse(ProcessResponse intgResponse) {
		logger.debug("enter into buildResponse method");
		ResourceResponse resourceResponse = new ResourceResponse();
		resourceResponse.setResponseCode(intgResponse.getResponseCode());
		resourceResponse.setResponseMsg(intgResponse.getResponseMsg());
		ResourceResponseBody responseBody = new ResourceResponseBody();
		responseBody.setCreatedBy(intgResponse.getRespBody().getCreatedBy());
		responseBody.setCreatedDate(intgResponse.getRespBody().getCreatedDate());
		responseBody.setStatus(intgResponse.getRespBody().getStatus());
		responseBody.setRoleId(intgResponse.getRespBody().getRoleId());
		responseBody.setRoleName(intgResponse.getRespBody().getRoleName());
		resourceResponse.setRespBody(responseBody);
		logger.debug("exit from buildResponse method");
		return resourceResponse;
	}
	
	public List<ResourceResponse> buildResponse(List<ProcessResponse> intgRespList) {
		logger.debug("enter into buildResponse method");
		List<ResourceResponse> processRespList = new ArrayList<ResourceResponse>();
		intgRespList.forEach(intgResponse ->{
			ResourceResponse resourceResponse = new ResourceResponse();
			resourceResponse.setResponseCode(intgResponse.getResponseCode());
			resourceResponse.setResponseMsg(intgResponse.getResponseMsg());
			ResourceResponseBody responseBody = new ResourceResponseBody();
			responseBody.setCreatedBy(intgResponse.getRespBody().getCreatedBy());
			responseBody.setCreatedDate(intgResponse.getRespBody().getCreatedDate());
			responseBody.setStatus(intgResponse.getRespBody().getStatus());
			responseBody.setRoleId(intgResponse.getRespBody().getRoleId());
			responseBody.setRoleName(intgResponse.getRespBody().getRoleName());
			resourceResponse.setRespBody(responseBody);
			processRespList.add(resourceResponse);
		});
		logger.debug("exit from buildResponse method");
		return processRespList;
	}
	
}
