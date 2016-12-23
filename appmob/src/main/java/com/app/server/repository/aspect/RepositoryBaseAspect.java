package com.app.server.repository.aspect;
import java.util.HashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.pluggable.logger.event.RuntimeLogUserInfo;

@Aspect
@Component
public class RepositoryBaseAspect extends RepositoryAspect {

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private EntityValidatorHelper<Object> entityValidator;

	/**
	 * Method to set multitenant 'tenant.id' value in runtimeLogInfoHelper
	 */
	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	public void setCustomerIdInEntityManager() {
		if (runtimeLogInfoHelper.getCustomerId() != "" && runtimeLogInfoHelper.getCustomerId() != null) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("tenant.id", runtimeLogInfoHelper.getCustomerId());
			emfResource.setResourceAttributes(map);
			runtimeLogInfoHelper.setMultitenantFields(map);
		}
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "appRepositoryOperation()")
	@Order(2)
	public Object aroundApp(ProceedingJoinPoint joinPoint) throws Throwable {
		setCustomerIdInEntityManager();
		return handleRepositoryCall(joinPoint);
	}

	/**method to point sqltoJpql converter
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "appJPQLToSQLConverter()")
	@Order(1)
	public Object aroundAppJPQLToSQLConverter(ProceedingJoinPoint joinPoint) throws Throwable {
		createLocalRuntimeLogInfo();
		return handleRepositoryCall(joinPoint);
	}

	@Pointcut("execution(* com.spartan..repository..*(..))) && !within(com.spartan.server.interfaces.ArtQueryRepository+) && ! cloudOperation() && !within(com.spartan..alarms..*)")
	protected void appRepositoryOperation() {
	}

	@Pointcut("execution(* com.spartan.server.interfaces.ArtQueryRepository.findSqlFromJPQL())")
	protected void appJPQLToSQLConverter() {

	}

	@Pointcut("execution(* com.athena.cloud..repository..*(..))")
	protected void cloudOperation() {
	}

}
