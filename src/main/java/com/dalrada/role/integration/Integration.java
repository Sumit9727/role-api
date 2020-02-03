package com.dalrada.role.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalrada.role.integration.beans.IntgRequest;
import com.dalrada.role.integration.beans.IntgResponse;
import com.dalrada.role.integration.entity.RoleEntity;
import com.dalrada.role.integration.exception.BusinessException;
import com.dalrada.role.integration.exception.SystemException;
import com.dalrada.role.integration.repository.RoleRepository;
import com.dalrada.role.integration.requestBuilder.IntgRequestBuilder;
import com.dalrada.role.integration.responseBuilder.IntgResponseBuilder;

@Component
public class Integration {

	IntgRequestBuilder requestBuilder ;
	RoleRepository repository ;
	IntgResponseBuilder responseBuilder ;
	private static final Logger logger = LoggerFactory.getLogger(Integration.class);
	@Autowired
	public Integration(IntgRequestBuilder requestBuilder, RoleRepository repository,
					   IntgResponseBuilder responseBuilder) {
		super();
		this.requestBuilder = requestBuilder;
		this.repository = repository;
		this.responseBuilder = responseBuilder;
	}

	public IntgResponse getUserById(Long userId) throws BusinessException, SystemException {
		logger.debug("enter into getUserById method");
		RoleEntity role;
		try {
			role = repository.findById(userId).get();
		} catch (RuntimeException e) {
			logger.error("Exception occured due to " ,e);
			throw new SystemException("","");	
		}
		if(role != null) {
			IntgResponse intgResponse = responseBuilder.buildResponse(role);
			logger.debug("exit from getUserById method");
			return intgResponse;
		}
		else
			throw new BusinessException("505","data not persent");
	}

	public List<IntgResponse> getAllUsers() throws BusinessException, SystemException {
		logger.debug("enter into getAllUsers method");
		List<RoleEntity> userList;
		try {
			userList = repository.findAll();
		} catch (RuntimeException e) {
			logger.error("Exception occured  " ,e);
			throw new SystemException("","");	
		}
		List<IntgResponse> responseList = new ArrayList<IntgResponse>();
		if(userList != null) {
			userList.forEach(user ->{
				IntgResponse intgResponse = responseBuilder.buildResponse(user);
				responseList.add(intgResponse);
			});
			logger.debug("exit from getAllUsers method");
			return responseList;
		}
		else
			throw new BusinessException("505","data not persent");
	}
	public IntgResponse createUser(IntgRequest intgRequest) throws BusinessException, SystemException {
		logger.debug("enter into createUser method");
		RoleEntity user = requestBuilder.buildRequest(intgRequest);
		RoleEntity entity;
		try {
			entity = repository.save(user);
		} catch (RuntimeException e) {
			logger.error("Exception occured  " ,e);
			throw new SystemException("","");
		}
		if(entity != null) {
			IntgResponse intgResponse = responseBuilder.buildResponse(user);
			logger.debug("exit from getAllUsers method");
			return intgResponse;
		}
		else
			throw new BusinessException("505","data not saved");
	}

	public IntgResponse editUser(IntgRequest intgRequest) throws BusinessException, SystemException {
		logger.debug("enter into editUser method");
		RoleEntity user = null ;
		user = requestBuilder.buildRequest(intgRequest);
		String roleName = user.getRoleName();
		Integer status= user.getStatus() ;
		String createdBy = user.getCreatedBy() ;
		Long roleId = user.getRoleId();
		try {
			repository.UpdateRole(roleName , roleId );
		} catch (RuntimeException e) {
			logger.error("Exception occured  " ,e);
			throw new SystemException("","");
		}
		catch (Exception e) {
			throw new BusinessException("","");
		}
		IntgResponse intgResponse = responseBuilder.buildResponse("200","user record successfully updated");
		logger.debug("exit from editUser method");
		return intgResponse;
	}

	public IntgResponse changeStatus(IntgRequest intgRequest) throws BusinessException, SystemException {
		logger.debug("enter into changeStatus method");
		RoleEntity user = null ;
		user = requestBuilder.buildRequest(intgRequest);
		Integer status= user.getStatus();
		Long roleId = user.getRoleId();
		try {
			repository.ChangeStatus(status , roleId );
		} catch (RuntimeException e) {
			logger.error("Exception occurred  " ,e);
			throw new SystemException("","");
		}
		catch (Exception e) {
			throw new BusinessException("","");
		}
		IntgResponse intgResponse = responseBuilder.buildResponse("200","user record successfully updated");
		logger.debug("exit from changeStatus method");
		return intgResponse;
	}
}
