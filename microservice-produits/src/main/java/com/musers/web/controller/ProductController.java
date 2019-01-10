package com.musers.web.controller;

import com.musers.configurations.ApplicationPropertiesConfiguration;
import com.musers.dao.ProductDao;
import com.musers.model.Product;
import com.musers.web.exceptions.ProductNotFoundException;
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
public class ProductController {

    @Autowired
    ProductDao productDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    /**
     * Affiche la liste de tous les produits disponibles
     * @return
     */
    @GetMapping(value = "/Produits")
    public List<Product> listeDesProduits(){

        List<Product> products = productDao.findAll();

        if(products.isEmpty()) throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");

        List<Product> listeLimitee = products.subList(0, appProperties.getLimitProducts());

        log.info("Recuperation de la lste des produits");

        return listeLimitee;

    }

    /**
     * Récuperer un produit par son id
     * @param id
     * @return
     */
    @GetMapping( value = "/Produits/{id}")
    public Optional<Product> recupererUnProduit(@PathVariable int id) {

        Optional<Product> product = productDao.findById(id);

        if(!product.isPresent())  throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");

        return product;
    }

    /**
     * Ajouter un produit
     * @param product
     * @return
     */
    @PostMapping(value = "/Produits", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        System.out.println("TEST");
        Product productAdded = productDao.save(product);

        // Dans le cas où le produit ajouté est vide ou n'existe pas, nous retournons le code 204 No Content.
        if (productAdded == null)
            return ResponseEntity.noContent().build();

        // Dans le cas où tout s'est bien passé, on retourne le code 201 et on ajoute l'URI vers la nouvelle ressource créée afin d'être conforme avec le protocole HTTP.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Mettre à jour un produit
     * @param product
     */
    @PutMapping(value = "/Produits")
    public void updateProduct(@RequestBody Product product) {
        productDao.save(product);
    }

    /**
     * Supprimer un produit par son Id
     * @param id
     */
    @DeleteMapping(value = "/Produits/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDao.deleteById(id);
    }
}

