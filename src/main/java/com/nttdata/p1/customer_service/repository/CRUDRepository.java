package com.nttdata.p1.customer_service.repository;

import com.nttdata.p1.customer_service.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CRUDRepository extends ReactiveMongoRepository<Customer, String> {

}
