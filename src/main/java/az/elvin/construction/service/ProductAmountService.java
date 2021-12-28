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

import java.util.LinkedList;
import java.util.List;

import static az.elvin.construction.dto.response.CommonResponse.success;
import static az.elvin.construction.enums.ActiveEnum.ACTIVE;

@Service
@Slf4j
public class ProductAmountService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductAmountService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }


    public CommonResponse productInfoByAmountAndPage(Double min, Double max, Long page) {
        log.info("*****Product Info By Amount Service*****");
        log.info(String.format("min amount= %s", min, "max amount= %s", max));

        int limit = 6;

        List<ProductEntity> productEntities = productRepository.findProductEntitiesByAmount(min, max, limit, page);

        Long pageCount = (long) Math.ceil(((double) productRepository.countProductByAmountAndActive(min, max)) / limit);

        ProductDtoList productDtoList = new ProductDtoList();

        List<ProductDto> productDtos = new LinkedList<>();

        productEntities.forEach(productEntity -> {
            productDtos.add(Method.fileMainRead(productImageRepository.findProductImageEntityByProductId(productEntity.getId()), productEntity, pageCount));
        });
        productDtoList.setProductDtoList(productDtos);
        productDtoList.setPageCount(pageCount);
        productDtoList.setMax(max);
        productDtoList.setMin(min);
        return success(productDtoList);
    }

}
