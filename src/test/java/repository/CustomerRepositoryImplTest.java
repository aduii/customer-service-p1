package repository;

import com.nttdata.p1.customer_service.model.Customer;
import com.nttdata.p1.customer_service.repository.CustomerRepositoryImpl;
import com.nttdata.p1.customer_service.repository.CRUDRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryImplTest {

    @Mock
    private CRUDRepository crudRepository;

    @InjectMocks
    private CustomerRepositoryImpl customerRepository;

    @Test
    void createCustomer_shouldReturnCreatedCustomer() {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setType("personal");

        Mockito.when(crudRepository.save(customer))
                .thenReturn(Mono.just(customer));

        StepVerifier.create(customerRepository.createCustomer(customer))
                .expectNextMatches(createdCustomer -> createdCustomer.getId().equals("1"))
                .verifyComplete();
    }

    @Test
    void getCustomer_shouldReturnCustomerById() {
        Customer customer = new Customer();
        customer.setId("1");

        Mockito.when(crudRepository.findById("1"))
                .thenReturn(Mono.just(customer));

        StepVerifier.create(customerRepository.getCustomer("1"))
                .expectNextMatches(foundCustomer -> foundCustomer.getId().equals("1"))
                .verifyComplete();
    }

    @Test
    void updateCustomer_shouldReturnUpdatedCustomer() {
        Customer existingCustomer = new Customer();
        existingCustomer.setId("1");
        existingCustomer.setName("John Doe");
        existingCustomer.setEmail("john.doe@example.com");
        existingCustomer.setType("personal");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId("1");
        updatedCustomer.setName("Jane Doe");
        updatedCustomer.setEmail("jane.doe@example.com");
        updatedCustomer.setType("business");

        Mockito.when(crudRepository.findById("1"))
                .thenReturn(Mono.just(existingCustomer));

        Mockito.when(crudRepository.save(updatedCustomer))
                .thenReturn(Mono.just(updatedCustomer));

        StepVerifier.create(customerRepository.updateCustomer("1", updatedCustomer))
                .expectNextMatches(customer -> customer.getName().equals("Jane Doe"))
                .verifyComplete();
    }

    @Test
    void deleteCustomer_shouldCompleteSuccessfully() {
        Mockito.when(crudRepository.deleteById("1"))
                .thenReturn(Mono.empty());

        StepVerifier.create(customerRepository.deleteCustomer("1"))
                .verifyComplete();
    }

    @Test
    void getAllCustomers_shouldReturnAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId("1");
        customer1.setName("John Doe");
        customer1.setEmail("john.doe@example.com");
        customer1.setType("personal");

        Customer customer2 = new Customer();
        customer2.setId("2");
        customer2.setName("Jane Doe");
        customer2.setEmail("jane.doe@example.com");
        customer2.setType("business");

        Mockito.when(crudRepository.findAll())
                .thenReturn(Flux.just(customer1, customer2));

        StepVerifier.create(customerRepository.getAllCustomers())
                .expectNextMatches(customer -> customer.getId().equals("1"))
                .expectNextMatches(customer -> customer.getId().equals("2"))
                .verifyComplete();
    }
}
