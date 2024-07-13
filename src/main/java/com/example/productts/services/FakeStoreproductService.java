package com.example.productts.services;

import com.example.productts.config.ApplicationConfiguration;
import com.example.productts.dtos.ProductrequestDto;
import com.example.productts.dtos.ProductresponseDto;
import com.example.productts.dtos.RatingResponseDto;
import com.example.productts.models.Category;
import com.example.productts.models.FakeStoreProduct;
import com.example.productts.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreproductService implements IProductService{

     @Autowired
     RestTemplate restTemplate;

    public Product getProductfromresponseDto(ProductresponseDto productresponseDto){
              Product product=new Product();
              product.setId(productresponseDto.getId());
              product.setName(productresponseDto.getTitle());
              product.setPrice(productresponseDto.getPrice());
              product.setDesc(productresponseDto.getDesc());
              product.setImage(productresponseDto.getImage());
//              product.setCategory(new Category());
//              product.getCategory().setName(productresponseDto.getCategory());
              Category category=new Category();
              category.setName(productresponseDto.getCategory());
              product.setCategory(category);
              
        return product;
    }

    public ProductresponseDto getProductresponsedtofromfakestoreproduct(FakeStoreProduct fakeStoreProduct){
        ProductresponseDto product=new ProductresponseDto();
        product.setId(fakeStoreProduct.getId());
        product.setTitle(fakeStoreProduct.getTitle());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDesc(fakeStoreProduct.getDescription());
        product.setImage(fakeStoreProduct.getImage());
        product.setCategory(fakeStoreProduct.getCategory());
        RatingResponseDto ratingResponseDto=new RatingResponseDto();
        ratingResponseDto.setRate(fakeStoreProduct.getRating().getRate());
        ratingResponseDto.setCount(fakeStoreProduct.getRating().getCount());
        product.setRating(ratingResponseDto);
//              product.setCategory(new Category());
//              product.getCategory().setName(productresponseDto.getCategory());
        /*Category category=new Category();
        category.setName(productresponseDto.getCategory());
        product.setCategory(category);*/

        return product;
    }
    @Override
    public Product getSingleProduct(Long id) {
        //this is the place where i should pass the id to get the product details from fakestoreapi
        //https://fakestoreapi.com/products/1
        ProductresponseDto response = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, ProductresponseDto.class);
        return getProductfromresponseDto(response);
    }

    @Override
    public List<ProductresponseDto> getAllproducts() {
        FakeStoreProduct[] responsedtolist = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProduct[].class);
        List<ProductresponseDto> output=new ArrayList<>();

        for (FakeStoreProduct fakeStoreProduct : responsedtolist){
            output.add(getProductresponsedtofromfakestoreproduct(fakeStoreProduct));
        }
         return output;
    }

    @Override
    public Product updateProduct(Long id, ProductrequestDto productrequestDto) {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(productrequestDto,ProductresponseDto.class);

        HttpMessageConverterExtractor<ProductresponseDto> responseExtractor =
                new HttpMessageConverterExtractor(ProductresponseDto.class, restTemplate.getMessageConverters());
         ProductresponseDto responseDto= restTemplate.
                 execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
/*        //this is not returning anything but i want updated version
        restTemplate.put("https://fakestoreapi.com/products/" + id, productresponseDto);
        return  getSingleProduct(id);

        //will above two lines of code will work or not*/

        return  getProductfromresponseDto(responseDto);
    }





}
