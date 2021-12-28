package az.elvin.construction.repo;


import az.elvin.construction.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    ProductEntity findByName(String name);

    @Query(value = "select p.* from pr.product p  " +
            "where p.active =1  " +
            "order by p.id desc limit :limit offset :page ", nativeQuery = true)
    List<ProductEntity> findAllByActiveOrderById(@Param("limit") long limit,
                                                 @Param("page") long page);

    List<ProductEntity> findAllByActiveOrderById(Integer active, Pageable pageable);

    @Query(value = "select count(p.id) from pr.product p  " +
            "where p.active =1 ", nativeQuery = true)
    Integer countAllByActive();

    @Query(value = "select p.* from pr.product p where p.active =1 and  lower(p.name)  " +
            "like lower(concat('%', :name, '%')) order by p.id desc ", nativeQuery = true)
    List<ProductEntity> findProductEntitiesByLikeName(@Param("name") String name, Pageable pageable);

    @Query(value = "select count(p.id) from pr.product p  " +
            "where p.active =1 and lower(p.name) like lower(concat('%', :name, '%')) ", nativeQuery = true)
    Integer countProductByNameAndActive(@Param("name") String name);


    @Query(value = "select p.* from pr.product p where  " +
            "  CASE " +
            "    WHEN CAST(cast (:minAmount as text) as numeric) is not null THEN p.old_amount >CAST (cast (:minAmount as text) as numeric) " +
            "    WHEN CAST (cast (:maxAmount as text) as numeric) is not null THEN p.old_amount <CAST (cast (:maxAmount as text) as numeric) " +
            "    WHEN CAST (cast (:maxAmount as text) as numeric) is not null and CAST (cast (:minAmount as text) as numeric) is not null  " +
            "THEN p.old_amount BETWEEN CAST (cast (:minAmount  as text) as numeric) AND CAST (cast (:maxAmount as text) as numeric) " +
            "ELSE true" +
            " END order by p.id desc limit :limit offset :page ", nativeQuery = true)
    List<ProductEntity> findProductEntitiesByAmount(@Param("minAmount") Double minAmount,
                                                    @Param("maxAmount") Double maxAmount,
                                                    @Param("limit") long limit,
                                                    @Param("page") long page);


    @Query(value = "select count(p.id) from pr.product p  " +
            "where p.active =1 and " +
            "  CASE " +
            "    WHEN CAST(cast (:minAmount as text) as numeric) is not null THEN p.old_amount >CAST (cast (:minAmount as text) as numeric) " +
            "    WHEN CAST (cast (:maxAmount as text) as numeric) is not null THEN p.old_amount <CAST (cast (:maxAmount as text) as numeric) " +
            "    WHEN CAST (cast (:maxAmount as text) as numeric) is not null and CAST (cast (:minAmount as text) as numeric) is not null  " +
            "THEN p.old_amount BETWEEN CAST (cast (:minAmount  as text) as numeric) AND CAST (cast (:maxAmount as text) as numeric) " +
            "ELSE true" +
            " END ", nativeQuery = true)
    Integer countProductByAmountAndActive(@Param("minAmount") Double minAmount,
                                          @Param("maxAmount") Double maxAmount);



    @Query(value = "select p.* from pr.product p  " +
            "inner join pr.category c " +
            "on c.id=p.category_id " +
            "where p.active =1 and c.name = :name and " +
            "  CASE " +
            "    WHEN CAST(cast (:minAmount as text) as numeric) is not null THEN p.old_amount >CAST (cast (:minAmount as text) as numeric) " +
            "    WHEN CAST (cast (:maxAmount as text) as numeric) is not null THEN p.old_amount <CAST (cast (:maxAmount as text) as numeric) " +
            "    WHEN CAST (cast (:maxAmount as text) as numeric) is not null and CAST (cast (:minAmount as text) as numeric) is not null  " +
            "THEN p.old_amount BETWEEN CAST (cast (:minAmount  as text) as numeric) AND CAST (cast (:maxAmount as text) as numeric) " +
            "ELSE true" +
            " END ", nativeQuery = true)
    List<ProductEntity> findProductEntitiesByCategory_NameAndAmountAndActiveOrderById(
            @Param("name") String name,
            @Param("minAmount") Double min,
            @Param("maxAmount") Double max,
            Pageable pageable);


    @Query(value = "select count(p.id) from pr.product p  " +
            "inner join pr.category c " +
            "on c.id=p.category_id " +
            "where p.active =1 and c.name = :name and" +
            "  CASE " +
            "    WHEN CAST(cast (:minAmount as text) as numeric) is not null THEN p.old_amount >CAST (cast (:minAmount as text) as numeric) " +
            "    WHEN CAST (cast (:maxAmount as text) as numeric) is not null THEN p.old_amount <CAST (cast (:maxAmount as text) as numeric) " +
            "    WHEN CAST (cast (:maxAmount as text) as numeric) is not null and CAST (cast (:minAmount as text) as numeric) is not null  " +
            "THEN p.old_amount BETWEEN CAST (cast (:minAmount  as text) as numeric) AND CAST (cast (:maxAmount as text) as numeric) " +
            "ELSE true" +
            " END ", nativeQuery = true)
    Integer countProductEntitiesByCategory_NameAndAmountAndActiveOrderById(
            @Param("name") String name,
            @Param("minAmount") Double min,
            @Param("maxAmount") Double max);
}
