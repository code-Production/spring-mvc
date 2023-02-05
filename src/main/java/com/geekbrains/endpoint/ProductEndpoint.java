package com.geekbrains.endpoint;

import com.geekbrains.mapper.ProductMapper;
import com.geekbrains.model.Product;
import com.geekbrains.service.ProductService;
import com.geekbrains.soap.product.GetProductsByFilterRequest;
import com.geekbrains.soap.product.GetProductsByFilterResponse;
import com.geekbrains.soap.product.ProductSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://geekbrains.com/product";

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsByFilterRequest")
    @ResponsePayload
    public GetProductsByFilterResponse getProductsByFilter(@RequestPayload GetProductsByFilterRequest request) {

        List<Product> productList = productService.getFilteredProducts(
                request.getMinPrice(),
                request.getMaxPrice(),
                request.getPageNum()
                ).getContent();
        List<ProductSoap> productSoapList = productList.stream().map(ProductMapper.MAPPER::toSoap).toList();
        GetProductsByFilterResponse response = new GetProductsByFilterResponse();
        productSoapList.forEach(ps -> response.getProductSoap().add(ps));
        return response;
    }
}
