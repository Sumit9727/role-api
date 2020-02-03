package com.dalrada.role.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalrada.role.integration.Integration;
import com.dalrada.role.integration.beans.IntgRequest;
import com.dalrada.role.integration.beans.IntgResponse;
import com.dalrada.role.integration.exception.BusinessException;
import com.dalrada.role.integration.exception.SystemException;
import com.dalrada.role.process.beans.ProcessRequest;
import com.dalrada.role.process.beans.ProcessResponse;
import com.dalrada.role.process.requestBuilder.ProcessRequestBuilder;
import com.dalrada.role.process.responseBuilder.ProcessResponseBuilder;

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
	public ProcessResponse getUserById(Long userId) throws BusinessException, SystemException {
		logger.debug("enter into getUserById method");
		IntgResponse intgResponse = Intg.getUserById(userId);
		ProcessResponse processResponse = responseBuilder.buildResponse(intgResponse);
		logger.debug("exit from getUserById method");
		return processResponse;
	}
	public List<ProcessResponse> getAllUser() throws BusinessException, SystemException {
		logger.debug("enter into getAllUsers method");
		List<IntgResponse> intgRespList = Intg.getAllUsers();
		List<ProcessResponse> processRespList = responseBuilder.buildResponse(intgRespList);
		logger.debug("exit from getAllUsers method");
		return processRespList;
	}


	public ProcessResponse createUser(ProcessRequest processRequest) throws BusinessException, SystemException {
		logger.debug("enter into createUser method");
		IntgRequest intgRequest = requestBuilder.buildRequest(processRequest);
		IntgResponse intgResponse = Intg.createUser(intgRequest);
		ProcessResponse processResponse = responseBuilder.buildResponse(intgResponse);
		logger.debug("exit from createUser method");
		return processResponse;
	}

	public ProcessResponse editUser(ProcessRequest processRequest) throws BusinessException, SystemException {
		logger.debug("enter into editUser method");
		IntgRequest intgRequest = requestBuilder.buildRequest(processRequest);
		IntgResponse intgResponse = Intg.editUser(intgRequest);
		ProcessResponse processResponse = responseBuilder.buildResponse(intgResponse);
		logger.debug("exit from editUser method");
		return processResponse;
	}

    public ProcessResponse changeStatus(ProcessRequest processRequest) throws BusinessException, SystemException {
		logger.debug("enter into editUser method");
		IntgRequest intgRequest = requestBuilder.buildRequest(processRequest);
		IntgResponse intgResponse = Intg.changeStatus(intgRequest);
		ProcessResponse processResponse = responseBuilder.buildResponse(intgResponse);
		logger.debug("exit from editUser method");
		return processResponse;
    }
}
