package com.ymagis.madmingw.dao;

import com.ymagis.madmingw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Amine BOUKILI
 */

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

    /*@Query("SELECT u FROM User u WHERE u.userLogin = :login")
    List<User> findByUserLogin(@Param("login") int login);*/
}
