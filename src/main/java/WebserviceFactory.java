import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import io.github.resilience4j.retry.Retry;

public final class WebserviceFactory{

    static <T> T decorateWithRetryer(final T service, Retry retry) {

        InvocationHandler invocationHandler = (proxy, method, args) -> retry.executeCheckedSupplier(() -> method.invoke(service, args));
    
        return (T) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(), invocationHandler);
    }
    

}