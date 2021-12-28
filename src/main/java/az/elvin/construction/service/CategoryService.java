package az.elvin.construction.service;
import az.elvin.construction.dto.CategoryDto;
import az.elvin.construction.dto.response.CommonResponse;
import az.elvin.construction.entity.CategoryEntity;
import az.elvin.construction.mapper.CategoryMapper;
import az.elvin.construction.repo.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

import static az.elvin.construction.dto.response.CommonResponse.success;
import static az.elvin.construction.enums.ActiveEnum.ACTIVE;

@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CommonResponse getCategoryList() {

        List<CategoryEntity> categoryEntities = categoryRepository.findAllByActiveOrderById(ACTIVE.ordinal());

        List<CategoryDto> categoryDtos = new LinkedList<>();
        categoryEntities.forEach(categoryEntity -> {
            categoryDtos.add(CategoryMapper.INSTANCE.entityToDto(categoryEntity));
        });
        return success(categoryDtos);
    }
}
