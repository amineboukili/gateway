package com.maddresses.web.controller;

import com.maddresses.configurations.ApplicationPropertiesConfiguration;
import com.maddresses.dao.AddressDao;
import com.maddresses.model.Address;
import com.maddresses.web.exceptions.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {

    @Autowired
    AddressDao addressDao;

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    // Affiche la liste des adresses disponibles
    @GetMapping(value = "/Addresses")
    public List<Address> listOfAddresses(){

        List<Address> addresses = addressDao.findAll();

        if(addresses.isEmpty()) throw new AddressNotFoundException("Aucune adresse n'est disponible");

        List<Address> listeLimitee = addresses.subList(0, appProperties.getLimitOfAddresses());

        return listeLimitee;

    }

    //Récuperer une adresse par son id
    @GetMapping( value = "/Addresses/{id}")
    public Optional<Address> recoverAddress(@PathVariable int id) {

        Optional<Address> address = addressDao.findById(id);

        if(!address.isPresent())  throw new AddressNotFoundException("L'adresse correspondante à l'id " + id + " n'existe pas");

        return address;
    }


    /**
     * Ajouter une adresse
     * @param address
     * @return
     */
    @PostMapping(value = "/Addresses", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<Void> addPartner(@RequestBody Address address) {
        System.out.println("TEST");
        Address addressAdded = addressDao.save(address);

        // Dans le cas où l'adresse ajoutée est vide ou n'existe pas, nous retournons le code 204 No Content.
        if (addressAdded == null)
            return ResponseEntity.noContent().build();

        // Dans le cas où tout s'est bien passé, on retourne le code 201 et on ajoute l'URI vers la nouvelle ressource créée afin d'être conforme avec le protocole HTTP.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addressAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Mettre à jour une adresse
     * @param address
     */
    @PutMapping(value = "/Addresses")
    public void updateAddress(@RequestBody Address address) {
        addressDao.save(address);
    }

    /**
     * Supprimer une adresse par son Id
     * @param id
     */
    @DeleteMapping(value = "/Addresses/{id}")
    public void deleteAddress(@PathVariable int id) {
        addressDao.deleteById(id);
    }
}

