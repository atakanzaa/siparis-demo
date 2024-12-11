package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tr.edu.ogu.ceng.Order.entity.Customer;
import tr.edu.ogu.ceng.Order.repository.CustomerRepository;


@Service
@RequiredArgsConstructor

public class CustomerService {

    private final RestClient restClient;

    private final CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer ){
        restClient.get().uri("http://192.168.137.149:8001/api/users/{username}","testuser")
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Customer.class);
        return customerRepository.save(customer );
    }




}