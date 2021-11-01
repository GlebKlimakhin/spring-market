package com.gb.data.springdata.services;

import com.gb.data.springdata.dtos.CartDto;
import com.gb.data.springdata.entities.Cart;
import com.gb.data.springdata.repositories.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ICartRepository repository;

    public Optional<CartDto> findById(long id){
        return repository.findById(id).map(CartDto::new);
    }

    public List<CartDto> findAll(){
        return repository.findAll().stream().map(CartDto::new).collect(Collectors.toList());
    }

    public Cart save(Cart cart){
        return repository.save(cart);
    }

    public void delete(Cart cart){
        repository.delete(cart);
    }

    public void deleteById(long id){
        repository.deleteById(id);
    }

}
