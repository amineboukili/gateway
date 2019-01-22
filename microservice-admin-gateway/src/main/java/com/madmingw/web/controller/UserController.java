package com.madmingw.web.controller;

import com.madmingw.configurations.ApplicationPropertiesConfiguration;
import com.madmingw.dao.UserDao;
import com.madmingw.model.User;
import com.madmingw.web.exceptions.UserNotFoundException;
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
public class UserController {

    @Autowired
    UserDao userDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    /**
     * Affiche la liste de tous les utilisateurs disponibles
     * @return
     */
    @GetMapping(value = "/Users")
    public List<User> listeUsers(){

        List<User> users = userDao.findAll();

        if(users.isEmpty()) throw new UserNotFoundException("Aucun utilisateur n'est disponible");

        List<User> listeLimitee = users.subList(0, appProperties.getLimitUsers());

        log.info("Recuperation de la lste des utilisateurs");

        return listeLimitee;

    }

    /**
     * Récuperer un produit par son id
     * @param id
     * @return
     */
    @GetMapping( value = "/Users/{id}")
    public Optional<User> getUser(@PathVariable int id) {

        Optional<User> user = userDao.findById(id);

        if(!user.isPresent())  throw new UserNotFoundException("L'utilisateur correspondant à l'id " + id + " n'existe pas");

        return user;
    }

    /**
     * Ajouter un utilisateur
     * @param user
     * @return
     */
    @PostMapping(value = "/Users", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        User userAdded = userDao.save(user);

        // Dans le cas où l'utilisateur ajouté est vide ou n'existe pas, nous retournons le code 204 No Content.
        if (userAdded == null)
            return ResponseEntity.noContent().build();

        // Dans le cas où tout s'est bien passé, on retourne le code 201 et on ajoute l'URI vers la nouvelle ressource créée afin d'être conforme avec le protocole HTTP.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Mettre à jour un utilisateur
     * @param user
     */
    @PutMapping(value = "/Users")
    public void updateUser(@RequestBody User user) {
        userDao.save(user);
    }

    /**
     * Supprimer un utilisateur par son Id
     * @param id
     */
    @DeleteMapping(value = "/Users/{id}")
    public void deleteUser(@PathVariable int id) {
        userDao.deleteById(id);
    }
}

