package com.example.LaptopKG.repository;

import com.example.LaptopKG.model.Brand;
import com.example.LaptopKG.model.Hardware;
import com.example.LaptopKG.model.Laptop;
import com.example.LaptopKG.model.enums.Category;
import com.example.LaptopKG.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    List<Laptop> findAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String query, String query1);
    Page<Laptop> findAllByStatus(Status status, Pageable pageable);
    Page<Laptop> findAllByCategoryAndStatus(Category category, Status status, Pageable pageable);
    Page<Laptop> findAllByBrandAndStatus(Brand brand, Status status, Pageable pageable);
    Page<Laptop> findAllByBrandAndStatusAndIdNot(Brand brand, Status status, Long id, Pageable pageable);
    Page<Laptop> findAllByStatusAndBrandAndCategory(
            Status status, Brand brand, Category category, Pageable pageable);
    Page<Laptop> findAllByStatusAndCategory(
            Status status, Category category, Pageable pageable);
    Page<Laptop> findAllByStatusAndBrand(
            Status status, Brand brand, Pageable pageable);


}
