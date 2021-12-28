package az.elvin.construction.mapper;

import az.elvin.construction.dto.CategoryDto;
import az.elvin.construction.dto.ProductDto;
import az.elvin.construction.entity.CategoryEntity;
import az.elvin.construction.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CategoryMapper {

    public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);


    @Mapping(target = "name", source = "name")
    public abstract CategoryDto entityToDto(CategoryEntity categoryEntity);

}
