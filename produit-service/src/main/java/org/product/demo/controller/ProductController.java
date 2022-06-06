package org.product.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.product.demo.dto.ProductReqDTO;
import org.product.demo.dto.ProductResDTO;
import org.product.demo.exception.ExceptionTechnical;
import org.product.demo.service.ProductService;
import org.product.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PUT;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {
    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ProductResDTO save(@RequestBody ProductReqDTO productReqDTO) throws ExceptionTechnical {
        log.info("Save product");
        productReqDTO.setId(0L);
        System.out.println(productReqDTO);
        return productService.save(productReqDTO);
    }
    @GetMapping("/{id}")
    public ProductResDTO getProduct(@PathVariable Long id) throws ExceptionTechnical {
        log.info("Get product");
        return productService.getProduct(id);
    }
    @PutMapping("/update")
    public ProductResDTO updateProduct(@RequestBody ProductReqDTO productReqDTO) throws ExceptionTechnical {
        log.info("Update product");
        return productService.updateProduct(productReqDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) throws ExceptionTechnical {
        log.info("Delete product");
        productService.deleteProduct(id);
    }
    //@PreAuthorize("hasRole('ROLE_PRODUCT_MANAGER')")
    //@Secured("ROLE_PRODUCT_MANAGER")
    @GetMapping("/all")
    public List<ProductResDTO> getAllProducts(@RequestHeader("Authorization") String token) throws ExceptionTechnical {
        log.info("Get product");
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
                KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
                // retrieving username here
                String username = kp.getKeycloakSecurityContext().getToken().getPreferredUsername();
            }
        }
         */
        System.out.println(token);
        return productService.allProducts();
    }

}
