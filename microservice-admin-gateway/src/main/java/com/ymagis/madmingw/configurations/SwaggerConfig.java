package com.ymagis.madmingw.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Amine BOUKILI
 */

@Configuration // Remplace un fichier de configuration XML en nous donne accès à plusieurs méthodes pour personnaliser Swagger grâce à la classe Docket qui gère toutes les configurations
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)  // Initialiser un objet Docket en précisant que nous souhaitons utiliser swagger 2
                .select() // Initialiser une classe du nom de ApiSelectorBuilder qui donne accès aux méthodes de personnalisation suivantes
                //.apis(RequestHandlerSelectors.any()) // Indiquer vouloir documenter toutes les classes dans tous les packages.
                .apis(RequestHandlerSelectors.basePackage("com.ymagis.madmingw.web.controller")) // Nous avons utilisé la méthode basePackage du prédicat RequestHandlerSelectors afin de demander à Swagger de ne documenter que le package "web" qui contient le code
                .paths(PathSelectors.any()) // Filtrer selon l'URI des requêtes. Ex : On peut demander à Swagger de ne documenter que les méthodes qui répondent à des requêtes commençant par "/public".
                .build();
    }


    private ApiInfo getApiInfo() {
        Contact contact = new Contact("Amine BOUKILI", "http://www.ymagis.com", "amine.boukili@ymagis.com");
        return new ApiInfoBuilder()
                .title("Administration Gateway Api")
                .description("Administration Gateway Api")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }
}
