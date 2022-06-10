package org.product.demo.service;

import org.product.demo.dto.ProductReqDTO;
import org.product.demo.dto.ProductResDTO;
import org.product.demo.exception.ExceptionTechnical;
import org.product.demo.model.Product;
import org.product.demo.model.ProductQte;

import java.util.List;

public interface ProductService {
    ProductResDTO save(ProductReqDTO productReqDTO) throws ExceptionTechnical;
    ProductResDTO getProduct(Long id) throws ExceptionTechnical;
    ProductResDTO updateProduct(ProductReqDTO productReqDTO) throws ExceptionTechnical;
    void deleteProduct(Long id) throws ExceptionTechnical;
    List<ProductResDTO> allProducts() throws ExceptionTechnical;
    ProductResDTO updateProductQte(ProductQte productQte) throws ExceptionTechnical;

}
