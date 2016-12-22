package com.app.server.repository.appinsight.health.aspect;
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
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.alarms.EventAppLayers;
import com.spartan.pluggable.logger.alarms.EventAction;

@Aspect
@Component
public class RepositoryhealthAspect extends RepositoryAspect {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ArtMethodCallStack requestDetails;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Around("allOperation()")
    public Object aroundAllOtherOpeartion(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.REPOSITORY, runtimeLogInfoHelper.getRuntimeLogInfo().getUserIPAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId(), requestDetails.getCustomerId());
        Object object = null;
        setCustomerIdInEntityManager();
        try {
            Log.out.println("AISHI314900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName());
            object = joinPoint.proceed();
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION);
            Log.out.println("AISHI347900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName(), methodCallDetails.getExecution_Time());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("AIS", "HI", EventAppLayers.DATABASE_SERVICE, EventAction.READ_WRITE_UPDATE, e.getClass().getName());
            Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        } finally {
            requestDetails.addMethodCallDetails(methodCallDetails);
        }
        return object;
    }

    @Around("findOperation()||getOperation()")
    public Object aroundfindAll(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.REPOSITORY, runtimeLogInfoHelper.getRuntimeLogInfo().getUserIPAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId(), requestDetails.getCustomerId());
        Object object = null;
        setCustomerIdInEntityManager();
        try {
            Log.out.println("AISHI314900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName());
            object = joinPoint.proceed();
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION);
            Log.out.println("AISHI347900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName(), methodCallDetails.getExecution_Time());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("AIS", "HI", EventAppLayers.DATABASE_SERVICE, EventAction.READ, e.getClass().getName());
            Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        } finally {
            requestDetails.addMethodCallDetails(methodCallDetails);
        }
        return object;
    }

    @Around("saveOperation()")
    public Object aroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.REPOSITORY, runtimeLogInfoHelper.getRuntimeLogInfo().getUserIPAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId(), requestDetails.getCustomerId());
        Object object = null;
        repositoryLogic(joinPoint);
        try {
            Log.out.println("AISHI312900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName());
            object = joinPoint.proceed();
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION);
            Log.out.println("AISHI347900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName(), methodCallDetails.getExecution_Time());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("AIS", "HI", EventAppLayers.DATABASE_SERVICE, EventAction.WRITE, e.getClass().getName());
            Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        } finally {
            requestDetails.addMethodCallDetails(methodCallDetails);
        }
        return object;
    }

    @Around("updateOperation()")
    public Object aroundUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.REPOSITORY, runtimeLogInfoHelper.getRuntimeLogInfo().getUserIPAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId(), requestDetails.getCustomerId());
        Object object = null;
        repositoryLogic(joinPoint);
        try {
            Log.out.println("AISHI311900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName());
            object = joinPoint.proceed();
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION);
            Log.out.println("AISHI347900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName(), methodCallDetails.getExecution_Time());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("AIS", "HI", EventAppLayers.DATABASE_SERVICE, EventAction.UPDATE, e.getClass().getName());
            Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        } finally {
            requestDetails.addMethodCallDetails(methodCallDetails);
        }
        return object;
    }

    @Around("deleteOperation()")
    public Object aroundDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.REPOSITORY, runtimeLogInfoHelper.getRuntimeLogInfo().getUserIPAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId(), requestDetails.getCustomerId());
        Object object = null;
        repositoryLogic(joinPoint);
        try {
            Log.out.println("AISHI318900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName());
            object = joinPoint.proceed();
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION);
            Log.out.println("AISHI347900100", runtimeLogInfoHelper.getRequestHeaderBean(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName(), methodCallDetails.getExecution_Time());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("AIS", "HI", EventAppLayers.DATABASE_SERVICE, EventAction.DELETE, e.getClass().getName());
            Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        } finally {
            requestDetails.addMethodCallDetails(methodCallDetails);
        }
        return object;
    }

    @Pointcut("execution(* com.app.server.repository.appinsight.health..save*(..))")
    protected void saveOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.appinsight.health..update*(..))")
    protected void updateOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.appinsight.health..delete*(..))")
    protected void deleteOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.appinsight.health..find*(..))")
    protected void findOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.appinsight.health..get*(..))")
    protected void getOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.appinsight.health..*(..)) && ! findOperation() && ! saveOperation() && ! updateOperation() && ! deleteOperation() && ! getOperation()")
    protected void allOperation() {
    }
}
