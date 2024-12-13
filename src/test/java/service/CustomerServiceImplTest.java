package service;

import com.nttdata.p1.customer_service.model.Customer;
import com.nttdata.p1.customer_service.repository.CustomerRepository;
import com.nttdata.p1.customer_service.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void createCustomer_shouldReturnCreatedCustomer() {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setType("personal");

        Mockito.when(customerRepository.createCustomer(customer))
                .thenReturn(Mono.just(customer));

        StepVerifier.create(customerService.createCustomer(customer))
                .expectNextMatches(createdCustomer -> createdCustomer.getId().equals("1"))
                .verifyComplete();
    }

    @Test
    void getCustomer_shouldReturnCustomerById() {
        Customer customer = new Customer();
        customer.setId("1");

        Mockito.when(customerRepository.getCustomer("1"))
                .thenReturn(Mono.just(customer));

        StepVerifier.create(customerService.getCustomer("1"))
                .expectNextMatches(foundCustomer -> foundCustomer.getId().equals("1"))
                .verifyComplete();
    }
    @Test
    void deleteCustomer_shouldCompleteSuccessfully() {
        Mockito.when(customerRepository.deleteCustomer("1"))
                .thenReturn(Mono.empty());

        StepVerifier.create(customerService.deleteCustomer("1"))
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

        Mockito.when(customerRepository.getAllCustomers())
                .thenReturn(Flux.just(customer1, customer2));

        StepVerifier.create(customerService.getAllCustomers())
                .expectNextMatches(customer -> customer.getId().equals("1"))
                .expectNextMatches(customer -> customer.getId().equals("2"))
                .verifyComplete();
    }
}
