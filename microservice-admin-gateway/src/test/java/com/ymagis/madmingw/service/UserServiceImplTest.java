package com.ymagis.madmingw.service;

import com.ymagis.madmingw.dao.UserDao;
import com.ymagis.madmingw.model.User;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsSame;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


class UserServiceImplTest {

    private static Logger logger = null;

    private UserServiceImpl userServiceImpl;
    @Mock
    private UserDao userDao;
    @Mock
    private User userMock;

    @Spy
    List<User> users = new ArrayList<User>();

    @BeforeAll
    public static void setUp() {
        logger = Logger.getLogger("Testing started");

    }

    @BeforeEach
    void setupMock() {
        MockitoAnnotations.initMocks(this);
        userServiceImpl=new UserServiceImpl();
        userServiceImpl.setUserDao(userDao);
    }

    @Test
    void findAll() throws Exception {
        // Arrange
        List<User> users = getUserList();

        when(userDao.findAll()).thenReturn(users);
        // Act
        List<User> result = userServiceImpl.findAll();
        verify(userDao, atLeastOnce()).findAll();
        // Assert
        assertThat(result.isEmpty(), is(false));
        assertThat(result.size(), is(2));
    }

    @Test
    void findById() throws Exception {
        // Arrange
        User user = getUserList().get(0);
        when(userDao.findById(anyInt())).thenReturn(Optional.ofNullable(user));
        // Act
        Optional<User> retrievedUser = userServiceImpl.findById(user.getId());
        // Assert
        assertThat(retrievedUser.get().getId(), is(equalTo(user.getId())));
    }

    @Test
    void save() throws Exception {
        // Arrange
        User user = getUserList().get(0);
        when(userDao.save(userMock)).thenReturn(user);
        // Act
        User savedUser = userServiceImpl.save(userMock);
        // Assert
        assertThat(savedUser.getId(), is(equalTo(user.getId())));

        /*doNothing().when(userDao).save(any(User.class));
        userServiceImpl.save(any(User.class));
        verify(userDao, atLeastOnce()).save(any(User.class));*/
    }

    @Test
    void update() throws Exception {
        // Arrange
        User user = getUserList().get(0);
        when(userDao.save(userMock)).thenReturn(user);
        // Act
        User updatedUser = userServiceImpl.update(userMock);
        // Assert
        assertThat("Michael", IsEqual.equalTo(updatedUser.getUserFirstname()));
        assertThat("Michael", IsSame.sameInstance(updatedUser.getUserFirstname()));
        assertThat("Michael", IsInstanceOf.instanceOf(CharSequence.class));
    }

    @Test
    void deleteById() throws Exception {
        // Arrange
        doNothing().when(userDao).deleteById(1);
        UserDao my = Mockito.mock(UserDao.class);
        // Act
        userServiceImpl.deleteById(1);
        // Assert
        verify(userDao, times(1)).deleteById(1);
    }

    @AfterAll
    public static void destroy() {
        logger = Logger.getLogger("Testing terminated");
        logger = null;
    }

    public List<User> getUserList(){
        users.add(new User(1, "msa", "michael.santos@dcinex.com", "Michael", "Santos", "1", 1, null, 1, null, null, 1));
        users.add(new User(2, "jla", "Jean-marie.lallouet@dcinex.com", "Jean-Marie", "Lallouet", "2", 2, null, 2, null, null, 2));
        return users;
    }


}