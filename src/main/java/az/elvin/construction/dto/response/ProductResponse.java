package az.elvin.construction.dto.response;

import az.elvin.construction.dto.ProductDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse {

    private List<ProductDto> products;

}
