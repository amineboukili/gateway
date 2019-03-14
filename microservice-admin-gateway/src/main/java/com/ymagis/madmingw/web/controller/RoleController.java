package com.ymagis.madmingw.web.controller;

import com.ymagis.madmingw.configurations.ApplicationPropertiesConfiguration;
import com.ymagis.madmingw.dao.RoleDao;
import com.ymagis.madmingw.model.Role;
import com.ymagis.madmingw.web.exceptions.RoleNotFoundException;
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

@Api(value = "role", description = "Rest API for Role operations", tags = "Role API")
@RestController
public class RoleController {

    @Autowired
    RoleDao roleDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    /**
     * Affiche la liste de tous les rôles disponibles
     * @return
     */
    @ApiOperation(value = "Retrieves the list of roles", response = Role.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/Roles")
    public List<Role> listeRoles(){

        List<Role> roles = roleDao.findAll();

        if(roles.isEmpty()) throw new RoleNotFoundException("Aucun rôle n'est disponible");

        List<Role> listeLimitee = roles.subList(0, appProperties.getLimitRoles());

        log.info("Recuperation de la liste des rôles");

        return listeLimitee;

    }

    /**
     * Récuperer un rôle par son id
     * @param id
     * @return
     */
    @ApiOperation(value = "Retrieves a role by Id", response = Role.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
    @GetMapping( value = "/Roles/{id}")
    public Optional<Role> getRole(@PathVariable int id) {

        Optional<Role> role = roleDao.findById(id);

        if(!role.isPresent())  throw new RoleNotFoundException("Le rôle correspondant à l'id " + id + " n'existe pas");

        return role;
    }

    /**
     * Ajouter un rôle
     * @param role
     * @return
     */
    @ApiOperation(value = "Add a role", response = Void.class)
    @PostMapping(value = "/Roles", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<Void> addRole(@RequestBody Role role) {
        Role roleAdded = roleDao.save(role);

        // Dans le cas où le rôle ajouté est vide ou n'existe pas, nous retournons le code 204 No Content.
        if (roleAdded == null)
            return ResponseEntity.noContent().build();

        // Dans le cas où tout s'est bien passé, on retourne le code 201 et on ajoute l'URI vers la nouvelle ressource créée afin d'être conforme avec le protocole HTTP.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(roleAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Mettre à jour un rôle
     * @param role
     */
    @ApiOperation(value = "Update a role", response = Void.class)
    @PutMapping(value = "/Roles")
    public void updateRole(@RequestBody Role role) {
        roleDao.save(role);
    }

    /**
     * Supprimer un rôle par son Id
     * @param id
     */
    @ApiOperation(value = "Delete a role", response = Void.class)
    @DeleteMapping(value = "/Roles/{id}")
    public void deleteRole(@PathVariable int id) {
        roleDao.deleteById(id);
    }
}

