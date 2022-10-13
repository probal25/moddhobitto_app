package com.probal.moddhobitto.core.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MAspect {

    @Before("execution(* com.probal.moddhobitto.core.auth.service.AuthService.createAppUser(..))")
    public void printUserCreatedStarted() {
        System.out.println("**************** New User creation started ****************");
    }

    @After("execution(* com.probal.moddhobitto.core.auth.service.AuthService.createAppUser(..))")
    public void printUserCreated() {
        System.out.println("**************** A new User has been created ****************");
    }
}
