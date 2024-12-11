package com.nttdata.p1.customer_service.repository;

import com.nttdata.p1.customer_service.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {
    Mono<Customer> createCustomer(Customer customer);
    Flux<Customer> getAllCustomers();
    Mono<Customer> getCustomer(String id);
    Mono<Customer> updateCustomer(String id, Customer customer);
    Mono<Void> deleteCustomer(String id);
}
