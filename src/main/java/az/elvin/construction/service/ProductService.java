package az.elvin.construction.service;

import az.elvin.construction.dto.ProductDto;
import az.elvin.construction.dto.ProductDtoList;
import az.elvin.construction.dto.response.CommonResponse;
import az.elvin.construction.entity.ProductEntity;
import az.elvin.construction.repo.ProductImageRepository;
import az.elvin.construction.repo.ProductRepository;
import az.elvin.construction.util.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.List;

import static az.elvin.construction.dto.response.CommonResponse.success;
import static az.elvin.construction.enums.ResponseEnum.PRODUCT_NOT_FOUND;
import static az.elvin.construction.enums.ActiveEnum.ACTIVE;
import static java.util.Objects.isNull;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public CommonResponse getDefaultProductList() {

        long limit = 8;
        long page = 0;

        List<ProductEntity> productEntities = productRepository.findAllByActiveOrderById(limit, page);

        Long pageCount = (long) Math.ceil(((double) productRepository.countAllByActive()) / limit);
        ProductDtoList productDtoList = new ProductDtoList();
        List<ProductDto> productDtos = new LinkedList<>();
        productEntities.forEach(productEntity -> {
            productDtos.add(Method.fileMainRead(productImageRepository
                    .findProductImageEntityByProductId(productEntity.getId()), productEntity, pageCount));
        });
        productDtoList.setProductDtoList(productDtos);
        productDtoList.setPageCount(pageCount);
        return success(productDtoList);
    }


    public CommonResponse getProductList(Long page) {

        int limit = 6;

        List<ProductEntity> productEntities = productRepository.findAllByActiveOrderById(ACTIVE.ordinal(),
                PageRequest.of(page.intValue() - 1, limit));

        Long pageCount = (long) Math.ceil(((double) productRepository.countAllByActive()) / limit);
        ProductDtoList productDtoList = new ProductDtoList();
        List<ProductDto> productDtos = new LinkedList<>();
        productEntities.forEach(productEntity -> {
            productDtos.add(Method.fileMainRead(productImageRepository
                    .findProductImageEntityByProductId(productEntity.getId()), productEntity, pageCount));
        });
        productDtoList.setProductDtoList(productDtos);
        productDtoList.setPageCount(pageCount);
        return success(productDtoList);
    }





}
