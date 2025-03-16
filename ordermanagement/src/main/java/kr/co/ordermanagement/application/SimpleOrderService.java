package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.exception.LackOfStockException;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.order.OrderedProduct;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.OrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleOrderService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Autowired
    public SimpleOrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderResponseDto processOrderList(List<OrderRequestDto> orders) {
        List<OrderedProduct> orderedProducts = toOrderedProducts(orders);
        decreaseAmounts(orderedProducts);

        Order order = orderRepository.addOrders(orderedProducts);
        return OrderResponseDto.toDto(order);
    }

    public OrderResponseDto changeOrderState(Long id, State state) {
        Order order = orderRepository.findById(id);
        order.changeState(state);

        return OrderResponseDto.toDto(order);
    }

    public OrderResponseDto getOrderByOrderId(Long id) {
        Order order = orderRepository.findById(id);

        return OrderResponseDto.toDto(order);
    }

    public List<OrderResponseDto> getOrderByState(State state) {
        List<Order> orders = orderRepository.findByState(state);

        List<OrderResponseDto> orderDtos = orders.stream()
                                            .map(order -> OrderResponseDto.toDto(order))
                                            .toList();
        return orderDtos;
    }

    public OrderResponseDto cancelOrder(Long id) {
        Order order = orderRepository.findById(id);
        order.cancel();
        return OrderResponseDto.toDto(order);
    }

    private List<OrderedProduct> toOrderedProducts(List<OrderRequestDto> orders) {
        List<OrderedProduct> orderedProducts = orders.stream()
                .map(order -> {
                    Long id = order.getId();
                    Product product = productRepository.findById(id);
                    if (product.getAmount() < order.getAmount())
                        throw new LackOfStockException(id +"번 상품의 수량이 부족합니다.");

                    return new OrderedProduct(id, product.getName(), product.getPrice(), order.getAmount());
                })
                .toList();

        return orderedProducts;
    }

    private void decreaseAmounts(List<OrderedProduct> orderedProducts) {
        orderedProducts.stream()
                .forEach(order -> {
                    Product product = productRepository.findById(order.getId());
                    product.decreaseAmount(order.getAmount());
                });
    }
}
