package kr.co.ordermanagement.infrastructure;

import kr.co.ordermanagement.domain.exception.EntityNotFoundException;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.order.OrderedProduct;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.domain.product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListOrderRepository implements OrderRepository {
    private List<Order> orders = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Order findById(Long id) {
        return orders.stream()
                .filter(order -> order.sameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Order를 찾지 못했습니다."));
    }

    @Override
    public Order addOrders(List<OrderedProduct> orders) {
        Integer total_price = orders.stream()
                .mapToInt(order -> order.getAmount()*order.getPrice())
                .sum();

        Order order = new Order(sequence.getAndAdd(1L), orders, total_price);
        this.orders.add(order);

        return order;
    }

    @Override
    public List<Order> findByState(State state) {
        return orders.stream()
                .filter(order -> order.sameState(state))
                .toList();
    }

}
