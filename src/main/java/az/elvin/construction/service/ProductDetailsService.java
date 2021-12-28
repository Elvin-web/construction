package az.elvin.construction.service;

import az.elvin.construction.dto.ProductDto;
import az.elvin.construction.dto.response.CommonResponse;
import az.elvin.construction.entity.ProductEntity;
import az.elvin.construction.entity.ProductImageEntity;
import az.elvin.construction.exception.CommonException;
import az.elvin.construction.mapper.ProductMapper;
import az.elvin.construction.repo.ProductImageRepository;
import az.elvin.construction.repo.ProductRepository;
import az.elvin.construction.util.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import static az.elvin.construction.dto.response.CommonResponse.success;
import static az.elvin.construction.enums.ResponseEnum.PRODUCT_NOT_FOUND;
import static java.util.Objects.isNull;

@Service
@Slf4j
public class ProductDetailsService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductDetailsService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }


    public CommonResponse productInfoByName(String name) {
        log.info("*****Product Info By Name Service*****");
        log.info(String.format("name = %s", name));


        ProductEntity productEntity=productRepository.findByName(name);

        Optional.ofNullable(productEntity).orElseThrow(() -> new CommonException(PRODUCT_NOT_FOUND));


        ProductDto productDto = ProductMapper.INSTANCE.entityToByProductInfoEntity(productEntity);

        ProductImageEntity productImageEntity = productImageRepository.findProductImageEntityByProductId(productEntity.getId());

        try {

            if (!isNull(productImageEntity.getMainImage()) && !productImageEntity.getMainImage().isEmpty()) {
                productDto.setMainImageName(Method.fileRead(productImageEntity.getMainImage()));
            }
            if (!isNull(productImageEntity.getFirstImage()) && !productImageEntity.getFirstImage().isEmpty()) {
                productDto.setFirstImageName(Method.fileRead(productImageEntity.getFirstImage()));
            }
            if (!isNull(productImageEntity.getSecondImage()) && !productImageEntity.getSecondImage().isEmpty()) {
                productDto.setSecondImageName(Method.fileRead(productImageEntity.getSecondImage()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = productEntity.getShowCount();
        productEntity.setShowCount(++count);
        productRepository.save(productEntity);
        return success(productDto);
    }
}
