package az.elvin.construction.repo;


import az.elvin.construction.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {

    ProductImageEntity findProductImageEntityByProductId(Long productId);

}
