package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.presentation.dto.OrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import kr.co.ordermanagement.presentation.dto.StateChangeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<OrderResponseDto> orderCame(@RequestBody List<OrderRequestDto> orderRequestDto) {
        OrderResponseDto response = simpleOrderService.processOrderList(orderRequestDto);
        return ResponseEntity.ok(response);
    }

    // 2. 주문 상태 강제 변경 API
    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PATCH)
    public ResponseEntity<OrderResponseDto> changeOrderState(@RequestBody StateChangeRequestDto stateChangeRequestDto, @PathVariable Long orderId) {
        OrderResponseDto response = simpleOrderService.changeOrderState(orderId, stateChangeRequestDto.getState());
        return ResponseEntity.ok(response);
    }

    // 3. 주문번호로 조회 API
    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderResponseDto> getOrderByOrderId(@PathVariable Long orderId) {
        OrderResponseDto response = simpleOrderService.getOrderByOrderId(orderId);
        return ResponseEntity.ok(response);
    }

    // 4. 주문상태로 조회 API
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<List<OrderResponseDto>> getOrderByState(@RequestParam State state) {
        List<OrderResponseDto> response = simpleOrderService.getOrderByState(state);
        return ResponseEntity.ok(response);
    }

    // 5. 주문 취소 API
    @RequestMapping(value = "/orders/{orderId}/cancel", method = RequestMethod.PATCH)
    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable Long orderId) {
        OrderResponseDto response = simpleOrderService.cancelOrder(orderId);
        return ResponseEntity.ok(response);
    }

}
