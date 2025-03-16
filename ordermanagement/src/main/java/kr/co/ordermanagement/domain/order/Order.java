package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.product.Product;

import java.util.List;
import java.util.Objects;

public class Order {
    private Long id;
    private List<OrderedProduct> orderedProducts;
    private Integer totalPrice;
    private State state = State.CREATED;

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }
    public boolean sameState(State state) {
        return this.state.equals(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    public Order(Long id, List<OrderedProduct> orderedProducts, Integer totalPrice) {
        this.id = id;
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
    }

    public Order(Long id, List<OrderedProduct> orderedProducts, Integer totalPrice, State state) {
        this.id = id;
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
        this.state = state;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void cancel() {
        this.state.checkCancellable();
        this.state = State.CANCELED;
    }

    public Long getId() {
        return id;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public State getState() {
        return state;
    }
}
