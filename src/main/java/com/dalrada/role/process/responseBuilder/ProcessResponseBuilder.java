package com.dalrada.role.process.responseBuilder;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dalrada.role.integration.beans.IntgResponse;
import com.dalrada.role.process.beans.ProcessResponseBody;
import com.dalrada.role.process.beans.ProcessResponse;


@Component
public class ProcessResponseBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ProcessResponseBuilder.class);
	public ProcessResponse buildResponse(IntgResponse intgResponse) {
		logger.debug("enter into buildResponse method");
		ProcessResponse processResponse = new ProcessResponse();
		processResponse.setResponseCode(intgResponse.getResponseCode());
		processResponse.setResponseMsg(intgResponse.getResponseMsg());
		ProcessResponseBody responseBody = new ProcessResponseBody();
		responseBody.setCreatedBy(intgResponse.getRespBody().getCreatedBy());
		responseBody.setCreatedDate(intgResponse.getRespBody().getCreatedDate());
		responseBody.setStatus(intgResponse.getRespBody().getStatus());
		responseBody.setRoleId(intgResponse.getRespBody().getRoleId());
		responseBody.setRoleName(intgResponse.getRespBody().getRoleName());
		processResponse.setRespBody(responseBody);
		logger.debug("exit into buildResponse method");
		return processResponse;
	}
	
	
	
	
	
	public List<ProcessResponse> buildResponse(List<IntgResponse> intgRespList) {
		List<ProcessResponse> processRespList = new ArrayList<ProcessResponse>();
		intgRespList.forEach(intgResponse ->{
			ProcessResponse processResponse = new ProcessResponse();
			processResponse.setResponseCode(intgResponse.getResponseCode());
			processResponse.setResponseMsg(intgResponse.getResponseMsg());
			ProcessResponseBody responseBody = new ProcessResponseBody();
			responseBody.setCreatedBy(intgResponse.getRespBody().getCreatedBy());
			responseBody.setCreatedDate(intgResponse.getRespBody().getCreatedDate());
			responseBody.setStatus(intgResponse.getRespBody().getStatus());
			responseBody.setRoleId(intgResponse.getRespBody().getRoleId());
			responseBody.setRoleName(intgResponse.getRespBody().getRoleName());
			processResponse.setRespBody(responseBody);
			processRespList.add(processResponse);
		});
		return processRespList;
	}
}
