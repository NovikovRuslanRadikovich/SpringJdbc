package test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.NovikovRuslan.aspect_log.DaoExceptionLogger;
import ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.CitiesDaoHibernateImpl;



@Configuration
public class AppTestContextConfiguration {

    @Bean
    CitiesDaoHibernateImpl citiesDaoHibernate(){
        return new CitiesDaoHibernateImpl();
    }

}
