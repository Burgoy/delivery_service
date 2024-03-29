package com.delivery.service.delivery_service.controllers;

import com.delivery.service.delivery_service.dto.OrderDto;
import com.delivery.service.delivery_service.dto.UpdateOrderAddressRequest;
import com.delivery.service.delivery_service.entities.OrderEntity;
import com.delivery.service.delivery_service.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api")
public class ClientController {
    OrderService orderService;

    @Operation(summary = "Create new order")
    @PostMapping("/client/order")
    public ResponseEntity<OrderEntity> createNewOrder(@RequestBody @Valid OrderDto dto) {
        return new ResponseEntity<>(orderService.create(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Change delivery address")
    @PatchMapping("client/order")
    public ResponseEntity<OrderEntity>
    updateAddressByOrderId(@RequestBody UpdateOrderAddressRequest request) {
        return new ResponseEntity<>(orderService.updateOrderAddress(request), HttpStatus.OK);
    }

    @Operation(summary = "Get order description by id")
    @GetMapping("/client/order/{id}")
    public ResponseEntity<String> getOrderDescription(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.showDescription(id), HttpStatus.OK);
    }
    @Operation(summary = "Get all client orders")
    @GetMapping("/client/order")
    public ResponseEntity<List<OrderEntity>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllClientOrders(), HttpStatus.OK);
    }

    @Operation(summary = "Drop order by order_id")
    @DeleteMapping("client/order/{id}")
    public HttpStatus deleteOrder(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return HttpStatus.OK;
    }


}
