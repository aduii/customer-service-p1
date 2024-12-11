package com.nttdata.p1.customer_service.repository;

import com.nttdata.p1.customer_service.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private CRUDRepository crudRepository;

    @Override
    public Mono<Customer> createCustomer(Customer customer) {
        return crudRepository.save(customer);
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return crudRepository.findAll();
    }

    @Override
    public Mono<Customer> getCustomer(String id) {
        return crudRepository.findById(id);
    }

    @Override
    public Mono<Customer> updateCustomer(String id, Customer customer) {
        return crudRepository.findById(id)
                .flatMap(existinCustomer -> {
                    existinCustomer.setName(customer.getName());
                    existinCustomer.setEmail(customer.getEmail());
                    existinCustomer.setType(customer.getType());

                    return crudRepository.save(existinCustomer);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Customer not found with id: " + id)));
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {
        return crudRepository.deleteById(id);
    }
}
