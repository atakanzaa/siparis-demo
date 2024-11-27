package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
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

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}