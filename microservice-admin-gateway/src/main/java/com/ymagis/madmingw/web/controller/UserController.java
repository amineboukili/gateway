package com.ymagis.madmingw.web.controller;

import com.ymagis.madmingw.configurations.ApplicationPropertiesConfiguration;
import com.ymagis.madmingw.model.User;
import com.ymagis.madmingw.service.UserService;
import com.ymagis.madmingw.web.exceptions.UserNotFoundException;
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

@Api(value = "user", description = "Rest API for User operations", tags = "User API")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    /**
     * Display a list of all available users
     * @return
     */
    @ApiOperation(value = "Retrieves the list of users", response = User.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/Users")
    public List<User> listeUsers(){

        List<User> users = userService.findAll();

        if(users.isEmpty()) throw new UserNotFoundException("Aucun utilisateur n'est disponible");

        List<User> listeLimitee = users.subList(0, appProperties.getLimitUsers());

        log.info("Recuperation de la lste des utilisateurs");

        return listeLimitee;
    }

    /**
     * Retrieve a product by its id
     * @param id
     * @return
     */
    @ApiOperation(value = "Retrieves a user by Id", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
    @GetMapping( value = "/Users/{id}")
    public Optional<User> getUser(@PathVariable int id) {

        Optional<User> user = userService.findById(id);

        if(!user.isPresent())  throw new UserNotFoundException("L'utilisateur correspondant à l'id " + id + " n'existe pas");

        return user;
    }

    /**
     * Add a user
     * @param user
     * @return
     */
    @ApiOperation(value = "Add a user", response = Void.class)
    @PostMapping(value = "/Users", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        User userAdded = userService.save(user);

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
     * Update a user
     * @param user
     */
    @ApiOperation(value = "Update a user", response = Void.class)
    @PutMapping(value = "/Users")
    public void updateUser(@RequestBody User user) {

        userService.update(user);
    }

    /**
     * Delete a user by its id
     * @param id
     */
    @ApiOperation(value = "Delete a user", response = Void.class)
    @DeleteMapping(value = "/Users/{id}")
    public void deleteUser(@PathVariable int id) {

        userService.deleteById(id);
    }
}

