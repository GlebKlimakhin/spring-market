package com.gb.data.springdata.controllers;

import com.gb.data.springdata.dtos.CartDto;
import com.gb.data.springdata.entities.Cart;
import com.gb.data.springdata.exceptions.ResourceNotFoundException;
import com.gb.data.springdata.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService service;

    @GetMapping
    public ResponseEntity<List<CartDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public CartDto findById(@PathVariable long id){
        return service.findById(id).orElseThrow(() -> new ResourceNotFoundException("The cart with provided id does not exist"));
    }

    @PostMapping
    public ResponseEntity<Cart> save(@RequestBody Cart cart){
        Cart created = service.save(cart);
        return ResponseEntity.created(URI.create("/carts/" + cart.getId())).body(created);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Cart cart){
        service.delete(cart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
