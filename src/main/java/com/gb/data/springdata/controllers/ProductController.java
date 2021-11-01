package com.gb.data.springdata.controllers;

import com.gb.data.springdata.dtos.ProductDto;
import com.gb.data.springdata.entities.Product;
import com.gb.data.springdata.exceptions.ResourceNotFoundException;
import com.gb.data.springdata.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> findAll(@RequestParam(value = "minPrice", required = false, defaultValue = "0") Integer minPrice,
                                                    @RequestParam(value = "maxPrice", required = false, defaultValue = "99999") Integer maxPrice) {
        return new ResponseEntity<>(service.findAllByPriceBetween(minPrice, maxPrice), HttpStatus.OK);

    }

    @PreAuthorize("hasRoles('ROLE_USER')")
    @GetMapping("/{id}")
    private ProductDto findById(@PathVariable String id) throws ResourceNotFoundException {
        return service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    @PreAuthorize("hasRoles('ROLE_ADMIN')")
    @PostMapping
    private ResponseEntity<Product> save(@RequestBody Product product) {
        Product created = service.save(product);
        return ResponseEntity.created(URI.create("/products/" + created.getId())).body(created);
    }

    @PreAuthorize("hasRoles('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRoles('ROLE_ADMIN')")
    private ResponseEntity<?> delete(@RequestBody Product product) {
        service.delete(product);
        return ResponseEntity.ok().build();
    }

}

