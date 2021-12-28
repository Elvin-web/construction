package az.elvin.construction.service;

import az.elvin.construction.dto.ProductDto;
import az.elvin.construction.dto.ProductDtoList;
import az.elvin.construction.dto.response.CommonResponse;
import az.elvin.construction.entity.ProductEntity;
import az.elvin.construction.entity.ProductImageEntity;
import az.elvin.construction.exception.CommonException;
import az.elvin.construction.mapper.ProductMapper;
import az.elvin.construction.repo.ProductImageRepository;
import az.elvin.construction.repo.ProductRepository;
import az.elvin.construction.util.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static az.elvin.construction.dto.response.CommonResponse.success;
import static az.elvin.construction.enums.ActiveEnum.ACTIVE;
import static az.elvin.construction.enums.ResponseEnum.PRODUCT_NOT_FOUND;
import static java.util.Objects.isNull;

@Service
@Slf4j
public class ProductCategoryService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductCategoryService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }


    public CommonResponse productInfoByCategoryNameAndPageAndAmount(String name, Long page, Double min, Double max) {
        log.info("*****Product Info By Category name Service*****");
        log.info(String.format("name = %s", name));

        int limit = 6;

        List<ProductEntity> productEntities = productRepository.
                findProductEntitiesByCategory_NameAndAmountAndActiveOrderById(name, min, max,
                        PageRequest.of(page.intValue() - 1, limit));

        Long pageCount = (long) Math.ceil(((double) productRepository.countProductEntitiesByCategory_NameAndAmountAndActiveOrderById(name, min, max)) / limit);

        ProductDtoList productDtoList = new ProductDtoList();
        List<ProductDto> productDtos = new LinkedList<>();
        productEntities.forEach(productEntity -> {

            productDtos.add(Method.fileMainRead(productImageRepository.findProductImageEntityByProductId(productEntity.getId()),
                    productEntity, pageCount));
        });
        productDtoList.setProductDtoList(productDtos);
        productDtoList.setPageCount(pageCount);
        productDtoList.setCategoryName(name);
        productDtoList.setMin(min);
        productDtoList.setMax(max);
        return success(productDtoList);
    }
}
