package ru.kpfu.itis.NovikovRuslan.aspect_log;

import org.apache.log4j.Logger;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.NovikovRuslan.entities.Doctor;
import ru.kpfu.itis.NovikovRuslan.entities.Polyclinic;
import ru.kpfu.itis.NovikovRuslan.entities.Schedule;
import ru.kpfu.itis.NovikovRuslan.entities.User;

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
        logger.info("Successfully saved doctor with name" + doctor.getFio());
    }

    @After("execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.DoctorsDaoHibernateImpl.deleteDoctor(..)) "
           + "&& args(id)")
    public void logAfterDeleting(Long id){logger.info("Successfully deleted doctor with id" + id);}

    @AfterReturning("execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.UsersDaoHibernateImpl.findUser(..)) "
            + "&& args(username)")
    public void logAfterReturning(String username) {

        logger.info("Found user with this" + username);
    }

    @After("execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.UsersDaoHibernateImpl.saveUser(..)) "
            + "&& args(user)")
    public void logAfterSavingUser(User user){
        logger.info("Successfully saved doctor with name" + user.getUsername());
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

    @Around(" execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.PolyclinicsDaoHibernateImpl.savePolyclinic(..)) "
            + "&& args(polyclinic)")
    public void logAroundSaving(ProceedingJoinPoint joinPoint, Polyclinic polyclinic) {
        String methodName = getMethodName(joinPoint);
        logger.info("Method \"" + methodName + "\" is calling with argument = " + polyclinic.getPolyclinic_name() );
        try{
            joinPoint.proceed();
            logger.info("Method \"" + methodName + "\" called");
        }catch(Throwable throwable) {
            logger.error("Method \"" + methodName +"\" called exception" + throwable.getMessage());
        }
    }

    @After("execution(* ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.SchedulesDaoHibernateImpl.saveSchedule(..)) "
            + "&& args(schedule)")
    public void logAfterSavingSchedule(Schedule schedule){
        logger.info("Successfully saved schedule for" + schedule.getDoctor().getFio());
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getName();
    }
}
