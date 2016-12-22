package com.app.server.repository.aspect;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.pluggable.exception.security.InvalidDataException;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;

@Aspect
@Component
public abstract class RepositoryAspect {

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private EntityValidatorHelper<Object> entityValidator;

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	public Object aroundfindAll(ProceedingJoinPoint joinPoint) throws Throwable {
		return joinPoint;
	}

	/**
	 * Checks the entity at save and update opeartion .
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	protected void repositoryLogic(ProceedingJoinPoint joinPoint) throws Throwable {
		setCustomerIdInEntityManager();
		Object object = null;
		if (joinPoint.getArgs().length > 0) {
			Object methodInputParam = joinPoint.getArgs()[0];
			if (methodInputParam != null && methodInputParam instanceof CommonEntityInterface) {
				CommonEntityInterface entity = (CommonEntityInterface) methodInputParam;
				preSaveUpdateOperation(entity);
			} else if (methodInputParam != null && methodInputParam instanceof List) {
				List listOfEntities = (List) methodInputParam;
				if (listOfEntities.size() > 0) {
					/*
					 * Checking 0th element type. So no need to check type for
					 * each element in the loop.
					 */
					if (listOfEntities.get(0) instanceof CommonEntityInterface) {
						for (Object object1 : listOfEntities) {
							CommonEntityInterface entity = (CommonEntityInterface) object1;
							preSaveUpdateOperation(entity);
						}
					}
				}
			}
		}
	}

	/**
	 * Proceed the request
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	protected Object handleRepositoryCall(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnObject = null;
		try {

			returnObject = joinPoint.proceed();
//			System.out.println("Repository handle");
		} catch (Exception e) {
		}
		return returnObject;
	}

	/**
	 * Validates the entity for save and update .
	 * 
	 * @param entity
	 * @throws InvalidDataException
	 */
	protected void preSaveUpdateOperation(CommonEntityInterface entity) throws InvalidDataException {
		entity.setEntityAudit(1, runtimeLogInfoHelper.getRuntimeLogInfo().getUserId());
		/* validates the entity */
		if (!entity.isEntityValidated()) {
			validateEntity(entity);
		}
	}

	/**
	 * Validates the entity
	 * 
	 * @param entity
	 * @return
	 * @throws InvalidDataException
	 */
	private boolean validateEntity(CommonEntityInterface entity) throws InvalidDataException {
		boolean isValidEntity = true;
		/* set entity validator */
		entity.setEntityValidator(this.entityValidator);
		/* validates the entity */
		isValidEntity = entity.isValid();
		return isValidEntity;
	}

	/**
	 * Sets the value of RuntimeLogUserInfoBean.
	 * 
	 * @return RuntimeLogUserInfoBean
	 */
	protected RuntimeLogUserInfoBean createLocalRuntimeLogInfo() {
		String ipAddress = "localhost";
		try {
			//InetAddress ip = InetAddress.getLocalHost();
			ipAddress = getIpAddress();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return new RuntimeLogUserInfoBean("customer", "DEFAULT", ipAddress, 0, 0, 0);
	}

	/**
	 * Method to set multitenant 'tenant.id' value in runtimeLogInfoHelper
	 */
	public void setCustomerIdInEntityManager() {
		if (runtimeLogInfoHelper.getCustomerId() != "" && runtimeLogInfoHelper.getCustomerId() != null) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("tenant.id", runtimeLogInfoHelper.getCustomerId());
			emfResource.setResourceAttributes(map);
			runtimeLogInfoHelper.setMultitenantFields(map);
		}
	}

	/**
	 * Method to return host ipAddress
	 */
	private String getIpAddress() throws SocketException {
		InetAddress inet = null;
		String ipAddress = "127.0.0.1";
		final Enumeration<NetworkInterface> lstnetInt = NetworkInterface.getNetworkInterfaces();
		while (lstnetInt.hasMoreElements()) {
			final NetworkInterface netInt = lstnetInt.nextElement();
			if (netInt.getDisplayName().equalsIgnoreCase("eth0")) {
				for (final InterfaceAddress intAdd : netInt.getInterfaceAddresses()) {
					if (intAdd.getAddress().isSiteLocalAddress()) {
						inet = intAdd.getAddress();
						ipAddress = inet.getHostAddress();
						break;
					}
				}
			}
		}
		return ipAddress;
	}

	@Pointcut("execution(* com.athena..repository..*(..))")
	protected void athenaServiceOperation() {
	}

	@Pointcut("execution(* com.spartan..repository..*(..))")
	protected void spartanServiceOperation() {
	}

	@Pointcut("(execution(* com.athena..repository..*(..)) || execution(* com.spartan..repository..*(..))) && !within(com.athena.server.repository.ArtQueryRepository+)")
	protected void appRepositoryOperation() {
	}

	@Pointcut("execution(* com.athena.server.repository.ArtQueryRepository.findSqlFromJPQL())")
	protected void appJPQLToSQLConverter() {

	}

}
