package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.kpfu.itis.NovikovRuslan.entities.User;

import ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.UsersDaoHibernateImpl;


import static org.mockito.Mockito.when;

import static ru.kpfu.itis.NovikovRuslan.enumeration.UserRole.ROLE_USER;


@RunWith(MockitoJUnitRunner.class)
public class AppMockitoTest {

    @Mock
    private UsersDaoHibernateImpl usersDao;

    @Before
    public void setUp(){
        when(usersDao.findUser("admin")).thenReturn(new User("admin","admin", ROLE_USER));
    }

    @Test
    public void confirmUser(){
        Assert.assertEquals(usersDao.findUser("admin").getRole(),ROLE_USER);
    }

}
