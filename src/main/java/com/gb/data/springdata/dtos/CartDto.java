package com.gb.data.springdata.dtos;

import com.gb.data.springdata.entities.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class CartDto {

    private long id;
    private long userId;
    private List<ProductDto> products;

    public CartDto(Cart cart) {
        this.id = cart.getId();
        this.userId = cart.getUser().getId();
        this.products = cart.getProducts().stream().map(ProductDto::new).collect(Collectors.toList());
    }


}
