package com.madmingw.web.controller;

import com.madmingw.configurations.ApplicationPropertiesConfiguration;
import com.madmingw.dao.RoleDao;
import com.madmingw.model.Role;
import com.madmingw.web.exceptions.RoleNotFoundException;
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
    @PutMapping(value = "/Roles")
    public void updateRole(@RequestBody Role role) {
        roleDao.save(role);
    }

    /**
     * Supprimer un rôle par son Id
     * @param id
     */
    @DeleteMapping(value = "/Roles/{id}")
    public void deleteRole(@PathVariable int id) {
        roleDao.deleteById(id);
    }
}

