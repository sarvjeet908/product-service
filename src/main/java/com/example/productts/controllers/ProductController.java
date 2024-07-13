package com.example.productts.controllers;

import com.example.productts.dtos.ProductrequestDto;
import com.example.productts.dtos.ProductresponseDto;
import com.example.productts.models.Product;
import com.example.productts.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private  IProductService productService;


    //get all the products
    @GetMapping("/products")
    public List<ProductresponseDto> getAllproducts(){
        return  productService.getAllproducts();
    }

    //get a product with id
    @GetMapping("/products/{id}")
    public Product getProductwithid(@PathVariable("id") Long id){

        return productService.getSingleProduct(id);  //Controller calling service
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id , @RequestBody ProductrequestDto productrequestDto){
      return productService.updateProduct(id,productrequestDto);
    }
    @PostMapping("/products")
    public ProductresponseDto  addProduct(@RequestBody ProductrequestDto productrequestDto){
        return new ProductresponseDto();
    }
    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id ){
      return true;
    }

}
