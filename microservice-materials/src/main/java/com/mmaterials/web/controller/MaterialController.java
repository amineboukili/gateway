package com.mmaterials.web.controller;

import com.mmaterials.configurations.ApplicationPropertiesConfiguration;
import com.mmaterials.dao.MaterialDao;
import com.mmaterials.model.Material;
import com.mmaterials.web.exceptions.MaterialNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class MaterialController {

    @Autowired
    MaterialDao materialDao;

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    // Affiche la liste de tous les materials disponibles
    @GetMapping(value = "/Materials")
    public List<Material> listeDesMaterials(){

        List<Material> materials = materialDao.findAll();

        if(materials.isEmpty()) throw new MaterialNotFoundException("Aucun material n'est disponible à la vente");

        List<Material> listeLimitee = materials.subList(0, appProperties.getLimitOfMaterials());

        return listeLimitee;

    }

    //Récuperer un material par son id
    @GetMapping( value = "/Materials/{id}")
    public Optional<Material> recupererUnMaterial(@PathVariable int id) {

        Optional<Material> material = materialDao.findById(id);

        if(!material.isPresent())  throw new MaterialNotFoundException("Le material correspondant à l'id " + id + " n'existe pas");

        return material;
    }

    /**
     * Ajouter un material
     * @param material
     * @return
     */
    @PostMapping(value = "/Materials", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<Void> addMaterial(@RequestBody Material material) {
        System.out.println("TEST");
        Material materialAdded = materialDao.save(material);

        // Dans le cas où le material ajouté est vide ou n'existe pas, nous retournons le code 204 No Content.
        if (materialAdded == null)
            return ResponseEntity.noContent().build();

        // Dans le cas où tout s'est bien passé, on retourne le code 201 et on ajoute l'URI vers la nouvelle ressource créée afin d'être conforme avec le protocole HTTP.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(materialAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Mettre à jour un material
     * @param material
     */
    @PutMapping(value = "/Materials")
    public void updateMaterial(@RequestBody Material material) {
        materialDao.save(material);
    }

    /**
     * Supprimer un material par son Id
     * @param id
     */
    @DeleteMapping(value = "/Materials/{id}")
    public void deleteMaterial(@PathVariable int id) {
        materialDao.deleteById(id);
    }

}

