package com.dalrada.role.resource.exceptionHandler;

import com.dalrada.role.integration.exception.BusinessException;
import com.dalrada.role.integration.exception.SystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.dalrada.role.resource.beans.ResourceResponse;
import com.dalrada.role.resource.exception.InvalidRequestException;


@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidRequestException.class)
	public ResourceResponse invalidRequestExceptionHandler(InvalidRequestException ex){
		ResourceResponse response = new ResourceResponse();
		response.setResponseCode(ex.getErrorCode());
		response.setResponseMsg(ex.getErrorMsg());
		return response;	
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(SystemException.class)
	public ResourceResponse systemExceptionHandler(SystemException ex){
		ResourceResponse response = new ResourceResponse();
		response.setResponseCode(ex.getErrorCode());
		response.setResponseMsg(ex.getErrorMsg());
		return response;	
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
	public ResourceResponse businessExceptionHandler(BusinessException ex){
		ResourceResponse response = new ResourceResponse();
		response.setResponseCode(ex.getErrorCode());
		response.setResponseMsg(ex.getErrorMsg());
		return response;	
	}
	
}

