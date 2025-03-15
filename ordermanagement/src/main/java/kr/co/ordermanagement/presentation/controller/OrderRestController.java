package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.presentation.dto.OrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import kr.co.ordermanagement.presentation.dto.StateChangeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderRestController {

    private SimpleOrderService simpleOrderService;

    @Autowired
    OrderRestController (SimpleOrderService simpleOrderService) {
        this.simpleOrderService = simpleOrderService;
    }

    // 1. 상품 주문 API
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public OrderResponseDto orderCame(@RequestBody List<OrderRequestDto> orderRequestDto) {
        return null;
    }

    // 2. 주문 상태 강제 변경 API
    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PATCH)
    public OrderResponseDto changeOrderState(@RequestBody StateChangeRequestDto stateChangeRequestDto) {
        return null;
    }

    // 3. 주문번호로 조회 API
    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public OrderResponseDto getOrderByOrderId(@PathVariable String orderId) {
        return null;
    }

    // 4. 주문상태로 조회 API
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<OrderResponseDto> getOrderByStatus(@RequestParam String state) {
        return null;
    }

    // 5. 주문 취소 API
    @RequestMapping(value = "/orders/{orderId}/cancel", method = RequestMethod.PATCH)
    public OrderResponseDto cancelOrder(@PathVariable String orderId) {
        return null;
    }

}
