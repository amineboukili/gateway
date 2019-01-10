package com.mmaterials.dao;

import com.mmaterials.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaterialDao extends JpaRepository<Material, Integer>{
}
