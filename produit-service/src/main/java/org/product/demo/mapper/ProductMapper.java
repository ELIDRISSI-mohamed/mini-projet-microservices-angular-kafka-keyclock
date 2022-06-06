package org.product.demo.mapper;

import org.mapstruct.Mapper;
import org.product.demo.dto.ProductReqDTO;
import org.product.demo.dto.ProductResDTO;
import org.product.demo.model.Product;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResDTO productToProductResDTO(Product product);
    Product productReqDTOProduct(ProductReqDTO productReqDTO);
}
