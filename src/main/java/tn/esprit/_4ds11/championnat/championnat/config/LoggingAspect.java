package tn.esprit._4ds11.championnat.championnat.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* tn.esprit._4ds11.championnat.championnat.services.*.*(..))")
    public void serviceMethods() {
        // Pointcut for all service methods
    }

    @Before("serviceMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info(">>> Entree dans la methode: {}", methodName);
    }

    @After("serviceMethods()")
    public void logMethodExit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("<<< Sortie de la methode: {}", methodName);
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Resultat retourne par {}: {}", methodName, result);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "error")
    public void logMethodException(JoinPoint joinPoint, Throwable error) {
        String methodName = joinPoint.getSignature().getName();
        log.error("Exception dans {}: {}", methodName, error.getMessage(), error);
    }
}
