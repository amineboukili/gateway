package com.ymagis.madmingw.service;

import com.ymagis.madmingw.model.User;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Amine BOUKILI
 */

public interface UserService {
    public List<User> findAll();
    public Optional<User> findById(Integer id);
    public User save(User user);
    public User update(User user);
    public void deleteById(Integer id);

}
