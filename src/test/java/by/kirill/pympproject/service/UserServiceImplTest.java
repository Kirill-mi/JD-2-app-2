package by.kirill.pympproject.service;


import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.UserDAO;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class UserServiceImplTest {
    private final UserServiceImpl userService = new UserServiceImpl();

    @Test
//    @ParameterizedTest
//    @MethodSource("createUserArgumentProvider")
    public void testCreateUser() throws ServiceException, DAOException {
        UserDAO userDAO = EasyMock.createMock(UserDAO.class);
        userService.setUserDao(userDAO);
        EasyMock.expect(userDAO.readUser("Jogg")).andReturn(Optional.of(new User()));
        EasyMock.replay(userDAO);
        String actual = userService.createUser(new RegistrationInfo("Joh", "Jogg", "123", "123"));
        EasyMock.verify(userDAO);
        assertEquals("ufefe", actual);

    }


    public void testDeleteUser() {
    }

    public void testValidateUser() {
    }

    public void testUpdateUser() {
    }

    public void testReadUser() {
    }

    @Test
    public void testCheckEmail() {
        try {
            Method method = UserServiceImpl.class.getDeclaredMethod("checkEmail", String.class);
            method.setAccessible(true);
            assertEquals(false, method.invoke(userService, ""));
            assertEquals(false, method.invoke(userService, "asks"));
            assertEquals(true, method.invoke(userService, "asks@mail.ru"));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}