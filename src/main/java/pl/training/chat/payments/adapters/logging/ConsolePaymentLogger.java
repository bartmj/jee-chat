package pl.training.chat.payments.adapters.logging;

import lombok.extern.java.Log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

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
