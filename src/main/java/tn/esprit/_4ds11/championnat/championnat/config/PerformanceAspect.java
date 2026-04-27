package tn.esprit._4ds11.championnat.championnat.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class PerformanceAspect {

    @Around("execution(* tn.esprit._4ds11.championnat.championnat.services.*.*(..))")
    public Object mesurePerformance(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        log.info("⏱ [AROUND - AVANT] Debut execution de: {}", methodName);

        long debut = System.currentTimeMillis();
        Object resultat = pjp.proceed(); // appel de la vraie méthode
        long duree = System.currentTimeMillis() - debut;

        log.info("⏱ [AROUND - APRES] {} executee en {} ms", methodName, duree);
        return resultat;
    }
}