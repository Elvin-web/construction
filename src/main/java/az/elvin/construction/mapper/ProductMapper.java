package az.elvin.construction.mapper;

import az.elvin.construction.dto.ProductDto;
import az.elvin.construction.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public abstract class ProductMapper {

    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Mappings({@Mapping(target = "id", source = "id"),
            @Mapping(target = "brendId", source = "brend.id"),
            @Mapping(target = "categoryId", source = "category.id"),
            @Mapping(target = "colourId", source = "colour.id"),
            @Mapping(target = "countryId", source = "country.id"),
            @Mapping(target = "havingStatusName", source = "havingStatus", qualifiedByName = "havingStatus"),
            @Mapping(target = "sizeId", source = "size.id")})
    public abstract ProductDto entityToDto(ProductEntity productEntity);


    @Named(value = "havingStatusName")
    public static String havingStatusName(Integer havingStatus) {
        if (havingStatus.equals(0))
            return "Yoxdur";
        else if (havingStatus.equals(1))
            return "Var";
        return null;
    }


    @Mappings({@Mapping(target = "id", source = "id"),
            @Mapping(target = "brendId", source = "brend.id"),
            @Mapping(target = "categoryId", source = "category.id"),
            @Mapping(target = "categoryName", source = "category.name"),
            @Mapping(target = "colourId", source = "colour.id"),
            @Mapping(target = "countryId", source = "country.id"),
            @Mapping(target = "havingStatusName", source = "havingStatus", qualifiedByName = "havingStatus"),
            @Mapping(target = "createDate", source = "dataDate", qualifiedByName = "createDate"),
            @Mapping(target = "sizeId", source = "size.id"),
            @Mapping(target = "sizeName", source = "size.name")})
    public abstract ProductDto entityToByProductInfoEntity(ProductEntity productEntity);

    @Named(value = "createDate")
    public static String createDate(LocalDateTime createDate) {

        return createDate != null ? dateTimeFormatter.format(createDate) : null;
    }
}
