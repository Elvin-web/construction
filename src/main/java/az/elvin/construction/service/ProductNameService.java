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
public class ProductNameService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductNameService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }


    public CommonResponse productInfoByNameAndPage(String name, Long page) {
        log.info("*****Product Info By Name Service*****");
        log.info(String.format("name = %s", name));

        int limit = 6;

        List<ProductEntity> productEntities = productRepository.findProductEntitiesByLikeName(name,
                PageRequest.of(page.intValue() - 1, limit));
        Long pageCount = (long) Math.ceil(((double) productRepository.countProductByNameAndActive(name)) / limit);
        ProductDtoList productDtoList = new ProductDtoList();
        List<ProductDto> productDtos = new LinkedList<>();
        productEntities.forEach(productEntity -> {
            productDtos.add(Method.fileMainRead(productImageRepository.findProductImageEntityByProductId(productEntity.getId()),
                    productEntity, pageCount));
        });
        productDtoList.setProductDtoList(productDtos);
        productDtoList.setPageCount(pageCount);
        productDtoList.setName(name);
        return success(productDtoList);
    }
}
