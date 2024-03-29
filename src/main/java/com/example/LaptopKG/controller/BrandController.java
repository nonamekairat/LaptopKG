package com.example.LaptopKG.controller;


import com.example.LaptopKG.dto.brand.RequestBrandDTO;
import com.example.LaptopKG.dto.brand.ResponseBrandDTO;
import com.example.LaptopKG.service.implementations.BrandServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
    name = "Контроллер для работы с брендами",
    description = "В этом контроллеры есть возможности добавления, получения и обновления брендов"
)
public class BrandController {
    private final BrandServiceImpl brandServiceImpl;

    @GetMapping()
    @Operation(
            summary = "Получение всех брендов"
    )
    public List<ResponseBrandDTO> getAllBrands(){
        return brandServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение бренда по айди"
    )
    public ResponseBrandDTO getBrandById(@PathVariable Long id){
        return brandServiceImpl.getById(id);
    }

    @GetMapping("/deleted")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Получение всех удаленных брендов"
    )
    public List<ResponseBrandDTO> getAllDeletedBrands(){
        return brandServiceImpl.getAllDeletedBrands();
    }

    @PostMapping("/create")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Добавление бренда"
    )
    public ResponseBrandDTO createBrand(@RequestBody RequestBrandDTO createBrandDto){
        return brandServiceImpl.createBrand(createBrandDto);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Обновление бренда по айди"
    )
    public ResponseBrandDTO updateBrand(@PathVariable Long id, @RequestBody RequestBrandDTO updateBrandDto){
        return brandServiceImpl.updateBrand(id, updateBrandDto);
    }

    @PutMapping("/restore/{id}")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Восстановление бренда по айди"
    )
    public ResponseBrandDTO restoreBrandById(@PathVariable Long id) {
        return brandServiceImpl.restoreBrandById(id);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Удаление бренда"
    )
    public ResponseEntity<String> deleteBrand(@PathVariable Long id){
        return brandServiceImpl.deleteBrand(id);
    }
}
