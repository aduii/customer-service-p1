package com.nttdata.p1.customer_service.controller;

import com.nttdata.p1.customer_service.model.Customer;
import com.nttdata.p1.customer_service.service.CustomerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
@Setter
@RefreshScope
@RestController
@RequestMapping("api/v1/customer")
public class CustomerServiceController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Mono<ResponseEntity<Customer>> createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer)
                .map(data -> new ResponseEntity<>(data, HttpStatus.CREATED));
    }

    @GetMapping
    public Flux<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PutMapping("/{id}")
    public Mono<Customer> updateCustomer(
            @PathVariable String id,
            @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }
}
