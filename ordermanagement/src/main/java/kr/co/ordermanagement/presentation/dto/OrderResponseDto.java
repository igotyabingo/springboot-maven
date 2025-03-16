package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.domain.product.Product;

import java.util.List;

public class OrderResponseDto {
    private Long id;
    private List<Product> orderedProducts;
    private Integer totalPrice;
    private State state;

    public OrderResponseDto(Long id, List<Product> orderedProducts, Integer totalPrice, State state) {
        this.id = id;
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
        this.state = state;
    }

    public static OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(order.getId(), order.getOrderedProducts(), order.getTotalPrice(), order.getState());
    }

    public Long getId() {
        return id;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public State getState() {
        return state;
    }
}
