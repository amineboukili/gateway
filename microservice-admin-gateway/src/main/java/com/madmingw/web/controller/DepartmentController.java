package com.madmingw.web.controller;

import com.madmingw.configurations.ApplicationPropertiesConfiguration;
import com.madmingw.dao.DepartmentDao;
import com.madmingw.model.Department;
import com.madmingw.web.exceptions.DepartmentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentDao departmentDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    /**
     * Affiche la liste de tous les départements disponibles
     * @return
     */
    @GetMapping(value = "/Departments")
    public List<Department> listeDepartments(){

        List<Department> departments = departmentDao.findAll();

        if(departments.isEmpty()) throw new DepartmentNotFoundException("Aucun département n'est disponible");

        List<Department> listeLimitee = departments.subList(0, appProperties.getLimitDepartments());

        log.info("Recuperation de la liste des départements");

        return listeLimitee;

    }

    /**
     * Récuperer un département par son id
     * @param id
     * @return
     */
    @GetMapping( value = "/Departments/{id}")
    public Optional<Department> getDepartment(@PathVariable int id) {

        Optional<Department> department = departmentDao.findById(id);

        if(!department.isPresent())  throw new DepartmentNotFoundException("Le département correspondant à l'id " + id + " n'existe pas");

        return department;
    }

    /**
     * Ajouter un département
     * @param department
     * @return
     */
    @PostMapping(value = "/Departments", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<Void> addDepartment(@RequestBody Department department) {
        Department departmentAdded = departmentDao.save(department);

        // Dans le cas où le département ajouté est vide ou n'existe pas, nous retournons le code 204 No Content.
        if (departmentAdded == null)
            return ResponseEntity.noContent().build();

        // Dans le cas où tout s'est bien passé, on retourne le code 201 et on ajoute l'URI vers la nouvelle ressource créée afin d'être conforme avec le protocole HTTP.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(departmentAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Mettre à jour un département
     * @param department
     */
    @PutMapping(value = "/Departments")
    public void updateDepartment(@RequestBody Department department) {
        departmentDao.save(department);
    }

    /**
     * Supprimer un département par son Id
     * @param id
     */
    @DeleteMapping(value = "/Departments/{id}")
    public void deleteDepartment(@PathVariable int id) {
        departmentDao.deleteById(id);
    }
}

