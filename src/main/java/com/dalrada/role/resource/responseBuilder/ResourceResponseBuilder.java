package com.dalrada.role.resource.responseBuilder;

import java.util.ArrayList;
import java.util.List;

import com.dalrada.role.process.beans.ProcessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dalrada.role.resource.beans.ResourceResponseBody;
import com.dalrada.role.resource.beans.ResourceResponse;

@Component
public class ResourceResponseBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ResourceResponseBuilder.class);
	
	public ResourceResponse buildResponse(ProcessResponse processResponse) {
		logger.debug("enter into buildResponse method");
		ResourceResponse resourceResponse = new ResourceResponse();
		resourceResponse.setResponseCode(processResponse.getResponseCode());
		resourceResponse.setResponseMsg(processResponse.getResponseMsg());
		ResourceResponseBody responseBody = new ResourceResponseBody();
		responseBody.setCreatedBy(processResponse.getRespBody().getCreatedBy());
		responseBody.setCreatedDate(processResponse.getRespBody().getCreatedDate());
		responseBody.setStatus(processResponse.getRespBody().getStatus());
		responseBody.setRoleId(processResponse.getRespBody().getRoleId());
		responseBody.setRoleName(processResponse.getRespBody().getRoleName());
		resourceResponse.setRespBody(responseBody);
		logger.debug("exit from buildResponse method");
		return resourceResponse;
	}
	
	public List<ResourceResponse> buildResponse(List<ProcessResponse> processResponseListRespList) {
		logger.debug("enter into buildResponse method");
		List<ResourceResponse> processRespList = new ArrayList<ResourceResponse>();
		processResponseListRespList.forEach(processResponse ->{
			ResourceResponse resourceResponse = new ResourceResponse();
			resourceResponse.setResponseCode(processResponse.getResponseCode());
			resourceResponse.setResponseMsg(processResponse.getResponseMsg());
			ResourceResponseBody responseBody = new ResourceResponseBody();
			responseBody.setCreatedBy(processResponse.getRespBody().getCreatedBy());
			responseBody.setCreatedDate(processResponse.getRespBody().getCreatedDate());
			responseBody.setStatus(processResponse.getRespBody().getStatus());
			responseBody.setRoleId(processResponse.getRespBody().getRoleId());
			responseBody.setRoleName(processResponse.getRespBody().getRoleName());
			resourceResponse.setRespBody(responseBody);
			processRespList.add(resourceResponse);
		});
		logger.debug("exit from buildResponse method");
		return processRespList;
	}
	
}
