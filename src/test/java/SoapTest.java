
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.learnwebservices.services.hello.HelloEndpoint;
import com.learnwebservices.services.hello.HelloEndpointService;
import com.learnwebservices.services.hello.HelloRequest;
import com.learnwebservices.services.hello.HelloResponse;

import org.junit.Test;

import io.github.resilience4j.retry.RetryRegistry;

public class SoapTest {

    @Test
    public void clientTest() {

        HelloEndpoint helloEndpointPort = new HelloEndpointService().getHelloEndpointPort();
        HelloEndpoint helloEndpoint = WebserviceFactory.decorateWithRetryer(helloEndpointPort,
                RetryRegistry.ofDefaults().retry("test"));

        HelloRequest helloRequest = new HelloRequest();
        helloRequest.setName("Foo Bar");

        HelloResponse response = helloEndpoint.sayHello(helloRequest);

        assertNotNull(response);
        assertTrue(response.getMessage().contains("Foo Bar"));

    }

}