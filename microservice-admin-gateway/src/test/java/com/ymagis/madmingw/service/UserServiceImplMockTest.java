package com.ymagis.madmingw.service;

import com.ymagis.madmingw.dao.UserDao;
import com.ymagis.madmingw.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;


public class UserServiceImplMockTest {


    private UserServiceImpl userServiceImpl;
    @Mock
    private UserDao userDao;
    @Mock
    private User user;
    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        userServiceImpl=new UserServiceImpl();
        userServiceImpl.setUserDao(userDao);
    }
    @Test
    public void shouldReturnUser_whenGetUserByIdIsCalled() throws Exception {
        // Arrange
        when(userDao.findById(1)).thenReturn(Optional.ofNullable(user));
        // Act
        Optional<User> retrievedProduct = userServiceImpl.findById(1);
        // Assert
        assertThat(retrievedProduct, is(equalTo(Optional.ofNullable(user))));

    }

    @Test
    public void shouldReturnUser_whenSaveUserIsCalled() throws Exception {
        // Arrange
        when(userDao.save(user)).thenReturn(user);
        // Act
        User savedUser = userServiceImpl.save(user);
        // Assert
        assertThat(savedUser, is(equalTo(user)));
    }

    @Test
    public void shouldCallDeleteMethodOfProductRepository_whenDeleteProductIsCalled() throws Exception {
        // Arrange
        doNothing().when(userDao).deleteById(1);
        UserDao my = Mockito.mock(UserDao.class);
        // Act
        userServiceImpl.deleteById(1);
        // Assert
        verify(userDao, times(1)).deleteById(1);
    }
}
