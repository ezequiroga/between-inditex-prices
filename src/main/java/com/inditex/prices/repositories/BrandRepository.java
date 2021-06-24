package com.inditex.prices.repositories;

import com.inditex.prices.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Eze Q.
 */
public interface BrandRepository extends JpaRepository<Brand, Integer>{
    
}
