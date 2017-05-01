package ru.kpfu.itis.NovikovRuslan.aspect_log;

import org.apache.log4j.Logger;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.NovikovRuslan.entities.Doctor;

import java.lang.reflect.Method;




@Aspect
@Component
public class DaoExceptionLogger {
    private static final Logger logger = Logger.getLogger(DaoExceptionLogger.class);

    @AfterThrowing(pointcut = "execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.CitiesDaoHibernateImpl.*(..))", throwing = "error")
    public void logAfterException(Throwable error) {
        logger.error("wrong CityDao execution" , error.getCause());
    }

    @After("execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.DoctorsDaoHibernateImpl.saveDoctor(..)) "
            + "&& args(doctor)")
    public void logAfterSaving(Doctor doctor){

        System.out.println("FUCKKKKKKKKKKKKKKKKKKKKKKKKK");
        logger.info("Successfully saved doctor with name" + doctor.getFio());
    }

    @AfterReturning("execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.UsersDaoHibernateImpl.findUser(..)) "
            + "&& args(username)")
    public void logAfterReturning(String username) {

        logger.info("Found user with this" + username);
    }

    @Around("execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.PolyclinicsDaoHibernateImpl.getPolyclinic(..)) "
            + "&& args(id)"
            + "|| execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.PolyclinicsDaoHibernateImpl.deletePolyclinic(..)) "
            + "&& args(id)")
    public void logAround(ProceedingJoinPoint joinPoint, Long id){
        String methodName = getMethodName(joinPoint);
        logger.info("Method \"" + methodName + "\" is calling with argument = " + id );
        try{
            joinPoint.proceed();
            logger.info("Method \"" + methodName + "\" called");
        }catch(Throwable throwable) {
            logger.error("Method \"" + methodName +"\" called exception" + throwable.getMessage());
        }
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getName();
    }
}
