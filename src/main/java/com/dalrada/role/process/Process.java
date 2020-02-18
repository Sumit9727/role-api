package com.dalrada.role.process;

import java.util.List;

import com.dalrada.role.process.beans.ProcessRequest;
import com.dalrada.role.process.beans.ProcessResponse;
import com.dalrada.role.process.requestBuilder.ProcessRequestBuilder;
import com.dalrada.role.process.responseBuilder.ProcessResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalrada.role.integration.Integration;
import com.dalrada.role.integration.beans.IntgRequest;
import com.dalrada.role.integration.beans.IntgResponse;
import com.dalrada.role.integration.exception.BusinessException;
import com.dalrada.role.integration.exception.SystemException;

@Component
public class Process {

	ProcessRequestBuilder requestBuilder;
	Integration Intg;
	ProcessResponseBuilder responseBuilder;
	private static final Logger logger = LoggerFactory.getLogger(Process.class);
	@Autowired
	public Process(ProcessRequestBuilder requestBuilder, Integration intg,
				   ProcessResponseBuilder responseBuilder) {
		super();
		this.requestBuilder = requestBuilder;
		Intg = intg;
		this.responseBuilder = responseBuilder;
	}
	public ProcessResponse getRoleById(Long userId) throws BusinessException, SystemException {
		logger.debug("enter into getRoleById method");
		IntgResponse intgResponse = Intg.getRoleById(userId);
		ProcessResponse processResponse = responseBuilder.buildResponse(intgResponse);
		logger.debug("exit from getRoleById method");
		return processResponse;
	}
	public List<ProcessResponse> getAllRole() throws BusinessException, SystemException {
		logger.debug("enter into getAllRoles method");
		List<IntgResponse> intgRespList = Intg.getAllRoles();
		List<ProcessResponse> processRespList = responseBuilder.buildResponse(intgRespList);
		logger.debug("exit from getAllRoles method");
		return processRespList;
	}


	public ProcessResponse createRole(ProcessRequest processRequest) throws BusinessException, SystemException {
		logger.debug("enter into createRole method");
		IntgRequest intgRequest = requestBuilder.buildRequest(processRequest);
		IntgResponse intgResponse = Intg.createRole(intgRequest);
		ProcessResponse processResponse = responseBuilder.buildResponse(intgResponse);
		logger.debug("exit from createRole method");
		return processResponse;
	}

	public ProcessResponse editRole(ProcessRequest processRequest) throws BusinessException, SystemException {
		logger.debug("enter into editRole method");
		IntgRequest intgRequest = requestBuilder.buildRequest(processRequest);
		IntgResponse intgResponse = Intg.editRole(intgRequest);
		ProcessResponse processResponse = responseBuilder.buildResponse(intgResponse);
		logger.debug("exit from editRole method");
		return processResponse;
	}

    public ProcessResponse changeStatus(long roleId,int status) throws BusinessException, SystemException {
		logger.debug("enter into changeStatus method");
		IntgResponse intgResponse = Intg.changeStatus(roleId,status);
		ProcessResponse processResponse = responseBuilder.buildResponse(intgResponse);
		logger.debug("exit from changeStatus method");
		return processResponse;
    }
}
