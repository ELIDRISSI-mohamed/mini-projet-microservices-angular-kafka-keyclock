package org.product.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.product.demo.dto.ProductReqDTO;
import org.product.demo.dto.ProductResDTO;
import org.product.demo.exception.ExceptionTechnical;
import org.product.demo.service.ProductService;
import org.product.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/all")
    public List<ProductResDTO> getAllProducts() throws ExceptionTechnical {
        log.info("Get product");

        return productService.allProducts();
    }

}
