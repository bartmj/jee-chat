package pl.training.chat.messages.adapters.logging;

import lombok.extern.java.Log;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@LogPayments
@Interceptor
@Log
public class ConsolePaymentLogger {

    @AroundInvoke
    public Object log(InvocationContext invocationContext) throws Exception {
        var payment = invocationContext.proceed();
        log.info("New payment: " + payment);
        return payment;
    }

}
