package com.dalrada.role.integration.requestBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dalrada.role.integration.beans.IntgRequest;
import com.dalrada.role.integration.entity.RoleEntity;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class IntgRequestBuilder {
	private static final Logger logger = LoggerFactory.getLogger(IntgRequestBuilder.class);
	public RoleEntity buildRequest(IntgRequest intgRequest) {
		logger.debug("enter into buildRequest method");
		RoleEntity entity  = new RoleEntity();
		entity.setRoleName(intgRequest.getRoleName());
		entity.setStatus(intgRequest.getStatus());
		entity.setCreatedBy(intgRequest.getCreatedBy());
		LocalDate currentDate = LocalDate.now();
		entity.setCreatedDate(Date.valueOf(currentDate));
		logger.debug("exit from buildRequest method");
		return entity;
	}

	public RoleEntity buildRequest(RoleEntity entity,IntgRequest intgRequest){
		logger.debug("enter into editRole method");
		entity.setRoleName(intgRequest.getRoleName());
		logger.debug("exit from editRole method");
		return entity;
	}


}
