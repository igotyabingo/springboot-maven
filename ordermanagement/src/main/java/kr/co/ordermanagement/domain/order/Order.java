package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.product.Product;

import java.util.List;
import java.util.Objects;

public class Order {
    private Long id;
    private List<Product> orderedProducts;
    private Integer totalPrice;
    private String state = "CREATED";

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }
    public boolean sameState(String state) {
        return this.state.equals(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    public Order(Long id, List<Product> orderedProducts, Integer totalPrice) {
        this.id = id;
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
    }

    public Order(Long id, List<Product> orderedProducts, Integer totalPrice, String state) {
        this.id = id;
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
        this.state = state;
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

    public String getState() {
        return state;
    }
}
