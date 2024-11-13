package com.nttdata.p1.customer_service.service;

import com.nttdata.p1.customer_service.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> createCustomer(Customer customer);
    Flux<Customer> getAllCustomers();
    Mono<Customer> updateCustomer(String id, Customer customer);
    Mono<Void> deleteCustomer(String id);
}