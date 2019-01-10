package com.mpartners.web.controller;

import com.mpartners.configurations.ApplicationPropertiesConfiguration;
import com.mpartners.dao.PartnerDao;
import com.mpartners.model.Partner;
import com.mpartners.web.exceptions.PartnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PartnerController {

    @Autowired
    PartnerDao partnerDao;

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    // Affiche la liste de tous les partenaires disponibles
    @GetMapping(value = "/Partners")
    public List<Partner> listOfPartners(){

        List<Partner> partners = partnerDao.findAll();

        if(partners.isEmpty()) throw new PartnerNotFoundException("Aucun partenaire n'est disponible");

        List<Partner> listeLimitee = partners.subList(0, appProperties.getLimitOfPartners());

        return listeLimitee;

    }

    //Récuperer un partenaire par son id
    @GetMapping( value = "/Partners/{id}")
    public Optional<Partner> recoverPartner(@PathVariable int id) {

        Optional<Partner> partner = partnerDao.findById(id);

        if(!partner.isPresent())  throw new PartnerNotFoundException("Le partenaire correspondant à l'id " + id + " n'existe pas");

        return partner;
    }

    /**
     * Ajouter un partenaire
     * @param partner
     * @return
     */
    @PostMapping(value = "/Partners", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<Void> addPartner(@RequestBody Partner partner) {
        System.out.println("TEST");
        Partner partnerAdded = partnerDao.save(partner);

        // Dans le cas où le partenaire ajouté est vide ou n'existe pas, nous retournons le code 204 No Content.
        if (partnerAdded == null)
            return ResponseEntity.noContent().build();

        // Dans le cas où tout s'est bien passé, on retourne le code 201 et on ajoute l'URI vers la nouvelle ressource créée afin d'être conforme avec le protocole HTTP.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(partnerAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Mettre à jour un partenaire
     * @param partner
     */
    @PutMapping(value = "/Partners")
    public void updatePartner(@RequestBody Partner partner) {
        partnerDao.save(partner);
    }

    /**
     * Supprimer un partenaire par son Id
     * @param id
     */
    @DeleteMapping(value = "/Partners/{id}")
    public void deletePartner(@PathVariable int id) {
        partnerDao.deleteById(id);
    }
}

