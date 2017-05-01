package test;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.CitiesDaoHibernateImpl;

import static junit.framework.Assert.assertNotNull;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContextConfiguration.class})
public class AppJUnitTest {

    @Autowired
    private CitiesDaoHibernateImpl citiesDaoHibernate;


    @Test
    public void verifyBean() {
        assertNotNull(citiesDaoHibernate);
    }
}
