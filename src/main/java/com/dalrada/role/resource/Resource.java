package com.dalrada.role.resource;

import java.util.List;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "Role Service API")
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

	@GetMapping("getRole/{roleId}")
	@ApiOperation(value = "Get Role By Id API")
	public ResourceResponse getUserById(@PathVariable Long roleId) throws BusinessException, SystemException {
		logger.debug("enter into getRoleById method");
		requestValidator.validate(roleId);
		ProcessResponse processResponse = process.getRoleById(roleId);
		ResourceResponse resourceResponse = responsebuilder.buildResponse(processResponse);
		logger.debug("exit from getRoleById method");
		return resourceResponse;	
	}
	
	@GetMapping("getAllRole")
	@ApiOperation(value = "Get All Role API")
	public List<ResourceResponse> getAllRoles() throws BusinessException, SystemException {
		logger.debug("enter into getAllRoles method");
		List<ProcessResponse> processRespList = process.getAllRole();
		List<ResourceResponse> resourceRespList = responsebuilder.buildResponse(processRespList);
		logger.debug("exit from getAllRoles method");
		return resourceRespList;	
	}
	@PostMapping("createRole")
	@ApiOperation(value = "Role Creating API")
	public ResourceResponse createRole(@RequestBody ResourceRequest resourceRequest) throws BusinessException, SystemException {
		logger.debug("enter into createRole method");
		requestValidator.validate(resourceRequest);
		ProcessRequest processRequest = requestBuilder.buildRequest(resourceRequest);
		ProcessResponse processResponse = process.createRole(processRequest);
		ResourceResponse resourceResponse = responsebuilder.buildResponse(processResponse);
		logger.debug("exit from createRoles method");
		return resourceResponse;	
	}
	@PostMapping("editRole")
	@ApiOperation(value = "Role Editing API")
	public ResourceResponse editUser(@RequestBody ResourceRequest resourceRequest) throws BusinessException, SystemException {
		logger.debug("enter into editRole method");
		requestValidator.validate(resourceRequest);
		ProcessRequest processRequest = requestBuilder.buildRequest(resourceRequest);
		ProcessResponse processResponse = process.editRole(processRequest);
		ResourceResponse resourceResponse = responsebuilder.buildResponse(processResponse);
		logger.debug("exit from editRole method");
		return resourceResponse;
	}
	@PostMapping("changeStatus/{roleId}/{status}")
	@ApiOperation(value = "Status Creating API")
	public ResourceResponse changeStatus(@PathVariable long roleId, @PathVariable int status)  throws BusinessException, SystemException {
		logger.debug("enter into changeStatus method");
		ProcessResponse processResponse = process.changeStatus(roleId,status);
		ResourceResponse resourceResponse = responsebuilder.buildResponse(processResponse);
		logger.debug("exit from editUser method");
		return resourceResponse;
	}


	@GetMapping("healthStatus")
	@ApiOperation(value = "Health Checking API")
	public String getHealth() {

		return "Service is up and running";
	}

}

