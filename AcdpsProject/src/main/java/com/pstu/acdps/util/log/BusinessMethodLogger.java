/**
 * 
 */
package com.pstu.acdps.util.log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import com.pstu.acdps.server.security.CurrentUser;

@Aspect
public class BusinessMethodLogger {
    private Map<Class<? extends Logger>, Logger> loggers = new HashMap<Class<? extends Logger>, Logger>();

    /**
     * @param loggers the writer to set
     */
    // @Autowired
    public void setLoggers(List<Logger> loggers) {
        for (Logger lg : loggers) {
            this.loggers.put(lg.getClass(), lg);
        }
    }

    @Around("@annotation(loggableBusinessMethod)")
    public Object logLoggableBusinessmethod(ProceedingJoinPoint pjp, LoggableBusinessMethod loggableBusinessMethod)
            throws Throwable {
        return logBusinessMethodCall(pjp, loggableBusinessMethod.method(), loggableBusinessMethod.logWith());
    }

    @SuppressWarnings("unchecked")
    @Around("execution(* com.pstu.acdps.server.service.GwtRpcServiceImpl.*(..))")
    public Object logGwtServiceMethodCall(ProceedingJoinPoint pjp) throws Throwable {
        return logBusinessMethodCall(pjp, "", DatabaseLogger.class);
    }

    protected Object logBusinessMethodCall(ProceedingJoinPoint joinPoint, String methodName,
            Class<? extends Logger>... loggersClasses) throws Throwable {
        Message m = MessageFactory.createMessage();
        m.setUserName(CurrentUser.getLogin()).setMethod(methodName.isEmpty() ? joinPoint.getSignature().getName() : methodName);
        m.setMessage(buildMessage(joinPoint));
        m.setStart(new Date());
        Object result = joinPoint.proceed();
        m.setEnd(new Date());

        for (Class<? extends Logger> cls : loggersClasses) {
            if (loggers.containsKey(cls)) {
                loggers.get(cls).log(m);
            }
        }

        return result;
    }

    private String buildMessage(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        String methodMethodName = joinPoint.getSignature().getName();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        if (method.getDeclaringClass().isInterface()) {
            method = joinPoint.getTarget().getClass().getDeclaredMethod(methodMethodName, method.getParameterTypes());
        }
        Object[] paramsArgs = joinPoint.getArgs();
        int i = 0;
        StringBuilder messageBuilder = new StringBuilder();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] an : parameterAnnotations) {
            for (Annotation ann : an) {
                if (ann instanceof LoggableBusinessParameter) {
                    LoggableBusinessParameter ourAnnotation = (LoggableBusinessParameter) ann;
                    if (ourAnnotation != null) {
                        messageBuilder.append(ourAnnotation.value() + " = " + paramsArgs[i] + ";");
                    }
                }
            }
            i++;
        }
        String result = messageBuilder.toString();
        return result.isEmpty() ? null : result;
    }

}
