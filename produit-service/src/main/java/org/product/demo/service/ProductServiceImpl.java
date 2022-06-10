package org.product.demo.service;

import org.product.demo.dto.ProductReqDTO;
import org.product.demo.dto.ProductResDTO;
import org.product.demo.exception.ExceptionCode;
import org.product.demo.exception.ExceptionTechnical;
import org.product.demo.mapper.ProductMapper;
import org.product.demo.model.Product;
import org.product.demo.model.ProductQte;
import org.product.demo.repository.ProductRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductMapper productMapper;
    private ProductRepo productRepo;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepo productRepo) {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
    }

    @Override
    public ProductResDTO save(ProductReqDTO productReqDTO) throws ExceptionTechnical {
        Product product = productMapper.productReqDTOProduct(productReqDTO);
        Optional<Product> p = productRepo.findById(product.getId());
        if(p.isPresent()) {
            throw new ExceptionTechnical(ExceptionCode.PRODUCT_ALREADY_EXIST);
        }
        Product saveProduct = productRepo.save(product);
        return productMapper.productToProductResDTO(saveProduct);
    }

    @Override
    public ProductResDTO getProduct(Long id) throws ExceptionTechnical {
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()) {
            throw new ExceptionTechnical(ExceptionCode.PRODUCT_NOT_EXIST);
        }
        return productMapper.productToProductResDTO(product.get());
    }

    @Override
    public ProductResDTO updateProduct(ProductReqDTO productReqDTO) throws ExceptionTechnical {
        Product product = productMapper.productReqDTOProduct(productReqDTO);
        Optional<Product> p = productRepo.findById(product.getId());
        if(!p.isPresent()){
            throw new ExceptionTechnical(ExceptionCode.PRODUCT_NOT_EXIST);
        }
        Product updateProduct = productRepo.save(product);
        return productMapper.productToProductResDTO(updateProduct);
    }

    @Override
    public void deleteProduct(Long id) throws ExceptionTechnical {
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()) {
            throw new ExceptionTechnical(ExceptionCode.PRODUCT_NOT_EXIST);
        }
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductResDTO> allProducts() throws ExceptionTechnical {
        List<Product> products = productRepo.findAll();
        if(products.isEmpty()) {
            throw new ExceptionTechnical(ExceptionCode.PRODUCT_IS_EMPTY);
        }
        return products.stream()
                .map(product -> productMapper.productToProductResDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResDTO updateProductQte(ProductQte productQte) throws ExceptionTechnical {
        Product product = productRepo.findById(productQte.getId()).get();
        if (product==null) throw new ExceptionTechnical(ExceptionCode.PRODUCT_NOT_EXIST);
        product.setQte(product.getQte()-productQte.getQte());
        Product updateProduct = productRepo.save(product);
        return  productMapper.productToProductResDTO(updateProduct);
    }
}
