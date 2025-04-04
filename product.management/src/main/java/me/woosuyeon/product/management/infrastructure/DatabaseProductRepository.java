package me.woosuyeon.product.management.infrastructure;

import me.woosuyeon.product.management.domain.EntityNotFoundException;
import me.woosuyeon.product.management.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import me.woosuyeon.product.management.domain.Product;

import java.util.Collections;
import java.util.List;

@Repository
@Profile("prod")
public class DatabaseProductRepository implements ProductRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public DatabaseProductRepository (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public Product add(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);
        namedParameterJdbcTemplate
                .update("INSERT INTO PRODUCTS (name, price, amount) VALUES (:name, :price, :amount)",
                        namedParameter, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        product.setId(generatedId);

        return product;
    }

    public Product findById(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);

        Product product = null;
        try {
            product = namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, name, price, amount FROM PRODUCTS WHERE id=:id",
                    namedParameter,
                    new BeanPropertyRowMapper<>(Product.class)
            );

        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException("Product를 찾지 못했습니다.");
        }

        return product;
    }

    public List<Product> findAll() {
        List<Product> products = namedParameterJdbcTemplate.query(
                "SELECT * FROM PRODUCTS",
                new BeanPropertyRowMapper<>(Product.class)
        );

        return products;
    }

    public List<Product> findByNameContaining(String name) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("name", "%"+name+"%");

        List<Product> products = namedParameterJdbcTemplate.query(
                "SELECT * FROM PRODUCTS WHERE name LIKE :name",
                namedParameter,
                new BeanPropertyRowMapper<>(Product.class)
        );

        return products;
    }

    public Product update(Product product) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);
        namedParameterJdbcTemplate
                .update("UPDATE PRODUCTS SET name=:name, price=:price, amount=:amount WHERE id=:id",
                        namedParameter);

        return product;
    }

    public void delete(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate
                .update("DELETE FROM PRODUCTS WHERE id=:id",
                        namedParameter);
    }

}
