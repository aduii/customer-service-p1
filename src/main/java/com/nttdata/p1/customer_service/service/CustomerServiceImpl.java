package com.nttdata.p1.customer_service.service;

import com.nttdata.p1.customer_service.model.Customer;
import com.nttdata.p1.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Customer> createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer);
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Mono<Customer> getCustomer(String id) {
        return customerRepository.getCustomer(id);
    }

    @Override
    public Mono<Customer> updateCustomer(String id, Customer customer) {
        return customerRepository.updateCustomer(id, customer);
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {
        return customerRepository.deleteCustomer(id);
    }
}
