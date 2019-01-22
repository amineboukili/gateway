package com.madmingw.dao;

import com.madmingw.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer>{
}
