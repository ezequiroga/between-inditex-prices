package com.inditex.prices.repositories;

import com.inditex.prices.entities.Price;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eze Q.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
    
    @Query("select p from Price p "
            + "where p.startDate <= :date "
            + "and p.endDate >= :date "
            + "and p.productId = :productId "
            + "and p.brand.id = :brandId "
            + "order by p.priority desc")
    public List<Price> findPrice(
            @Param("date") Long date,
            @Param("productId") int productId,
            @Param("brandId") int brandId);
    
}
