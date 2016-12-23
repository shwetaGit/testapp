package com.app.server.businessservice.aspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;

import org.springframework.beans.factory.annotation.Autowired;

import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import com.spartan.healthmeter.msgWriter.core.Healthmeter;

import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import com.spartan.healthmeter.entity.scheduler.MethodCallDetails;
import com.spartan.healthmeter.msgWriter.config.HealthConstants;
import com.spartan.pluggable.exception.core.AppBaseException;

@Aspect
@Component
public class BusinessServiceBaseAspect extends BusinessAspect {

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private ArtMethodCallStack requestDetails;

	@Pointcut("execution(* com.athena..server.bizService..*(..))")
	protected void allOperation() {
	}

	/**
	 * Calculate health statistics for all com.athena packaged business services .
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("allOperation()")
	public Object aroundfindAll(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.BUSINESS,
				runtimeLogInfoHelper.getRuntimeLogInfo().getUserIPAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(),
				runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId(),requestDetails.getCustomerId());
		Object object = null;
		try {
			object = handleMethodCall(joinPoint, runtimeLogInfoHelper.getRuntimeLogInfo());
			methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION);
		} catch (AppBaseException e) {
			methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, e.getAppAlarmId());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, HealthConstants.DEFAULT_EXCEPTION_ID);
			e.printStackTrace();
			throw e;
		} finally {
			requestDetails.addMethodCallDetails(methodCallDetails);
		}
		return object;
	}
}
