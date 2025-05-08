package com.guorong.factory.method.plan_2;

import com.guorong.factory.method.plan_2.factory.DefaultPaymentFactoryRegistry;
import com.guorong.factory.method.plan_2.factory.IPaymentFactory;

public class AppClient {
    public static void main(String[] args) {
        String filePath = "strategy/discount-strategies.json";
        DefaultPaymentFactoryRegistry factoryRegistry = new DefaultPaymentFactoryRegistry(filePath);
        IPaymentFactory paymentFactory = factoryRegistry.getPaymentFactory(PaymentType.ALIPAY);
        PaymentContext context = new PaymentContext(100, EnvironmentType.ONLINE);
        IPayment payment = paymentFactory.getPayment(context);
        payment.pay(context);
    }
}
