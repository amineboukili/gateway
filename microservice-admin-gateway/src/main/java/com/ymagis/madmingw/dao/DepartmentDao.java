package com.ymagis.madmingw.dao;

import com.ymagis.madmingw.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Amine BOUKILI
 */

@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer>{
}
