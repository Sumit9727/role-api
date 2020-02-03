package com.dalrada.role.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dalrada.role.integration.exception.BusinessException;
import com.dalrada.role.integration.exception.SystemException;
import com.dalrada.role.process.Process;
import com.dalrada.role.process.beans.ProcessRequest;
import com.dalrada.role.process.beans.ProcessResponse;
import com.dalrada.role.resource.beans.ResourceRequest;
import com.dalrada.role.resource.beans.ResourceResponse;
import com.dalrada.role.resource.requestBuilder.ResourceRequestBuilder;
import com.dalrada.role.resource.responseBuilder.ResourceResponseBuilder;
import com.dalrada.role.resource.validator.RequestValidator;


@RestController
@RequestMapping(path = "/dalrada/role")
public class Resource {
	
	ResourceRequestBuilder requestBuilder;
	Process process;
	ResourceResponseBuilder responsebuilder;
	RequestValidator requestValidator ;
	private static final Logger logger = LoggerFactory.getLogger(Resource.class);
	@Autowired
	public Resource(ResourceRequestBuilder requestBuilder, Process process,
					ResourceResponseBuilder responsebuilder, RequestValidator requestValidator) {
		super();
		this.requestBuilder = requestBuilder;
		this.process = process;
		this.responsebuilder = responsebuilder;
		this.requestValidator = requestValidator;
	}

	@GetMapping("getRole")
	public ResourceResponse getUserById(Long userId) throws BusinessException, SystemException {
		logger.debug("enter into getUserById method");
		requestValidator.validate(userId);
		ProcessResponse processResponse = process.getUserById(userId);
		ResourceResponse resourceResponse = responsebuilder.buildResponse(processResponse);
		logger.debug("exit from getUserById method");
		return resourceResponse;	
	}
	
	@GetMapping("getAllRole")
	public List<ResourceResponse> getAllUsers() throws BusinessException, SystemException {
		logger.debug("enter into getAllUsers method");
		List<ProcessResponse> processRespList = process.getAllUser();
		List<ResourceResponse> resourceRespList = responsebuilder.buildResponse(processRespList);
		logger.debug("exit from getAllUsers method");
		return resourceRespList;	
	}
	@PostMapping("createRole")
	public ResourceResponse createUser(@RequestBody ResourceRequest resourceRequest) throws BusinessException, SystemException {
		logger.debug("enter into createUser method");
		requestValidator.validate(resourceRequest);
		ProcessRequest processRequest = requestBuilder.buildRequest(resourceRequest);
		ProcessResponse processResponse = process.createUser(processRequest);
		ResourceResponse resourceResponse = responsebuilder.buildResponse(processResponse);
		logger.debug("exit from createUsers method");
		return resourceResponse;	
	}
	@PostMapping("editRole")
	public ResourceResponse editUser(@RequestBody ResourceRequest resourceRequest) throws BusinessException, SystemException {
		logger.debug("enter into editUser method");
		requestValidator.validate(resourceRequest);
		ProcessRequest processRequest = requestBuilder.buildRequest(resourceRequest);
		ProcessResponse processResponse = process.editUser(processRequest);
		ResourceResponse resourceResponse = responsebuilder.buildResponse(processResponse);
		logger.debug("exit from editUser method");
		return resourceResponse;
	}
	@PostMapping("changeStatus")
	public ResourceResponse changeStatus(@RequestBody ResourceRequest resourceRequest)  throws BusinessException, SystemException {
		logger.debug("enter into changeStatus method");
		requestValidator.validate(resourceRequest);
		ProcessRequest processRequest = requestBuilder.buildRequest(resourceRequest);
		ProcessResponse processResponse = process.changeStatus(processRequest);
		ResourceResponse resourceResponse = responsebuilder.buildResponse(processResponse);
		logger.debug("exit from editUser method");
		return resourceResponse;
	}


	@GetMapping("healthStatus")
	public String getHealth() {
		return "Service is up and running";
	}

}

