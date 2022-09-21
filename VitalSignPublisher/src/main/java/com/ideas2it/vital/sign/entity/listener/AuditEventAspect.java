package com.ideas2it.vital.sign.entity.listener;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditEventAspect {

	@Pointcut(value = "execution(* com.ideas2it.vital.sign.*.*.*(..) )")
	public void callAnnotatedmethod(AnnotationForAudit annotation) {
		System.out.println("entering call annotated 1");
	}

//	@Before(value = "callAnnotatedmethod(annotation)", argNames = "joinPoint,annotation")
//	public void beforeCallAnnotatedmethod(JoinPoint joinPoint, AnnotationForAudit annotation) {
//		System.out.println("do something...");
//	}
}