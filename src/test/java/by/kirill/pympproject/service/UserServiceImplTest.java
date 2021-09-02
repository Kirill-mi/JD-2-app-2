package by.kirill.pympproject.service;


import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.UserDAO;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class UserServiceImplTest {
    private final UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void testCreateUser() throws ServiceException, DAOException {
        UserDAO userDAO = EasyMock.createMock(UserDAO.class);
        userService.setUserDao(userDAO);
        EasyMock.expect(userDAO.readUser("111a@mail.ru")).andReturn(Optional.of(new User()));
        EasyMock.replay(userDAO);
        String actual = userService.createUser(new RegistrationInfo("John", "111a@mail.ru", "123", "123"));
        EasyMock.verify(userDAO);
        assertEquals("User exists", actual);
    }

    @Ignore
    @Test
    public void testCreateUserSuccessfully() throws ServiceException, DAOException {
        UserDAO userDAO = EasyMock.createMock(UserDAO.class);
        userService.setUserDao(userDAO);
        EasyMock.expect(userDAO.readUser("111a@mail.ru")).andReturn(Optional.empty());
        EasyMock.expect(userDAO.add(new User("John", "111a@mail.ru", "123"))).andReturn(true);
        EasyMock.replay(userDAO);
        String actual = userService.createUser(new RegistrationInfo("John", "111a@mail.ru", "123", "123"));
        EasyMock.verify(userDAO);
        assertEquals("User added", actual);
    }

    @ParameterizedTest
    @MethodSource("createUserArgumentProvider")
    public void testCreateUser(String message, RegistrationInfo registrationInfo)
            throws ServiceException, DAOException {
        assertEquals(message, userService.createUser(registrationInfo));
    }

    static Stream<Arguments> createUserArgumentProvider() {
        return Stream.of(
                arguments("Enter correct name",
                        new RegistrationInfo("", "111@mail.ru", "123", "123")),
                arguments("Enter correct password",
                        new RegistrationInfo("John", "111@mail.ru", "", "123")),
                arguments("Enter correct password",
                        new RegistrationInfo("John", "111@mail.ru", "123", "12")),
                arguments("Enter correct email",
                        new RegistrationInfo("John", "111", "123", "123"))
        );
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
    public void testIsEmailInvalid() {
        try {
            Method method = UserServiceImpl.class.getDeclaredMethod("isEmailInvalid", String.class);
            method.setAccessible(true);
            assertEquals(true, method.invoke(userService, ""));
            assertEquals(true, method.invoke(userService, "asks"));
            assertEquals(false, method.invoke(userService, "asks@mail.ru"));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}