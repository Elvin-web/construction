package az.elvin.construction.util;

import az.elvin.construction.dto.ProductDto;
import az.elvin.construction.entity.ProductEntity;
import az.elvin.construction.entity.ProductImageEntity;
import az.elvin.construction.mapper.ProductMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import static java.util.Objects.isNull;

public class Method {

//    private static final String url = "C:\\Users\\DELL\\Desktop\\freeLance\\upload\\";

   // private static final String url = "http://127.0.1.1:8080/images/";

    private static final String url = "/opt/tomcat/webapps/images/";


    public static String fileRead(String image) throws IOException {

        String fileImageName = url + image;
        File fileImage = new File(fileImageName);
        byte[] fileBytesImage = Files.readAllBytes(fileImage.toPath());
        return Base64.getEncoder().encodeToString(fileBytesImage);
    }

    public static ProductDto fileMainRead(ProductImageEntity productImageEntity
            , ProductEntity productEntity, Long pageCount) {

        ProductDto productDto = ProductMapper.INSTANCE.entityToDto(productEntity);

        productDto.setPageCount(pageCount);

        if (!isNull(productImageEntity.getMainImage()) && !productImageEntity.getMainImage().isEmpty()) {
            try {
                productDto.setMainImageName(Method.fileRead(productImageEntity.getMainImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return productDto;
    }
}
