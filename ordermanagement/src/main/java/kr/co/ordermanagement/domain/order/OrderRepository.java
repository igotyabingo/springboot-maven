package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.product.Product;

import java.util.List;

public interface OrderRepository {
    public Order findById(Long id);
    public Order addOrders(List<Product> orders);
    public void update(Order order);
}
