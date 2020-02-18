package com.dalrada.role.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.dalrada.role.integration.beans.IntgRequest;
import com.dalrada.role.integration.beans.IntgResponse;
import com.dalrada.role.integration.entity.RoleEntity;
import com.dalrada.role.integration.exception.BusinessException;
import com.dalrada.role.integration.exception.SystemException;
import com.dalrada.role.integration.repository.RoleRepository;
import com.dalrada.role.integration.requestBuilder.IntgRequestBuilder;
import com.dalrada.role.integration.responseBuilder.IntgResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Integration {

	IntgRequestBuilder requestBuilder ;
	RoleRepository repository ;
	IntgResponseBuilder responseBuilder ;
	private static final Logger logger = LoggerFactory.getLogger(Integration.class);
	@Autowired
	public Integration(IntgRequestBuilder requestBuilder, RoleRepository repository,
					   IntgResponseBuilder responseBuilder) {
		this.requestBuilder = requestBuilder;
		this.repository = repository;
		this.responseBuilder = responseBuilder;
	}

	public IntgResponse getRoleById(Long roleId) throws BusinessException, SystemException {
		logger.debug("enter into getRoleById method");
		RoleEntity role;
		try {
			role = repository.findById(roleId).get();
		} catch (RuntimeException e) {
			logger.error("Exception occurred due to " ,e);
			throw new SystemException("","");	
		}
		if(role != null) {
			IntgResponse intgResponse = responseBuilder.buildResponse(role);
			logger.debug("exit from getRoleById method");
			return intgResponse;
		}
		else
			throw new BusinessException("505","data not present");
	}

	public List<IntgResponse> getAllRoles() throws BusinessException, SystemException {
		logger.debug("enter into getAllRoles method");
		List<RoleEntity> roleList;
		try {
			roleList = repository.findAll();
		} catch (RuntimeException e) {
			logger.error("Exception occurred  " ,e);
			throw new SystemException("","");	
		}
		List<IntgResponse> responseList = new ArrayList<IntgResponse>();
		if(roleList != null) {
			roleList.forEach(role ->{
				IntgResponse intgResponse = responseBuilder.buildResponse(role);
				responseList.add(intgResponse);
			});
			logger.debug("exit from getAllRoles method");
			List<IntgResponse> activeResponseList = responseList.stream()
					.filter(response -> response.getRespBody().getStatus()==1)
					.collect(Collectors.toList());
			return activeResponseList;
		}
		else
			throw new BusinessException("505","data not present");
	}
	public IntgResponse createRole(IntgRequest intgRequest) throws BusinessException, SystemException {
		logger.debug("enter into createRole method");
		RoleEntity role = requestBuilder.buildRequest(intgRequest);
		RoleEntity entity;
		try {
			entity = repository.save(role);
		} catch (RuntimeException e) {
			logger.error("Exception occurred  " ,e);
			throw new SystemException("","");
		}
		if(entity != null) {
			IntgResponse intgResponse = responseBuilder.buildResponse(role);
			logger.debug("exit from getAllRoles method");
			return intgResponse;
		}
		else
			throw new BusinessException("505","data not saved");
	}

	public IntgResponse editRole(IntgRequest intgRequest) throws BusinessException, SystemException {
		logger.debug("enter into editRole method");
		RoleEntity role = null ;
		RoleEntity entity = null;
		Long roleId = intgRequest.getRoleId();
		try {
			entity = repository.findById(roleId).get();
			role = requestBuilder.buildRequest(entity,intgRequest);
			entity =repository.save(role);
		} catch (RuntimeException e) {
			logger.error("Exception occurred  " ,e);
			throw new SystemException("","");
		}
		catch (Exception e) {
			throw new BusinessException("","");
		}
		if(entity != null) {
			IntgResponse intgResponse = responseBuilder.buildResponse(role);
			logger.debug("exit from editRole method");
			return intgResponse;
		}
		else
			throw new BusinessException("505","data not updated");
	}

	public IntgResponse changeStatus(long roleId,int status) throws BusinessException, SystemException {
		logger.debug("enter into changeStatus method");
		RoleEntity entity = null;
		try {
			RoleEntity role = repository.findById(roleId).get();
			role.setStatus(status);
			entity =repository.save(role);
		} catch (RuntimeException e) {
			logger.error("Exception occurred  " ,e);
			throw new SystemException("","");
		}
		catch (Exception e) {
			throw new BusinessException("","");
		}
		if(entity != null) {
			IntgResponse intgResponse = responseBuilder.buildResponse(entity);
			logger.debug("exit from changeStatus method");
			return intgResponse;
		}
		else
			throw new BusinessException("505","Status not updated");
	}

}
