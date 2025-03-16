package kr.co.ordermanagement.domain.product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
}
