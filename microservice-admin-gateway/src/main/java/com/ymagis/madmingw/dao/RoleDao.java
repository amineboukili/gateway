package com.ymagis.madmingw.dao;

import com.ymagis.madmingw.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Amine BOUKILI
 */

@Repository
public interface RoleDao extends JpaRepository<Role, Integer>{
}
