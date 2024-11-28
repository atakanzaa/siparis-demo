package tr.edu.ogu.ceng.Order.ControllerTest;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tr.edu.ogu.ceng.Order.AbstractContainerBaseTest;
import tr.edu.ogu.ceng.Order.Service.CustomerService;

@SpringBootTest
public class CustomerControllerTest extends AbstractContainerBaseTest {

    @MockBean
    private CustomerService customerService;


}
