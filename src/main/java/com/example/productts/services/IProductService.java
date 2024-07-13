package com.example.productts.services;

import com.example.productts.dtos.ProductrequestDto;
import com.example.productts.dtos.ProductresponseDto;
import com.example.productts.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    public Product getSingleProduct(Long id);
    public List<ProductresponseDto> getAllproducts();

   public Product updateProduct(Long id, ProductrequestDto productrequestDto);
}
