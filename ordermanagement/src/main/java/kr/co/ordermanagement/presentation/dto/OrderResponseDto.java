package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.product.Product;

import java.util.List;

public class OrderResponseDto {
    private Long id;
    private List<Product> orderedProducts;
    private Integer totalPrice;
    private String state;
}
