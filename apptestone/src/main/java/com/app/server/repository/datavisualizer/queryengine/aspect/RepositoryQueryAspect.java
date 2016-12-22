package com.app.server.repository.datavisualizer.queryengine.aspect;
import com.app.server.repository.aspect.RepositoryAspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;

import org.springframework.beans.factory.annotation.Autowired;

import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import com.spartan.pluggable.logger.api.LogManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import com.spartan.healthmeter.entity.scheduler.MethodCallDetails;
import com.spartan.healthmeter.msgWriter.config.HealthConstants;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.athena.server.pluggable.utils.AppLoggerConstant;

@Aspect
@Component
public class RepositoryQueryAspect extends RepositoryAspect {

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private ArtMethodCallStack requestDetails;

	private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

	/**
	 * checks the entity , calulate health , log the status for all operation in query domain 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("allOperation()")
	public Object aroundAllOtherOpeartion(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.REPOSITORY,
				runtimeLogInfoHelper.getRuntimeLogInfo().getUserIPAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(),
				runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId(),requestDetails.getCustomerId());
		Object object = null;
		repositoryLogic(joinPoint);
		try {
			Log.out.println("DVIQE224900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			object = joinPoint.proceed();
			methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION);
			Log.out.println("DVIQE234900200", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		} catch (Exception e) {
			Log.out.println("DVIQE254900500", runtimeLogInfoHelper.getRequestHeaderBean(), e, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
					e.getMessage());
			throw e;
		} finally {
			requestDetails.addMethodCallDetails(methodCallDetails);
		}
		return object;
	}

	@Pointcut("execution(* com.athena.server.dataengine..repository..*(..))  && ! findSqlFromJPQL() && ! updateSQLFromJPQL() ")
	protected void allOperation() {
	}

	@Pointcut("execution(* com.spartan.server.interfaces.ArtQueryRepository.findSqlFromJPQL(..))")
	protected void findSqlFromJPQL() {
	}

	@Pointcut("execution(* com.spartan.server.interfaces.ArtQueryRepository.updateSQLFromJPQL(..))")
	protected void updateSQLFromJPQL() {
	}
}
