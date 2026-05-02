package tn.esprit._4ds11.championnat.championnat.config;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class SecurityPerformanceAspect {

    @Pointcut("execution(* tn.esprit._4ds11.championnat.championnat.services..*.get*(..))")
    public void getMethods() {
        // toutes les méthodes get* des services (y compris sous-packages)
    }

    @Before("getMethods()")
    public void verifierDroit(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new AccessDeniedException("Utilisateur non authentifie");
        }

        boolean hasRight = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                        || a.getAuthority().equals("ROLE_USER"));

        if (!hasRight) {
            throw new AccessDeniedException("Acces refuse a " + joinPoint.getSignature().getName());
        }
    }

    @Around("getMethods()")
    public Object mesurerPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long durationMs = (System.nanoTime() - start) / 1_000_000;
            log.info("Temps execution {}: {} ms", joinPoint.getSignature().toShortString(), durationMs);
        }
    }
}
