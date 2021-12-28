package az.elvin.construction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoList {

    private List<ProductDto> productDtoList;

    private Long categoryId;

    private String categoryName;

    private Long pageCount;

    private String name;

    private Double min;

    private Double max;
}
