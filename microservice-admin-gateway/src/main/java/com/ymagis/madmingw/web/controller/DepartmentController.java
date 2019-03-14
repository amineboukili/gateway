package com.ymagis.madmingw.web.controller;

import com.ymagis.madmingw.configurations.ApplicationPropertiesConfiguration;
import com.ymagis.madmingw.dao.DepartmentDao;
import com.ymagis.madmingw.model.Department;
import com.ymagis.madmingw.web.exceptions.DepartmentNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


/**
 *
 * @author Amine BOUKILI
 */

@Api(value = "department", description = "Rest API for Department operations", tags = "Department API")
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
    @ApiOperation(value = "Retrieves the list of departements", response = Department.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
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
    @ApiOperation(value = "Retrieves a department by Id", response = Department.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
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
    @ApiOperation(value = "Add a department", response = Void.class)
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
    @ApiOperation(value = "Update a department", response = Void.class)
    @PutMapping(value = "/Departments")
    public void updateDepartment(@RequestBody Department department) {
        departmentDao.save(department);
    }

    /**
     * Supprimer un département par son Id
     * @param id
     */
    @ApiOperation(value = "Delete a department", response = Void.class)
    @DeleteMapping(value = "/Departments/{id}")
    public void deleteDepartment(@PathVariable int id) {
        departmentDao.deleteById(id);
    }
}

