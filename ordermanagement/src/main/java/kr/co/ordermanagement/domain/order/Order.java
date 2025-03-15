package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.product.Product;

import java.util.List;

public class Order {
    private Long id;
    private List<Product> orderedProducts;
    private Integer totalPrice;
    private String state = "CREATED";

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public Order(Long id, List<Product> orderedProducts, Integer totalPrice) {
        this.id = id;
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
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
