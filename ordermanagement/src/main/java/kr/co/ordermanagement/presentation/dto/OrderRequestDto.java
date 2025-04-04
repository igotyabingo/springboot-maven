package kr.co.ordermanagement.presentation.dto;

public class OrderRequestDto {
    private Long id;
    private Integer amount;

    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public OrderRequestDto(Long id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }
}
