package tr.edu.ogu.ceng.Order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Order.entity.Customer;
import tr.edu.ogu.ceng.Order.repository.CustomerRepository;


@Service
@RequiredArgsConstructor

public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer ){
        return customerRepository.save(customer );
    }
}