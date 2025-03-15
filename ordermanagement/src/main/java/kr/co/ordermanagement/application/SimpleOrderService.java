package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.exception.LackOfStockException;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.OrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import kr.co.ordermanagement.presentation.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Product> orderedProducts = toOrderedProducts(orders);
        decreaseAmounts(orderedProducts);

        Order order = orderRepository.addOrders(orderedProducts);
        return OrderResponseDto.toDto(order);
    }

    private List<Product> toOrderedProducts(List<OrderRequestDto> orders) {
        List<Product> orderedProducts = orders.stream()
                .map(order -> {
                    Long id = order.getId();
                    Product product = productRepository.findById(id);
                    if (product.getAmount() < order.getAmount())
                        throw new LackOfStockException(id +"번 상품의 수량이 부족합니다.");

                    return new Product(id, product.getName(), product.getPrice(), order.getAmount());
                })
                .toList();

        return orderedProducts;
    }

    private void decreaseAmounts(List<Product> orderedProducts) {
        orderedProducts.stream()
                .forEach(order -> {
                    Product product = productRepository.findById(order.getId());
                    productRepository.update(new Product(product.getId(), product.getName(), product.getPrice(), product.getAmount() - order.getAmount()));
                });
    }
}
