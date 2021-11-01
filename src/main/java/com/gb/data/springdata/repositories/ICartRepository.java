package com.gb.data.springdata.repositories;

import com.gb.data.springdata.entities.Cart;
import com.gb.data.springdata.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {
}
